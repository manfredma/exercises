import java.time.Instant;
import java.time.ZoneId;

/**
 * @author Manfred since 2019/9/27
 */
public class TimeTest {
    public static void main(String[] args) {
        Instant instant = Instant.ofEpochMilli(1569344399000L);
        System.out.println(instant.atZone(ZoneId.of("GMT+8")));
        System.out.println(instant.atZone(ZoneId.of("GMT+7")));
    }
}
