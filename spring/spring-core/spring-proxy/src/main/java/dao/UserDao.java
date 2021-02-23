package dao;

public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println(
                this.getClass() + " - " + System.identityHashCode(this) + ": ----已经保存数据!----1");
        save2();
    }

    @Override
    public void save2() {
        System.out.println(
                this.getClass() + " - " + System.identityHashCode(this) + ": ----已经保存数据!----2");
    }
}