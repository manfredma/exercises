package manfred.end.java.basic.time;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author manfred
 * @since 2020-04-21 11:09 上午
 */
public class LocalDateTest {
    public static void main(String[] args) {
        System.out.println(LocalDate.parse("2020-02-02"));
        System.out.println(LocalDate.parse("2020-02-02"));

        System.out.println(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(LocalDate.now().atStartOfDay());
    }
}
