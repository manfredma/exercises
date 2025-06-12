package com.manfred.rdbms.dynamic_sql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class SpringJdbcTest {
    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @BeforeEach
    void setUp() {
        // 使用 EmbeddedDatabaseBuilder 创建数据源
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .build();

        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Test
    void testCRUD() {
        // 插入
        Map<String, Object> params = new HashMap<>();
        params.put("name", "张三");
        params.put("age", 25);
        Number key = simpleJdbcInsert.executeAndReturnKey(params);
        assertNotNull(key);

        // 查询
        String selectSql = "SELECT * FROM users WHERE id = :id";
        Map<String, Object> result = jdbcTemplate.queryForMap(
                selectSql,
                new MapSqlParameterSource("id", key.longValue())
        );
        assertEquals("张三", result.get("name"));

        // 更新
        String updateSql = "UPDATE users SET age = :age WHERE id = :id";
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("id", key.longValue());
        updateParams.put("age", 26);
        int updateCount = jdbcTemplate.update(updateSql, updateParams);
        assertEquals(1, updateCount);

        // 删除
        String deleteSql = "DELETE FROM users WHERE id = :id";
        int deleteCount = jdbcTemplate.update(
                deleteSql,
                new MapSqlParameterSource("id", key.longValue())
        );
        assertEquals(1, deleteCount);
    }
}