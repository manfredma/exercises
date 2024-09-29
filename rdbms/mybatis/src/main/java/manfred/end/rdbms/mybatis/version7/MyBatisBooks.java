package manfred.end.rdbms.mybatis.version7;


import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import manfred.end.rdbms.mybatis.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyBatisBooks {


    private static MyMapper myMapper;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "version7/mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = factory.openSession();
        myMapper = factory.getConfiguration().getMapper(MyMapper.class, session);
    }

    @Test
    public void queryBooks() {
        Book book = myMapper.getBookById(4L);
        System.out.println("id=4, book=" + book);
        List<Book> books = myMapper.getBooksByAuthor("Debu Panda");
        System.out.println("Miguel de Cervantes's list:");
        for (Book b : books) {
            System.out.println("\t" + b);
        }
    }

    @Test
    public void insertBook() {
        int result = myMapper.insertBook("Miguel de Cervantes", "Don Quixote",
                1605, "First modern novel");
        System.out.println("result=" + result);
    }

}