package cglib.proxy;

import dao.IUserDao;
import dao.UserDao;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author Manfred since 2019/9/30
 */
public class App {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".");
        IUserDao userDao = (IUserDao) new ProxyFactory(UserDao.class).getProxyInstance();
        userDao.save();
    }
}
