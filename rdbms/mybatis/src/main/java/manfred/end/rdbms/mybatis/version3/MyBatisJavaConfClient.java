package manfred.end.rdbms.mybatis.version3;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author maxingfang
 */
public class MyBatisJavaConfClient {

    private static SqlSessionFactory sesFact = null;

    @BeforeClass
    public static void init() {
        Properties prop = new Properties();
        prop.setProperty("driver", "org.h2.Driver");
        prop.setProperty("url", "jdbc:h2:mem:MyDb;INIT=create schema if not exists MyDb\\;runscript from 'classpath:schema/schema.sql'");

        MyDataSourceFactory mdsf = new MyDataSourceFactory();
        mdsf.setProperties(prop);
        DataSource ds = mdsf.getDataSource();

        TransactionFactory trFact = new JdbcTransactionFactory();
        Environment environment = new Environment("development", trFact, ds);
        Configuration config = new Configuration(environment);
        config.addMapper(MyMapper.class);
        sesFact = new SqlSessionFactoryBuilder().build(config);
    }

    @Test
    public void getBooks() {
        try (SqlSession session = sesFact.openSession()) {
            int numOfBooks = session.selectOne("getNumberOfBooks");
            System.out.format("There are %d books", numOfBooks);
        }
    }
}