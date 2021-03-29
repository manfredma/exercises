package jol;

import static java.lang.System.out;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.util.Scanner;

/**
 * @author manfred
 * @since 2019-12-17 下午1:51
 */
public class Boot {

    public static void main(String[] args) {
        out.println(VM.current().details());
        String ins = "哈哈哈哈哈哈哈哈";
        out.println(ClassLayout.parseClass(java.lang.String.class).toPrintable());
        out.println(ClassLayout.parseInstance(ins).toPrintable());

        X x = new X();
        out.println(ClassLayout.parseClass(X.class).toPrintable());
        out.println(ClassLayout.parseInstance(x).toPrintable());
        out.println(ClassLayout.parseInstance(X.class).toPrintable());
        out.println(ClassLayout.parseInstance((short) 2).toPrintable());
        out.println(ClassLayout.parseInstance(2).toPrintable());
        out.println(ClassLayout.parseInstance(2L).toPrintable());
        out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        out.println(ClassLayout.parseInstance(new int[3]).toPrintable());

        out.println(GraphLayout.parseInstance(x).toPrintable());
        out.println(GraphLayout.parseInstance(X.class).toPrintable());
        int[] ia = new int[40];
        byte[] ba = new byte[40];
        boolean[] bla = new boolean[40];
        out.println(GraphLayout.parseInstance((Object) ia).toPrintable());
        out.println(GraphLayout.parseInstance((Object) ba).toPrintable());
        out.println(GraphLayout.parseInstance((Object) bla).toPrintable());
        out.println("请输入任意字符结束程序...");
        new Scanner(System.in).next();
    }

}

class X {

    private int x = 2;

    private float y = 3.0F;
}
