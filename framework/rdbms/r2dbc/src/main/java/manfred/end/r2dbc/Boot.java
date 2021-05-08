package manfred.end.r2dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.felord.r2dbc.config", "manfred.end.r2dbc"})
public class Boot {

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

}
