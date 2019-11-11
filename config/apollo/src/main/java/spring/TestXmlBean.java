package spring;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author manfred
 * @since 2019-11-11 下午3:46
 */
@Data
public class TestXmlBean {

    private int timeout;

    private int batch;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
        TestXmlBean testXmlBean = context.getBean("testXmlBean", TestXmlBean.class);
        System.out.println(testXmlBean);
    }

}
