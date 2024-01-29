package manfred.end.thread;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Boot {
    public static void main(String[] args) {
        System.out.println("父线程 Begin" + Instant.now().toString());

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("子线程 Begin" + Instant.now().toString());
            xxx();
            System.out.println("子线程 End" + Instant.now().toString());
        }).start();
        xxx();
        System.out.println("父线程 End" + Instant.now().toString());
    }


    public synchronized static void xxx() {
        System.out.println("xxx begin +++");
        long begin = System.currentTimeMillis();
        long x = 0;
        while (System.currentTimeMillis() - begin < 10000) {
            x++;
        }
        System.out.println("xxx end +++");
    }

}
