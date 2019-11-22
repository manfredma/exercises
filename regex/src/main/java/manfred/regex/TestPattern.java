package manfred.regex;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author manfred
 * @since 2019-11-13 下午7:51
 */
public class TestPattern {
    public static Pattern pattern = Pattern.compile(".*[a-zA-Z]+.*");
    public static void main(String[] args) {
        System.out.println(pattern.matcher("1233a4312").find());
        System.out.println(pattern.matcher("aa4312").find());
        System.out.println(pattern.matcher("aa").find());
        System.out.println(pattern.matcher("132123412").find());

        String x = "1x_23_33";
        System.out.println(Arrays.toString(x.split("_")));


        String[] split = "222?222".split("\\?");
        System.out.println(Arrays.toString(split));
    }
}
