package com.manfred.rdbms.dynamic_sql;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MyBatisTest {
    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void setUp() {
        // 配置H2数据源
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        // 先创建表
        try (Connection conn = dataSource.getConnection()) {
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                            "name VARCHAR(100) NOT NULL," +
                            "age INT)"
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to create table", e);
        }

        // 配置 MyBatis
        Environment environment = new Environment("test",
                new JdbcTransactionFactory(),
                dataSource);

        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Test
    void testCRUD() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            // 插入
            User user = new User("张三", 25);
            mapper.insert(user);
            assertNotNull(user.getId());

            // 查询
            User found = mapper.findById(user.getId());
            Assertions.assertEquals("张三", found.getName());

            // 更新
            user.setAge(26);
            int updateCount = mapper.update(user);
            Assertions.assertEquals(1, updateCount);

            // 删除
            int deleteCount = mapper.delete(user.getId());
            Assertions.assertEquals(1, deleteCount);

            session.commit();
        }
    }
}