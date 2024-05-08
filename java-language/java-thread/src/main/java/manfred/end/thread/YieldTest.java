package manfred.end.thread;

import java.util.concurrent.atomic.AtomicBoolean;

public class YieldTest implements Runnable {


    private static final AtomicBoolean LOCK = new AtomicBoolean(false);

    public void run() {
        while (!LOCK.get()) {
            // do nothing
        }
        System.out.println(Thread.currentThread().getName() + " begin--");
        for (int i = 0; i < 5; i++) {
            //当i=0时让出CPU执行权，放弃时间片，进行下一轮调度
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread().getName() + " yield cpu...");
                //当前线程让出CPU执行权，放弃时间片，进行下一轮调度
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is over");
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(null, new YieldTest(), "YieldTest-" + i);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        LOCK.getAndSet(true);
    }
}