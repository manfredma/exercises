package manfred.spring.boot.config;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author manfred
 */
@SpringBootApplication(scanBasePackages = {"manfred.spring.boot.config.**"})
public class Application {

    private static final Logger LOGGER = getLogger(Application.class);
    private static final Logger LOGGER_V2 =
            LoggerFactory.getILoggerFactory().getLogger(Application.class.getName());

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        LOGGER.info("V1_" + applicationContext.getBean("a", A.class));
        LOGGER.info("V1_" + applicationContext.getBean("b", B.class));
        LOGGER.info("V1_" + applicationContext.getBean("c") + "");

        LOGGER_V2.info("V2_" + applicationContext.getBean("a", A.class));
        LOGGER_V2.info("V2_" + applicationContext.getBean("b", B.class));
        LOGGER_V2.info("V2_" + applicationContext.getBean("c"));
    }

}