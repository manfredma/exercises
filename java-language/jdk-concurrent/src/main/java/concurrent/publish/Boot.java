package concurrent.publish;

public class Boot {


    public static void main(String[] args) {
        Init init = new Init();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (; ; ) {
                    if (init.holder.n != 42 || init.holder.a != 42 || init.holder.j != 42) {
                        System.out.println("构造函数溢出了！");
                    }
                }
            }).start();
        }

        new Thread(() -> {
            for (; ; ) {
                init.initialize();
            }
        }).start();

    }
}
