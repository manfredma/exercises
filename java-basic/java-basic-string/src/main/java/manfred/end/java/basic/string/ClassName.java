package manfred.end.java.basic.string;

import java.util.Scanner;

/**
 * @author Manfred since 2019/8/28
 */
public class ClassName {
    public static String generateNameWithMain(String s, String prefix) {
        s = s.toLowerCase();
        s = s.replace(" ", ".");
        s = s.replace("..", ".");
        s = s + ".Main";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i)) || '.' == (s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        s = sb.toString();
        s = prefix + s;
        return s;
    }

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            while (true) {
                System.out.println(generateNameWithMain(s.nextLine(), "exe"));
            }
        } catch (Exception e) {

        }
    }
}
