package manfred.end.java.basic.string.intern;

public class Boot2 {
    public static void main(String[] args) {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        String s5 = s3.intern();
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s4 == s5);
    }
}
