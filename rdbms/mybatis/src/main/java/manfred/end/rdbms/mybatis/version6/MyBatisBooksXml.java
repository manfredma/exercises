package manfred.end.rdbms.mybatis.version6;

import manfred.end.rdbms.mybatis.version5.MyMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

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

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }
}
