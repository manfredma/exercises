package jol;

import static java.lang.System.out;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

public class ObjectLayout {

    public static void main(String[] args) {
        out.println(VM.current().details());
        String ins = "哈哈哈哈哈哈哈哈";
        out.println(ClassLayout.parseInstance(String.class).toPrintable());
        out.println(ClassLayout.parseInstance(X.class).toPrintable());
        out.println(ClassLayout.parseInstance(ins).toPrintable());

        X x = new X();
        out.println(ClassLayout.parseInstance(x).toPrintable());
        out.println("current address of String.class: " + VM.current().addressOf(String.class));
        out.println("current address of X.class: " + VM.current().addressOf(X.class));
        out.println("hash is " + Integer.toHexString(System.identityHashCode(x)));
        out.println(ClassLayout.parseInstance(x).toPrintable());


        out.println(ClassLayout.parseInstance(new int[40]).toPrintable());
        out.println(ClassLayout.parseInstance(new Object[40]).toPrintable());
    }
}

