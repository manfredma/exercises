package concurrent.thread;


import java.io.IOException;

/**
 * @author Manfred since 2019/6/12
 */
public class Boot {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(10000000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
            thread.start();
        }
        System.in.read();
    }
}
