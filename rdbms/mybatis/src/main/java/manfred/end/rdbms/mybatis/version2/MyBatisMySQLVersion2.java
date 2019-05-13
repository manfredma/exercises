package manfred.end.rdbms.mybatis.version2;

import java.io.IOException;
import java.io.Reader;

import manfred.end.rdbms.mybatis.version.MyMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyBatisMySQLVersion2 {

    private static SqlSessionFactory factory;
    private static SqlSession session;


    @BeforeClass
    public static void init() throws IOException {
        String resource = "version2/mybatis-config.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(MyMapper.class);
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