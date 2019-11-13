import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Manfred since 2019/9/27
 */
public class TimeTest {
    public static void main(String[] args) {
        Instant instant = Instant.ofEpochMilli(1569344399000L);
        System.out.println(instant.atZone(ZoneId.of("GMT+8")));
        System.out.println(instant.atZone(ZoneId.of("GMT+7")));


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        System.out.println(simpleDateFormat.format(new Date()));

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        localDateTime = localDateTime.minusDays(10);
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
}
