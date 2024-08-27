package manfred.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author manfred
 * @since 2019-12-11 下午4:59
 */
@ConfigurationProperties(prefix = "b")
@Configuration
public class B {
    private int b;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "B{" +
                "b=" + b +
                '}';
    }
}
