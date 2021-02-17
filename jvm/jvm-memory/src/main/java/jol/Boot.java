package jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

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
    }

}

class X {

    private int x = 2;

    private float y = 3.0F;
}
