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

        System.out.printf("\n MyEnvironmentPostProcessor: enviroment=%s, application=%s\n", environment, application);

        // 这行无法输出是因为spring在启动的时候增加了过滤器，过略掉所有的日志输出！
        // LoggingApplicationListener.java
        // 	private void onApplicationStartingEvent(ApplicationStartingEvent event) {
        //		this.loggingSystem = LoggingSystem.get(event.getSpringApplication().getClassLoader
        //		());
        //		this.loggingSystem.beforeInitialize();
        //	}
        LOGGER.info("enviroment={}, application={}", environment, application);
        LOGGER.error("enviroment={}, application={}", environment, application);
    }
}
