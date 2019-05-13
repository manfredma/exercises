package manfred.end.rdbms.mybatis.version;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class MyBatisMySQLVersion {

    private static SqlSessionFactory factory;
    private static SqlSession session;


    @BeforeClass
    public static void init() throws IOException {
        String resource = "version/mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(MyMapper.class);
        reader.close();
        session = factory.openSession();
    }

    @Test
    public void getVersion() {
        String version = session.selectOne("getMySQLVersion");
        System.out.println(version);
    }


    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }
}