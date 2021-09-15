package concurrent.lock.aqs;

import java.util.concurrent.TimeUnit;

public class LeeMain {

    static int count = 0;
    static LeeLock leeLock = new LeeLock();

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            try {
                leeLock.lock();
                System.out.println(Thread.currentThread() + "开始执行");
                TimeUnit.SECONDS.sleep(1L);
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
                System.out.println(Thread.currentThread() + "执行结束");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                leeLock.unlock();
            }

        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}