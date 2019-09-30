package cglib.proxy;

import dao.IUserDao;
import dao.UserDao;

/**
 * @author Manfred since 2019/9/30
 */
public class App {
    public static void main(String[] args) {
        IUserDao userDao = (IUserDao) new ProxyFactory(new UserDao()).getProxyInstance();
        userDao.save();
    }
}
