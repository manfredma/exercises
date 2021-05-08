package hard.coded.proxy;

import dao.IUserDao;

public class UserDaoProxy implements IUserDao {
    /**
     * 接收保存目标对象
     */
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务...1");
        //执行目标对象的方法
        target.save();
        System.out.println("提交事务...1");
    }

    @Override
    public void save2() {
        System.out.println("开始事务...2");
        //执行目标对象的方法
        target.save2();
        System.out.println("提交事务...2");
    }
}