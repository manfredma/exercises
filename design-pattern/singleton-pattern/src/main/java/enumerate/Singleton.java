package enumerate;

/**
 * @author Manfred since 2019/8/14
 */
public enum Singleton {
    INSTANCE;

    public void showMessage() {
        System.out.println("Hello World![enum]");
    }
}