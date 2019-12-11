package manfred.spring.boot.hello.world;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author manfred
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello1")
    public String hello1() {
        return "Hello World";
    }

    @RequestMapping("/hello2")
    public List<String> hello2() {
        return Arrays.asList("A", "B", "C");
    }

    @RequestMapping("/2")
    public List<String> hello3() {
        return Arrays.asList("A", "B", "C");
    }
}