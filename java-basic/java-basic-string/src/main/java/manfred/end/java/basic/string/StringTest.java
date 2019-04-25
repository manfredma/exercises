package manfred.end.java.basic.string;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

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

    @Test
    public void reverse() {
        Assert.assertEquals("bxxxxxx", new StringBuffer("xxxxxxb").reverse().toString());
    }

    @Test
    public void encode() throws UnsupportedEncodingException {
        System.out.println("淡淡的蓝20106");
        System.out.println(new String("淡淡的蓝20106".getBytes("UTF-8"), "GBK"));
        System.out.println(new String("张Qq-_".getBytes("UTF-8"), "GBK"));
    }
}
