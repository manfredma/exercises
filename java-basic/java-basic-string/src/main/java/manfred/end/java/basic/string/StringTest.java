package manfred.end.java.basic.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Manfred since 2019/3/19
 */
public class StringTest {

    /**
     * 测试下charAt
     */
    @Test
    public void charAt() {
        String a = "012345";
        Assert.assertTrue('1' == a.charAt(1));
    }
}
