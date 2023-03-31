package manfred.end.rdbms.mybatis.version6;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import manfred.end.rdbms.mybatis.Book;
import manfred.end.rdbms.mybatis.BookV2;
import manfred.end.rdbms.mybatis.version5.MyMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyBatisBooksXml {
    private static SqlSessionFactory factory = null;
    private static SqlSession session = null;

    @BeforeClass
    public static void main() throws IOException {
        String resource = "version6/mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(MyMapper.class);
        session = factory.openSession();
    }

    @Test
    public void deleteBooks2() {
        Map<String, Object> param = new HashMap<>();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        param.put("ids", ids);
        int result = session.delete("deleteBook", param);
        session.commit();
        System.out.println("result=" + result);
    }

    @Test
    public void deleteBooks() {
        Map<String, Object> param = new HashMap<>();
        List<Long> ids = Arrays.asList(7L, 9L, 10L, 11L, 12L);
        param.put("ids", ids);
        int result = session.delete("deleteBook", param);
        session.commit();
        System.out.println("result=" + result);
    }

    @Test
    public void selectBooks() {
        List<BookV2> bookV2s = session.selectList("selectBook");
        bookV2s.forEach(System.out::println);
    }

    @Test
    public void insertBooks() {
        Book book = new Book("T-Miguel de Cervantes", "T-Don Quixote",
                16050, "T-First modern novel");
        BookV2 bookV2 = new BookV2();
        bookV2.setBook(book);
        bookV2.setId(1000L);
        session.insert("com.zetcode.insertBook", bookV2);

    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }
}
