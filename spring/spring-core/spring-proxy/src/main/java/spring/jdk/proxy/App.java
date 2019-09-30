package spring.jdk.proxy;

import dao.IUserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Manfred since 2019/9/30
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/jdk/beans.xml");
        IUserDao userDao = context.getBean("userDao", IUserDao.class);
        userDao.save();
    }
}
