package manfred.end.java.basic.string;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

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
        System.out.println(new String("金戈小虔".getBytes("UTF-8"), "GBK"));
    }

    @Test
    public void split() {
        String aString = "ad_x_2";
        String[] splitResult = aString.split("_");
        for (int i = 0; i < splitResult.length; i++) {
            System.out.print(splitResult[i] + " ");
        }
        System.out.println();

        aString = "fja;f?df;akf?:kjfas;lf'";
        splitResult = aString.split("\\?");
        System.out.println(Arrays.toString(splitResult));
    }

    @Test
    public void plus() {
        System.out.println("xxx" + null + "zzz");
    }

    @Test
    public void subString() {
        String string = "ur1092345780";
        System.out.println(string.substring(string.length() - 3));
    }

    @Test
    public void replace() {

        String st = "\\\\";
        System.out.println(st.replace("\\\\", "!!!!!￥￥￥￥￥$$$!!!!")
                .replace("\\", "").replace("!!!!!￥￥￥￥￥$$$!!!!", "\\"));
        st = "\\hellofasjf\\flkaj\\";
        System.out.println(st.replace("\\\\", "!!!!!￥￥￥￥￥$$$!!!!")
                .replace("\\", "").replace("!!!!!￥￥￥￥￥$$$!!!!", "\\"));
        st = "\\\"";
        System.out.println(st.replace("\\\\", "!!!!!￥￥￥￥￥$$$!!!!")
                .replace("\\", "").replace("!!!!!￥￥￥￥￥$$$!!!!", "\\"));
    }

}
