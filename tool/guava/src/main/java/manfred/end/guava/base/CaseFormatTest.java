package manfred.end.guava.base;

import com.google.common.base.CaseFormat;
import org.junit.Test;

public class CaseFormatTest {

    @Test
    public void test() {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, "xxx-xxx"));
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "xxx-xxx"));
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, "xxx-xxx"));

        System.out.println(CaseFormat.LOWER_HYPHEN.converterTo(CaseFormat.UPPER_UNDERSCORE)
                .convert("xxx-xxx"));
    }
}
