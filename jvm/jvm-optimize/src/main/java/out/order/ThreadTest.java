package out.order;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;


public class ThreadTest {
    int a = 0;
    int b = 0;
    int x = -1;
    int y = -1;

    public void path1() {
        a = 1;
        x = b;
    }

    public void path2() {
        b = 2;
        y = a;
    }

    public boolean test() throws InterruptedException {
        a = b = 0;
        x = y = -1;
        CyclicBarrier cy = new CyclicBarrier(2);
        Thread t1 = new Thread(() -> {
            shortWait(100000);
            path1();
        });
        Thread t2 = new Thread(this::path2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return x == 0 && y == 0;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            int index = atomicInteger.addAndGet(1);
            ThreadTest tt = new ThreadTest();
            boolean b = tt.test();
            if (b) {
                System.out.println("出现了指令重排的现象！共执行了 " + index + " 次！");
                break;
            }
        }
    }

    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}