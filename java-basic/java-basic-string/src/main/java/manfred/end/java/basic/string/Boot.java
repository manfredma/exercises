package manfred.end.java.basic.string;

/**
 * @author Manfred since 2019/3/19
 */
public class Boot {
    public static void main(String[] args) {
        String a = "012345";
        for (int i = 0; i < a.length(); i++) {
            System.out.println('0' == a.charAt(i));
        }
    }
}
