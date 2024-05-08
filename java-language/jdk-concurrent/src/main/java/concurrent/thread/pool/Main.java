package concurrent.thread.pool;

import java.util.concurrent.*;

/**
 * @author Manfred since 2019/6/12
 */
public class Main {
    public static void main(String[] args) {
        int tn = 200;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 100, 1L, TimeUnit.MINUTES, new SynchronousQueue());
        CountDownLatch latch = new CountDownLatch(tn);
        for (int j = 0; j < tn; j++) {
            Runnable s = () -> {
                latch.countDown();
                for (int i = 0; i < 10; i++) {
                    try {

                        Future f = executor.submit(() -> {
                                    try {
                                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                                    } catch (InterruptedException e) {
                                        // do nothing
                                    }
                                }
                        );
                        System.out.println(f.getClass());
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                }
            };
            new Thread(s).start();
        }
    }
}
