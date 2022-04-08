package manfred.end.r2dbc;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
                .host("127.0.0.1")
                .port(3306)
                .username("coupon")
                .password("coupon")
                .database("manfred")
                // 额外的其它非必选参数省略
                .build());
    }
}