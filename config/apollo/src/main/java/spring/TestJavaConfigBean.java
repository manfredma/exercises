package spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author manfred
 * @since 2019-11-11 下午3:58
 */
@Data
public class TestJavaConfigBean {

    @Value("${timeout:100}")
    private int timeout;

    @Value("${batch:200}")
    private int batch;
}
