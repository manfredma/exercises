package manfred.end.spring.boot.tomcat.controller;

import javax.validation.Valid;
import manfred.end.spring.boot.tomcat.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/index2")
    public String index2(@Valid @RequestBody User user) {
        return "Greetings from Spring Boot! (" + user.toString() + ")";
    }

}