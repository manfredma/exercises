package manfred.end.rdbms.mybatis.version2;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class MyBatisMySQLVersion2 {

    private static SqlSessionFactory factory;
    private static SqlSession session;


    @BeforeClass
    public static void init() throws IOException {
        String resource = "version2/mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        factory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
        session = factory.openSession();
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void getVersion() {
        String version = session.selectOne("mysqlVersion");
        System.out.println(version);
    }
}