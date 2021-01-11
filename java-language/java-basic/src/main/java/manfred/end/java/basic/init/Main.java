package manfred.end.java.basic.init;

/**
 * @author Manfred since 2019/7/11
 */
public class Main {

    static {
        i = 11;
    }

    private static int i = 20;

    static {
        System.out.println("before invoke static block, i = " + i);
        i = 10;
        System.out.println("after invoke static block, i = " + i);
    }

    public static void main(String[] args) {
        System.out.println("main - i = " + i);
    }

}
