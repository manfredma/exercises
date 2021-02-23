package spring.cglib.proxy;

import dao.IUserDao;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Manfred since 2019/9/30
 */
public class App {
    public static void main(String[] args) {
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/cglib/beans.xml");
        IUserDao userDao = context.getBean("userDao", IUserDao.class);
        userDao.save();
    }
}
