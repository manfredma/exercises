package manfred.spring.cloud.resilience.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationContext;

/**
 * @author manfred
 * @since 2019-12-16 下午3:00
 */
@SpringBootApplication(scanBasePackages = {"manfred.spring.cloud.resilience.hystrix"})
@EnableHystrix
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        HelloServiceConsumer helloServiceConsumer = applicationContext.getBean(HelloServiceConsumer.class);
        int successCount = 0;
        int fail = 0;
        for (int i = 0; i < 100; i++) {
            try {
                String x = helloServiceConsumer.doSayHello("xxx");
                if ("hystrix fallback value".equals(x)) {
                    fail++;
                } else {
                    successCount++;
                }
                System.out.println("t=" + i +", s=" + successCount + ", f=" + fail + ": " + helloServiceConsumer.doSayHello("xxx"));
            } catch (Exception e) {
                System.out.println(i + " : " + e);
            }
        }
    }
}
