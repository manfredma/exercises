package out.order;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


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
            try {
                cy.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            path1();
        });
        Thread t2 = new Thread(() -> {
            try {
                cy.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            path2();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return x == 0 && y == 0;
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            ThreadTest tt = new ThreadTest();
            boolean b = tt.test();
            if (b) {
                System.out.println("恭喜你！");
                break;
            }
        }
    }
}