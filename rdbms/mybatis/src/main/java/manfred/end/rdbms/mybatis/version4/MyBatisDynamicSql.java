package manfred.end.rdbms.mybatis.version4;

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
import java.util.List;

/**
 * @author maxingfang
 */
public class MyBatisDynamicSql {
    private static SqlSessionFactory factory;
    private static SqlSession session;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "version4/mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        factory = new SqlSessionFactoryBuilder().build(reader);
        session = factory.openSession();
    }

    @Test
    public void getBook() {
        Book book = session.selectOne("getBook", 5);
        System.out.println(book);

        List<Book> books = session.selectList("getBook");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }
}