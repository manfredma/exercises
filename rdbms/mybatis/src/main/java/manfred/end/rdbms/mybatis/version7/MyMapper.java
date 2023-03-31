package manfred.end.rdbms.mybatis.version7;

import java.util.Date;
import java.util.List;
import manfred.end.rdbms.mybatis.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MyMapper {

    Book getBookById(Long id);

    List<Book> getBooksByAuthor(String author);

    int insertBook(@Param("author") String author, @Param("title") String title,
                   @Param("published") int published, @Param("remark") String remark);

    int updateBookCreated(Date date);
}