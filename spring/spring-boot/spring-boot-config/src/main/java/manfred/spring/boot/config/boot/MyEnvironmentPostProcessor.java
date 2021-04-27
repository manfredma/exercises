package manfred.spring.boot.config.boot;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final Logger LOGGER = getLogger(MyEnvironmentPostProcessor.class);

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment,
            SpringApplication application) {

        System.out.printf("enviroment=%s, application=%s\n", environment, application);

        LOGGER.info("enviroment={}, application={}", environment, application);
    }
}
