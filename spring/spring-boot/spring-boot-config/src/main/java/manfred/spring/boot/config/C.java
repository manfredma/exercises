package manfred.spring.boot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author manfred
 * @since 2019-12-11 下午5:21
 */
@Component
@Data
public class C {

    @Value("${c}")
    private int c1;
    @Value("${c.c2}")
    private String c2;

}
