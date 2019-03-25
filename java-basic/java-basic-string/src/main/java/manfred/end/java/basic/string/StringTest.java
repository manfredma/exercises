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
        Assert.assertEquals('1', a.charAt(1));
    }

    @Test
    public void stringOperator() {
        Object a = new Object() {
            @Override
            public String toString() {
                return "parent.toString";
            }
        } + "~~xxx";
        Assert.assertEquals(String.class, a.getClass());
        Assert.assertEquals("parent.toString~~xxx", a.toString());
    }
}
