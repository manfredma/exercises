package manfred.end.shard.mybatis.interceptors;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSetStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.visitor.VisitorFeature;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.JdbcConstants;
import manfred.end.shard.mybatis.multidatasource.DataSourceHolder;
import manfred.end.shard.mybatis.policy.SingleTableShardPolicy;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "queryCursor", args = {MappedStatement.class, Object.class, RowBounds.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class ShardTableInterceptor implements Interceptor {

    private static final Logger LOGGER = getLogger(ShardTableInterceptor.class);

    private static final int STR_MAX_LEN = 100;

    private final ExtraContextProvider extraContextProvider;

    private final MyBatisMetric myBatisMetric;

    private final boolean outputParams;

    public ShardTableInterceptor(ExtraContextProvider extraContextProvider, MyBatisMetric myBatisMetric) {
        this(extraContextProvider, myBatisMetric, false);
    }

    public ShardTableInterceptor(ExtraContextProvider extraContextProvider, MyBatisMetric myBatisMetric, boolean outputParams) {
        this.extraContextProvider = extraContextProvider;
        this.myBatisMetric = myBatisMetric;
        this.outputParams = outputParams;
    }

    private boolean isNotShard(ShardTableSupport.ShardConf shardConf) {
        if (shardConf.isEmpty() || shardConf.shardPolicy instanceof SingleTableShardPolicy) {
            return true;
        }
        return false;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = 0L;
        if (myBatisMetric != null) {
            start = System.nanoTime();
        }

        MetricDbInfo metricDbInfo = new MetricDbInfo();
        Throwable throwable = null;
        String[] sqlMd5Holder = {""};

        try {
            return execute(invocation, sqlMd5Holder, metricDbInfo);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throwable = e;
            throw e;
        } finally {
            long costTime = (System.nanoTime() - start) / 1_000_000;
            if (myBatisMetric != null) {
                String code = (throwable == null) ? "ok" : "error";
                myBatisMetric.reportMetric(costTime, code, metricDbInfo);
            }
            if (!outputParams) {
                LOGGER.info("mybatis intercept: || cost = {} ms || sqlmd5 = {}", costTime, sqlMd5Holder[0]);
            } else {
                LOGGER.info("mybatis intercept: || cost = {} ms || sqlmd5 = {} || params = {} ", costTime, sqlMd5Holder[0], makeParamsJson(invocation));
            }
        }
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String makeParamsJson(Invocation invocation) {
        Map<String, Object> paramsMap = (Map<String, Object>) invocation.getArgs()[1];
        Map<String, Object> copyMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            if (entry.getKey().startsWith("param") && StringUtils.isNumeric(entry.getKey().substring("param".length()))) {
                continue;
            }
            Object val = entry.getValue();
            if (val instanceof String) {
                String strVal = (String) val;
                if (strVal.length() > STR_MAX_LEN) {
                    val = strVal.substring(0, STR_MAX_LEN);
                }
            }
            if (entry.getKey().equals("po")) {
                val = convertPo(entry.getValue());
            }
            copyMap.put(entry.getKey(), val);
        }
        return JSONUtils.toJSONString(copyMap);
    }

    private Map<String, Object> convertPo(Object po) {
        if (po == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        List<Field> fields = FieldUtils.getAllFieldsList(po.getClass());
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            try {
                Object val = FieldUtils.readField(field, po, true);
                if (val instanceof String) {
                    String strVal = (String) val;
                    if (strVal.length() > STR_MAX_LEN) {
                        val = strVal.substring(0, STR_MAX_LEN);
                    }
                }
                map.put(field.getName(), val);
            } catch (IllegalAccessException e) {
                LOGGER.info("", e);
            }
        }
        return map;
    }

    private Object execute(Invocation invocation, String[] sqlMd5Holder, MetricDbInfo metricDbInfo) throws InvocationTargetException, IllegalAccessException {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        ShardTableSupport.ShardConf shardConf = ShardTableSupport.getConf(mappedStatement.getId());
        Object parameterObject = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        String sql = boundSql.getSql();
        DataSourceHolder.DataSourceUrl dataSourceUrl = DataSourceHolder.getCurrentDataSourceUrl();
        metricDbInfo.accept(dataSourceUrl.datasourceId, dataSourceUrl.dbHost);
        if (isNotShard(shardConf) && !ShardTableSupport.forceMaster.get() && !ShardTableSupport.userArchive.get() && !isWithShadow(shardConf)) {
            sqlMd5Holder[0] = DigestUtils.md5Hex(sql);
            LOGGER.info("NO CHANGE SQL || db={} || sqlmd5={} || sql={}", dataSourceUrl.dbHost, sqlMd5Holder[0], sql);
            return invocation.proceed();
        }
        boolean[] changeHolder = {false};
        long start = System.nanoTime();
        LOGGER.debug("before CHANGE SQL: {}", sql);
        String changeSql = changeSql(sql, shardConf, ShardTableSupport.forceMaster.get(), ShardTableSupport.userArchive.get(), changeHolder);
        long cost = (System.nanoTime() - start) / 1_000_000L;
        if (!changeHolder[0]) {
            sqlMd5Holder[0] = DigestUtils.md2Hex(sql);
            LOGGER.info("after NOCHANGED SQL || db=={} || sqlmd5={} || sql={} || cost = {} ms.", dataSourceUrl.dbHost, sqlMd5Holder[0], sql, cost);
        } else {
            sqlMd5Holder[0] = DigestUtils.md2Hex(changeSql);
            LOGGER.info("after CHANGED SQL || db=={} || sqlmd5={} || aftersql={} || cost = {} ms.", dataSourceUrl.dbHost, sqlMd5Holder[0], sql, cost);
        }

        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), changeSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
        MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
        for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
            String prop = parameterMapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        invocation.getArgs()[0] = newMs;

        return invocation.proceed();
    }

    public static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameter) {
            return boundSql;
        }
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(ms.getKeyProperties()[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    private String changeSql(String sql, ShardTableSupport.ShardConf shardConf, Boolean forceMaster, Boolean isArchive, boolean[] changeHolder) {
        changeHolder[0] = true;
        StringBuilder sqlBuilder = new StringBuilder();
        if (forceMaster && isWithShadow(shardConf)) {
            sqlBuilder.append(ShardTableSupport.MASTER_PRESS_HINT);
        } else {
            if (forceMaster) {
                sqlBuilder.append(ShardTableSupport.MASTER_HINT);
            }
            if (isWithShadow(shardConf)) {
                sqlBuilder.append(ShardTableSupport.PRESS_HINT);
            }
        }

        String[] extraInfos = getExtraInfo();
        if (extraInfos != null && extraInfos.length > 0) {
            for (String extraInfo : extraInfos) {
                if (extraInfo != null && extraInfo.length() > 0) {
                    sqlBuilder.append("/*").append(extraInfo).append("*/");
                }
            }
        }

        if (isNotShard(shardConf) && !isArchive) {
            if (sqlBuilder.length() == 0) {
                changeHolder[0] = false;
                return sql;
            }
            sqlBuilder.append(sql);
            return sqlBuilder.toString();
        }

        TableNameAwareVisitor visitor = new TableNameAwareVisitor(shardConf, isArchive);
        List<SQLStatement> statementList = resolveTableName(sql, visitor);
        String resolveTableSql = toSQLString(statementList);
        sqlBuilder.append(resolveTableSql);
        return sqlBuilder.toString();
    }

    private String toSQLString(List<SQLStatement> statementList) {
        StringBuilder out = new StringBuilder();
        MySqlOutputVisitorExt visitorExt = new MySqlOutputVisitorExt(out);
        for (int i = 0, size = statementList.size(); i < statementList.size(); i++) {
            SQLStatement stmt = statementList.get(i);
            if (i > 0) {
                SQLStatement preStmt = statementList.get(i - 1);
                if (!preStmt.isAfterSemi()) {
                    visitorExt.print(";");
                }

                List<String> comments = preStmt.getAfterCommentsDirect();
                visitComments(visitorExt, comments);
                visitorExt.println();
                if (!(stmt instanceof SQLSetStatement)) {
                    visitorExt.println();
                }
            }
            {
                List<String> comments = stmt.getBeforeCommentsDirect();
                if (comments != null) {
                    for (String comment : comments) {
                        visitorExt.printComment(comment);
                        visitorExt.println();
                    }
                }
            }
            stmt.accept(visitorExt);

            if (i == size - 1) {
                List<String> comments = stmt.getAfterCommentsDirect();
                visitComments(visitorExt, comments);
            }
        }
        return out.toString();
    }

    private void visitComments(MySqlOutputVisitorExt visitorExt, List<String> comments) {
        if (comments != null) {
            for (int i = 0; i < comments.size(); i++) {
                String comment = comments.get(i);
                if (i != 0) {
                    visitorExt.println();
                }
                visitorExt.printComment(comment);
            }
        }
    }

    private List<SQLStatement> resolveTableName(String sql, TableNameAwareVisitor visitor) {
        List<SQLStatement> statementList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        for (SQLStatement sqlStatement : statementList) {
            sqlStatement.accept(visitor);
        }
        return statementList;
    }

    private static class TableNameAwareVisitor extends MySqlASTVisitorAdapter {
        private final ShardTableSupport.ShardConf shardConf;
        private final boolean isArchive;

        TableNameAwareVisitor(ShardTableSupport.ShardConf shardConf, boolean isArchive) {
            this.shardConf = shardConf;
            this.isArchive = isArchive;
        }

        @Override
        public boolean visit(SQLExprTableSource x) {
            String tableName = x.getExpr().toString();
            if (isArchive) {
                tableName = shardConf.archiveNamePolicy.archiveName(tableName);
            }
            Object shardKey = ShardTableSupport.shardKey.get();
            String realName = shardConf.shardPolicy.shard(shardKey, shardConf.shardCount, tableName);
            x.setExpr(realName);
            return true;
        }
    }

    private static class MySqlOutputVisitorExt extends MySqlOutputVisitor {
        public MySqlOutputVisitorExt(Appendable appender) {
            super(appender);
            this.setUppCase(false);
            this.setPrettyFormat(true);
            this.setParameterized(false);
            this.config(VisitorFeature.OutputPrettyFormat, true);
            this.config(VisitorFeature.OutputUCase, false);
            this.config(VisitorFeature.OutputParameterized, false);
            this.setShardingSupport(false);
        }

        @Override
        public void println() {
            print(' ');
        }
    }

    private String[] getExtraInfo() {
        if (extraContextProvider == null) {
            return null;
        }
        return extraContextProvider.provider();
    }

    private boolean isWithShadow(ShardTableSupport.ShardConf shardConf) {
        if (extraContextProvider == null || !shardConf.hasShadow) {
            return false;
        }
        return extraContextProvider.isPress();
    }
}