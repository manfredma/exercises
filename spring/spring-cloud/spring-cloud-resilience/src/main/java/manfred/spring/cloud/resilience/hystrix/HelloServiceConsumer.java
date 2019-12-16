package manfred.spring.cloud.resilience.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author manfred
 * @since 2019-12-16 下午3:03
 */
@Service
public class HelloServiceConsumer {

    @Resource
    private HelloService helloService;

    @HystrixCommand(fallbackMethod = "reliable")
    public String doSayHello(String name) {
        return helloService.sayHello(name);
    }
    public String reliable(String name) {
        return "hystrix fallback value";
    }

}
