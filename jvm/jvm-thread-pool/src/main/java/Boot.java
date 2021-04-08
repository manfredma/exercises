import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Boot {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                30,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                Thread::new,
                (r, executor) -> System.out.println("进入 reject ！ 该怎么办？")
        );

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(() -> {
                System.out.println("sleep 1s");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
