package manfred.end.springboot.starter.sample;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import top.dayarch.autoconfigure.GreetingService;

import javax.annotation.Resource;

@Component
public class Test implements InitializingBean {
    @Resource
    private GreetingService greetingService;


    @Override
    public void afterPropertiesSet() throws Exception {
        greetingService.sayHello();
    }
}
