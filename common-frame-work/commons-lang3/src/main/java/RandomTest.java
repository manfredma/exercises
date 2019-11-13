import org.apache.commons.lang3.RandomUtils;

/**
 * @author manfred
 * @since 2019-11-13 下午4:03
 */
public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int r = RandomUtils.nextInt(1, 10);
            if (r == 10) {
                System.out.println("出现了10");
            } else if (r == 1) {
                System.out.println("出现了1");
            }
        }
    }
}
