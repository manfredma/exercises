package manfred.end.java.basic;

public class Basic {
    public static void main(String[] args) {
        System.out.println(null + "");
        System.out.println((String) null);
        String s = null + "";
        System.out.println(s.length());

        // 三目运算符
        System.out.println(false ? 1 : Integer.valueOf(2));

        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Math.abs(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE);

    }
}
