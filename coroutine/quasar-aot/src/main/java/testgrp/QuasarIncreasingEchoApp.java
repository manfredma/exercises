package testgrp;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;
import co.paralleluniverse.strands.SuspendableRunnable;
import co.paralleluniverse.strands.channels.Channels;
import co.paralleluniverse.strands.channels.IntChannel;

import java.util.concurrent.ExecutionException;

/**
 * Increasing-Echo Quasar Example
 * 运行：mvn clean compile dependency:properties exec:exec
 * @author circlespainter
 */
public class QuasarIncreasingEchoApp {
    static public Integer doAll() throws ExecutionException, InterruptedException {
        final IntChannel increasingToEcho = Channels.newIntChannel(0); // Synchronizing channel
        // (buffer = 0)
        final IntChannel echoToIncreasing = Channels.newIntChannel(0); // Synchronizing channel
        // (buffer = 0)

        Fiber<Integer> increasing = new Fiber<>("INCREASER", new SuspendableCallable<Integer>() {
            @Override
            public Integer run() throws SuspendExecution, InterruptedException {
                ////// The following is enough to test instrumentation of synchronizing methods
                // synchronized(new Object()) {}

                int curr = 0;
                for (int i = 0; i < 10; i++) {
                    Fiber.sleep(10);
                    System.out.println("INCREASER sending: " + curr + "\t|| " + Thread.currentThread());
                    increasingToEcho.send(curr);
                    curr = echoToIncreasing.receive();
                    System.out.println("INCREASER received: " + curr + "\t|| " + Thread.currentThread());
                    curr++;
                    System.out.println("INCREASER now: " + curr + "\t|| " + Thread.currentThread());
                }
                System.out.println("INCREASER closing channel and exiting" + "\t|| " + Thread.currentThread());
                increasingToEcho.close();
                return curr;
            }
        }).start();

        Fiber<Void> echo = new Fiber<Void>("ECHO", new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                Integer curr;
                while (true) {
                    Fiber.sleep(1000);
                    curr = increasingToEcho.receive();
                    System.out.println("ECHO received: " + curr + "\t|| " + Thread.currentThread());

                    if (curr != null) {
                        System.out.println("ECHO sending: " + curr + "\t\t|| " + Thread.currentThread());
                        echoToIncreasing.send(curr);
                    } else {
                        System.out.println("ECHO detected closed channel, closing and exiting" + "\t|| " + Thread.currentThread());
                        echoToIncreasing.close();
                        return;
                    }
                }
            }
        }).start();

        try {
            increasing.join();
            echo.join();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return increasing.get();
    }

    static public void main(String[] args) throws ExecutionException, InterruptedException {
        doAll();
    }
}