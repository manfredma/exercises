package manfred.end.java.basic.time;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author manfred
 * @since 2020-03-17 12:20 下午
 */
public class DateTimeFormatterTest {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
        System.out.println(dateTimeFormatter.format(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now()), dateTimeFormatter)));

        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        System.out.println(f.format(new Date()));
    }
}
