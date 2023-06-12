package manfred.end.shard.mybatis.multidatasource;

import org.springframework.beans.factory.FactoryBean;

import javax.sql.DataSource;

public class DataSourceFactoryBean implements FactoryBean<DataSource> {

    private final DataSource dataSource;

    public DataSourceFactoryBean(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public DataSource getObject() throws Exception {
        return dataSource;
    }

    @Override
    public Class<?> getObjectType() {
        return dataSource.getClass();
    }
}
