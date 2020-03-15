import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author manfred
 * @since 2019-11-12 下午8:31
 */
public class NumberTest {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
        System.out.println(String.valueOf(Integer.MAX_VALUE).length());
        AtomicInteger atomicInteger = new AtomicInteger(Integer.MAX_VALUE);
        System.out.println(atomicInteger.incrementAndGet());
    }
}
