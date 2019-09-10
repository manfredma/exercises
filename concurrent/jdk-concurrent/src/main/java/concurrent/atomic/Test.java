package concurrent.atomic;

/**
 * @author Manfred since 2019/4/16
 */
public class Test {

    @org.junit.Test
    public void testSafeWM() {
        SafeWM safeWM = new SafeWM();
        safeWM.setUpper(10);
    }
}
