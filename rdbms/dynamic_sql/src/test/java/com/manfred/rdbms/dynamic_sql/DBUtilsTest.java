package com.manfred.rdbms.dynamic_sql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DBUtilsTest {
    private QueryRunner queryRunner;
    private JdbcDataSource dataSource;

    @BeforeEach
    void setUp() throws Exception {
        dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        queryRunner = new QueryRunner(dataSource);

        // 创建表
        queryRunner.update("CREATE TABLE IF NOT EXISTS users (" +
                         "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                         "name VARCHAR(100) NOT NULL," +
                         "age INT)");
    }

    @Test
    void testCRUD() throws Exception {
        // 插入
        String insertSql = "INSERT INTO users (name, age) VALUES (?, ?)";
        int insertCount = queryRunner.update(insertSql, "张三", 25);
        assertEquals(1, insertCount);

        // 查询
        String selectSql = "SELECT * FROM users WHERE name = ?";
        Map<String, Object> result = queryRunner.query(
            selectSql,
            new MapHandler(),
            "张三"
        );
        assertEquals(25, ((Number)result.get("age")).intValue());

        // 更新
        String updateSql = "UPDATE users SET age = ? WHERE name = ?";
        int updateCount = queryRunner.update(updateSql, 26, "张三");
        assertEquals(1, updateCount);

        // 删除
        String deleteSql = "DELETE FROM users WHERE name = ?";
        int deleteCount = queryRunner.update(deleteSql, "张三");
        assertEquals(1, deleteCount);
    }
}