package boot;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author manfred
 * @since 2019-11-11 下午4:29
 */
@Component
@Data
@Slf4j
public class TestSpringBootConfigBean implements InitializingBean {

    @Value("${timeout:100}")
    private int timeout;

    @Value("${batch:200}")
    private int batch;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.error("TestSpringBootConfigBean initialized " + this);
    }
}
