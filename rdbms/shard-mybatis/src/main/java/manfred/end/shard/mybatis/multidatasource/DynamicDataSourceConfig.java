package manfred.end.shard.mybatis.multidatasource;

import com.alibaba.druid.pool.DruidDataSource;
import manfred.end.shard.mybatis.annotation.EnableMultiDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicDataSourceConfig implements ImportBeanDefinitionRegistrar, EnvironmentAware, BeanFactoryAware {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DynamicDataSourceConfig.class);

    public static final String DATA_SOURCE_BEAN_NAME = "datasource";

    public static final String JDBC_MYSQL_PREFIX = "jdbc:mysql://";

    private BeanFactory beanFactory;

    private transient Environment env;

    private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();

    private static final String[] DEFAULT_KEYS = {"spring.datasource.primary", "spring.datasource.other"};

    static {
        aliases.addAliases("url", "jdbc-url");
        aliases.addAliases("username", "user");
    }

    private transient Map<String, DataSource> customDataSources = new HashMap<>();

    private transient Binder binder;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
        binder = Binder.get(env);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
                                        BeanDefinitionRegistry beanDefinitionRegistry) {
        String[] dsKeys = getDataSourceKeys(annotationMetadata);
        Map<String, Object> defaultDataSourceProperties = binder.bind(dsKeys[0], Map.class).get();
        realizeJdbcUrl(dsKeys[0], defaultDataSourceProperties, annotationMetadata);

        // 获取数据类型
        String typeStr = env.getProperty(dsKeys[0] + ".type");
        Class<? extends DataSource> clazz = getDataSourceType(typeStr);

        DataSource defaultDataSource = bind(clazz, defaultDataSourceProperties);
        initDruidDatasource(defaultDataSource);
        DataSourceHolder.addDataSource(DataSourceHolder.PRIMARY, getJdbcFromConf(defaultDataSourceProperties));

        List<Map> configs = binder.bind(dsKeys[1], Bindable.listOf(Map.class)).get();
        for (int i = 0; i < configs.size(); i++) {
            Map config = configs.get(i);
            String key = config.get("key").toString();
            realizeJdbcUrl(key, config, annotationMetadata);
            clazz = getDataSourceType((String) config.get("type"));
            DataSource consumerDataSource = bind(clazz, config);
            initDruidDatasource(consumerDataSource);

            customDataSources.put(key, consumerDataSource);
            DataSourceHolder.addDataSource(key, getJdbcFromConf(config));
        }

        // 定义并注册bean
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(DynamicDataSource.class);
        MutablePropertyValues mpv = definition.getPropertyValues();
        mpv.add("defaultTargetDataSource", defaultDataSource);
        mpv.add("targetDataSources", customDataSources);
        definition.setPrimary(true);
        beanDefinitionRegistry.registerBeanDefinition(DATA_SOURCE_BEAN_NAME, definition);
        registerDataSource(beanDefinitionRegistry, dsKeys[0], defaultDataSource);
        for (Map.Entry<String, DataSource> entry : customDataSources.entrySet()) {
            registerDataSource(beanDefinitionRegistry, "spring.datasource." + entry.getKey(), entry.getValue());
        }
    }

    private void registerDataSource(BeanDefinitionRegistry beanDefinitionRegistry, String beanName, DataSource dataSource) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(DataSourceFactoryBean.class);
        ConstructorArgumentValues args = new ConstructorArgumentValues();
        args.addGenericArgumentValue(dataSource);
        gbd.setConstructorArgumentValues(args);
        beanDefinitionRegistry.registerBeanDefinition(beanName, gbd);
        log.info("created database bean: {}", beanName);
    }

    private String getJdbcFromConf(Map dsConfig) {
        if (dsConfig.containsKey("url")) {
            return (String) dsConfig.get("url");
        }
        if (dsConfig.containsKey("jdbc-url")) {
            return (String) dsConfig.get("jdbc-url");
        }
        if (dsConfig.containsKey("jdbcUrl")) {
            return (String) dsConfig.get("jdbcUrl");
        }
        throw new RuntimeException("can not recognize jdbc url config");
    }

    private void initDruidDatasource(DataSource defaultDataSource) {
        if (defaultDataSource instanceof DruidDataSource) {
            long start = System.nanoTime();
            try {
                ((DruidDataSource) defaultDataSource).init();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                log.info("init druid cost: {} ms", (System.nanoTime() - start) / 1 - 000 - 000);
            }
        }
    }

    private <T extends DataSource> T bind(Class<T> clazz, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get();
    }

    private Class<? extends DataSource> getDataSourceType(String typeStr) {
        Class<? extends DataSource> type;
        try {
            if (!StringUtils.isEmpty(typeStr)) {
                type = (Class<? extends DataSource>) Class.forName(typeStr);
            } else {
                type = DruidDataSource.class;
            }
            return type;
        } catch (Exception e) {
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr);
        }
    }

    private String[] getDataSourceKeys(AnnotationMetadata annotationMetadata) {
        if (annotationMetadata == null) {
            return DEFAULT_KEYS;
        }
        Map<String, Object> annotationAttributes =
                annotationMetadata.getAnnotationAttributes(EnableMultiDataSource.class.getName());

        if (annotationAttributes == null) {
            return DEFAULT_KEYS;
        }

        String primaryKey = (String) annotationAttributes.get("primaryKey");
        String otherKey = (String) annotationAttributes.get("otherKey");
        return new String[]{primaryKey, otherKey};
    }

    private void realizeJdbcUrl(String key, Map<String, Object> dsProperties, AnnotationMetadata annotationMetadata) {
        String registerBeanName = getRegisterBeanName(annotationMetadata);
        if (StringUtils.isEmpty(registerBeanName)) {
            return;
        }
        DatabaseRegister databaseRegister = beanFactory.getBean(registerBeanName, DatabaseRegister.class);

        for (Map.Entry<String, Object> entry : dsProperties.entrySet()) {
            if (entry.getValue() == null || !(entry.getValue() instanceof String)) {
                continue;
            }
            String jdbcUrlValue = entry.getValue().toString().trim();
            // 非MySQL 不支持
            if (StringUtils.isEmpty(jdbcUrlValue) || !jdbcUrlValue.startsWith(JDBC_MYSQL_PREFIX)) {
                continue;
            }

            int pos = jdbcUrlValue.indexOf("/", JDBC_MYSQL_PREFIX.length());
            if (pos <= 0) {
                continue;
            }

            String hostName = jdbcUrlValue.substring(JDBC_MYSQL_PREFIX.length(), pos);
            if (!hostName.startsWith("${") || !hostName.endsWith("}")) {
                continue;
            }

            String realHost = databaseRegister.getDatabaseHostByName(hostName.substring(2, hostName.length() - 1), env);
            String nJdbcUrl = JDBC_MYSQL_PREFIX + realHost + jdbcUrlValue.substring(pos);
            entry.setValue(nJdbcUrl);
            log.info("realize jdbc url: key = {} || realJdbcUrl = {}", key, nJdbcUrl);
        }
    }

    private String getRegisterBeanName(AnnotationMetadata annotationMetadata) {
        if (annotationMetadata == null) {
            return null;
        }

        Map<String, Object> annotationAttributes =
                annotationMetadata.getAnnotationAttributes(EnableMultiDataSource.class.getName());
        if (annotationAttributes == null) {
            return null;
        }
        return (String) annotationAttributes.get("dbRegisterBeanName");
    }


}
