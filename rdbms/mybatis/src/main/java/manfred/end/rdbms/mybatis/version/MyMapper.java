package manfred.end.rdbms.mybatis.version;

import org.apache.ibatis.annotations.Select;

public interface MyMapper {

    @Select("SELECT h2VERSION() from dual")
    String getMySQLVersion();
}