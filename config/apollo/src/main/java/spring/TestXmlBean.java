package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author manfred
 * @since 2019-11-11 下午3:46
 */
public class TestXmlBean {

    private int timeout;

    private int batch;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
        TestXmlBean testXmlBean = context.getBean("testXmlBean", TestXmlBean.class);
        System.out.println(testXmlBean);
    }


    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "TestXmlBean{" +
                "timeout=" + timeout +
                ", batch=" + batch +
                '}';
    }
}
