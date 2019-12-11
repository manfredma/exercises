package manfred.spring.boot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author manfred
 */
@SpringBootApplication(scanBasePackages = {"manfred.spring.boot.config"})
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        System.out.println(applicationContext.getBean("a", A.class).getA());
        System.out.println(applicationContext.getBean("b", B.class).getB());
        System.out.println(applicationContext.getBean("c"));
    }

}