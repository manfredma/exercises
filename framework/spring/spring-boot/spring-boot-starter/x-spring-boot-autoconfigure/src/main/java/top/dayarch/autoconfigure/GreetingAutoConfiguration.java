package top.dayarch.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "rgyb.greeting.enable", havingValue = "true")
@ConditionalOnClass(DummyEmail.class)
@EnableConfigurationProperties({GreetingProperties.class})
public class GreetingAutoConfiguration {

    @Bean
    public GreetingService greetingService(GreetingProperties greetingProperties) {
        return new GreetingService(greetingProperties.getMembers());
    }
}