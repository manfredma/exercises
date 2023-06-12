package manfred.end.shard.mybatis.multidatasource;

import manfred.end.shard.mybatis.annotation.NoneTransaction;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NoneTransaction
public class DataSourceHolder {

    public static final String PRIMARY = "primary";

    private static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(() -> null);

    private static final Map<String, DataSourceUrl> dataSourceCache = new ConcurrentHashMap<>();

    public static class DataSourceUrl {
        public final String datasourceId;
        public final String jdbcUrl;
        public final String dbHost;

        private DataSourceUrl(String datasourceId, String jdbcUrl, String dbHost) {
            this.datasourceId = datasourceId;
            this.jdbcUrl = jdbcUrl;
            this.dbHost = dbHost;
        }
    }

    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceCache.containsKey(dataSourceId);
    }

    public static void addDataSource(String dataSourceId, String jdbcUrl) {
        try {
            URI uri = URI.create(jdbcUrl.substring(5));
            String dbHost = uri.getHost() + ":" + uri.getPort();
            dataSourceCache.put(dataSourceId, new DataSourceUrl(dataSourceId, jdbcUrl, dbHost));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSourceUrl getCurrentDataSourceUrl() {
        String dbName = getCurrentDataSourceId();
        if (dbName == null) {
            return null;
        }
        return dataSourceCache.get(dbName);
    }

    public static String getCurrentDataSourceId() {
        return contextHolder.get();
    }

    public static void setCurrentDataSourceId(String dataSourceId) {
        contextHolder.set(dataSourceId);
    }

    public static void resetCurrentDataSourceId() {
        contextHolder.set(null);
    }
}
