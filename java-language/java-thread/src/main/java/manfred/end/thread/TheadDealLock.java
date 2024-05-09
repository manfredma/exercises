package manfred.end.thread;

import java.util.concurrent.atomic.AtomicReference;

public class TheadDealLock {
    /**
     * 测试Suspend Resume 死锁
     * <p>
     */
    public static void main(String[] args) {
        AtomicReference<String> message = new AtomicReference<>();
        Thread consumer = new Thread(() -> {
            while (true) {
                synchronized (TheadDealLock.class) {
                    while (message.get() == null) {
                        System.out.println("等待接受消息");
                        Thread.currentThread().suspend();
                    }
                    System.out.println("接受消息 => " + message);
                }
            }
        }, "C-----------------------------------");
        consumer.start();

        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (TheadDealLock.class) {
                    message.set("Hello , this is Producer");
                    consumer.resume();

                }
            }
        }, "P-----------------------------------");
        producer.start();
    }


}
