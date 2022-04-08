package manfred.end.rdbms.mybatis.version3;

import org.apache.ibatis.annotations.Select;

public interface MyMapper {

    @Select("SELECT COUNT(*) FROM MyBooks")
    public int getNumberOfBooks();
}