package manfred.end.rdbms.mybatis.version;

import org.apache.ibatis.annotations.Select;

public interface MyMapper {

    @Select("SELECT VERSION()")
    public String getMySQLVersion();
}