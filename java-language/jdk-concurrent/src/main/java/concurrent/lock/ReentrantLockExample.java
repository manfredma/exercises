package concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final ReentrantLockExample t = new ReentrantLockExample();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }

    void m1() {
        try {
            lock.lock(); // 加锁
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1() method " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }
    }

    void m2() {
        lock.lock();
        System.out.println("m2() method");
        lock.unlock();
    }
}