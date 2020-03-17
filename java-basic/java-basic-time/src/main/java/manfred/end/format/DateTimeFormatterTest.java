package manfred.end.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author manfred
 * @since 2020-03-17 12:20 下午
 */
public class DateTimeFormatterTest {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
        System.out.println(dateTimeFormatter.format(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now()), dateTimeFormatter)));
    }
}
