package manfred.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author manfred
 * @since 2019-12-11 下午4:57
 */
@ConfigurationProperties(prefix = "a")
@Component
public class A {

    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                '}';
    }
}
