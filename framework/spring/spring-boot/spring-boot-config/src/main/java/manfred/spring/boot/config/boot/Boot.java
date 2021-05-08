package manfred.spring.boot.config.boot;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;

public class Boot {

    private static final Logger LOGGER = getLogger(Boot.class);

    static {
        System.out.println("加载俺老孙干啥！");
    }

    public void sayHello() {
        System.out.println("sayHello！");
    }
}
