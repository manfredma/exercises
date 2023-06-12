package manfred.end.shard.mybatis.multidatasource;

import org.springframework.core.env.Environment;

/**
 *
 */
public interface DatabaseRegister {

    String getDatabaseHostByName(String dbNamePlaceHolder, Environment environment);
}
