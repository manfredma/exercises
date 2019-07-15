/*
-XX:+PrintCompilation // 在控制台打印编译过程信息
-XX:+UnlockDiagnosticVMOptions // 解锁对 JVM 进行诊断的选项参数。默认是关闭的，开启后支持一些特定参数对 JVM 进行诊断
-XX:+PrintInlining // 将内联方法打印出来

-XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
 */
package inline;

/**
 * @author Manfred since 2019/7/11
 */
public class Main {
    private int add1(int x1, int x2, int x3, int x4) {
        return add2(x1, x2) + add2(x3, x4);
    }

    private int add2(int x1, int x2) {
        return x1 + x2;
    }

    public static void main(String[] args) {
        Main main = new Main();

        for (int i = 0; i < 1000_000; i++) {
            main.add1(1, 2, 3, 4);
        }
    }
}
