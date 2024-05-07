package com.manfred.parseq.study;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.promise.Promises;
import com.linkedin.parseq.promise.SettablePromise;
import com.linkedin.parseq.retry.RetryPolicy;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryExampleV3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryExampleV3.class);

    private static ListeningExecutorService listeningExecutorService =
            MoreExecutors.listeningDecorator(
                    new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS,
                            new SynchronousQueue<>(),
                            new ThreadFactoryBuilder().setDaemon(false).setNameFormat("out-%d").build()));


    public static void main(String[] args) throws IOException, InterruptedException {

        ThreadFactory serialExecutorThreadFactory
                = new ThreadFactoryBuilder().setDaemon(false).setNameFormat("executor-%d").build();
        ExecutorService enginePool = new ThreadPoolExecutor(
                20, 50,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(), serialExecutorThreadFactory);

        ThreadFactory timeoutExecutorThreadFactory
                = new ThreadFactoryBuilder().setDaemon(false).setNameFormat("timeout-%d").build();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(20,
                timeoutExecutorThreadFactory);

        Engine engine = new EngineBuilder().setTaskExecutor(enginePool)
                .setTimerScheduler(scheduledExecutorService)
                .build();

        Task<String> task = createRetryTask();
        engine.run(task, "xxx");
        task.await();
        engine.shutdown();
        System.exit(0);
    }

    private static Task<String> createAsyncTask(String desc) {
        Task<String> task = Task.async("Task " + desc, () -> {
            ListenableFuture<String> listenableFuture = listeningExecutorService.submit(() -> {
                LOGGER.info("{} 开始在外部线程池执行", desc);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (new Random().nextBoolean()) {
                    LOGGER.info("{} 执行失败 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", desc);
                    throw new RuntimeException();
                }
                LOGGER.info("{} 执行完成 VVVVVVVVVVVVVVVVVVVVVVVVVVVVVV", desc);
                return "done!";
            });
            SettablePromise<String> promise = Promises.settable();
            listenableFuture.addListener(() -> {
                try {
                    promise.done(listenableFuture.get());
                } catch (Throwable e) {
                    promise.fail(e);
                }
            }, MoreExecutors.directExecutor());
            return promise;
        });
        return task;
    }

    private static Task<String> createRetryTask() {

        RetryPolicy retryPolicy = RetryPolicy.attempts(2, 10);

        return Task.withRetryPolicy(
                retryPolicy,
                (x) -> {
                    LOGGER.info("第 {} 次重试", x);
                    return createAsyncTask("retry----------");
                }
        );
    }
}
