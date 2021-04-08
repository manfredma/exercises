package manfred.end.guava.base;

import com.google.common.base.Ascii;
import org.junit.Test;

public class AsciiTest {

    @Test
    public void test() {
        System.out.println(Ascii.toLowerCase("XXX"));
        System.out.println(Ascii.toUpperCase("xxx"));
        System.out.println(Ascii.truncate("foobar", 7, "...")); // returns "foobar"
        System.out.println(Ascii.truncate("foobar", 5, "...")); // returns "fo..."
    }
}
