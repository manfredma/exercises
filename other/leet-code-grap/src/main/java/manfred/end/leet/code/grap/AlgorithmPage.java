package manfred.end.leet.code.grap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Manfred since 2019/8/28
 */
public class AlgorithmPage {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/rest/*.xml");
        RestTemplate template = applicationContext.getBean("restTemplate", RestTemplate.class);
        ResponseEntity<String> result = template.getForEntity("https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/", String.class, "");
        System.out.println(result.getBody());
    }
}
