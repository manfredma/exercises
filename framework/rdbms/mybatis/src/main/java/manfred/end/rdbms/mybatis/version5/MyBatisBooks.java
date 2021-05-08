package manfred.end.rdbms.mybatis.version5;

import manfred.end.rdbms.mybatis.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

public class MyBatisBooks {

    private static SqlSessionFactory factory = null;

    private static SqlSession session = null;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "version5/mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(MyMapper.class);
        session = factory.openSession();
    }

    @Test
    public void queryBooks() {
        Book book = session.selectOne("getBookById", 4L);
        System.out.println("id=4, book=" + book);
        List<Book> books = session.selectList("getBooksByAuthor", "Miguel de Cervantes");
        System.out.println("Miguel de Cervantes's list:");
        for (Book b : books) {
            System.out.println("\t" + b);
        }
    }

    @Test
    public void insertBook() {
        Book newBook = new Book("Miguel de Cervantes", "Don Quixote",
                1605, "First modern novel");
        int result = session.update("insertBook", newBook);
        session.commit();
        System.out.println("result=" + result);
    }

    @Test
    public void updateBookCreated() {
        int i = session.update("updateBookCreated", new Date());
        System.out.println("update=" + i);
        session.commit();
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

}