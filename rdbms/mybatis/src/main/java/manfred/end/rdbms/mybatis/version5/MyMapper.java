package manfred.end.rdbms.mybatis.version5;

import manfred.end.rdbms.mybatis.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MyMapper {

    @Select("SELECT * FROM MyBooks WHERE Id = #{id}")
    Book getBookById(Long id);

    @Select("SELECT * FROM MyBooks WHERE Author = #{author}")
    List<Book> getBooksByAuthor(String author);

    @Insert("INSERT INTO MyBooks(Author, Title, Published, Remark) "
            + "VALUES(#{author}, #{title}, #{published}, #{remark})")
    void insertBook(String author, String title, int published,
                    String remark);

    @Update("update MyBooks set created = date_add(created, interval 1 second)")
    void updateBookCreated();
}