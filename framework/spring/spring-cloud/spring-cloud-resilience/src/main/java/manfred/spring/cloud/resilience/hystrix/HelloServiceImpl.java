package manfred.spring.cloud.resilience.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class HelloServiceImpl implements HelloService {

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // @HystrixProperty(name = "circuitBreaker.forceOpen", value = "true"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200")})
    @Override
    public String sayHello(String name) {
        System.out.println("async provider received: " + name);
        int x = ThreadLocalRandom.current().nextInt(400);
        try {
            TimeUnit.MILLISECONDS.sleep(x);
        } catch (InterruptedException e) {
            // e.printStackTrace();
            System.out.println("sleep interrupted!");
        }
        // throw new RuntimeException("Exception to show hystrix enabled.");
        return "hello, " + name;
    }
}