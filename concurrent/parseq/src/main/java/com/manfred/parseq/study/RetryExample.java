package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.promise.Promises;
import com.linkedin.parseq.promise.SettablePromise;
import com.linkedin.parseq.retry.ErrorClassification;
import com.linkedin.parseq.retry.RetryPolicy;
import com.linkedin.parseq.retry.RetryPolicyBuilder;
import com.linkedin.parseq.retry.termination.TerminationPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

public class RetryExample {

    private static List<Task> taskList = new ArrayList<>();

    private static ExecutorService poolExecutor =
            new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                    new ThreadFactoryBuilder().setDaemon(false)
                            .setNameFormat("out-%d")
                            .build(), new AsyncCallerRunsPolicy());

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryExample.class);


    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService poolExecutor =
                new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                        new ThreadFactoryBuilder().setDaemon(false)
                                .setNameFormat("parseq-%d")
                                .build(), new AsyncCallerRunsPolicy());

        Engine engine = new EngineBuilder().setTaskExecutor(poolExecutor)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();
        Task task = createCompoundTask();
        engine.run(task, "xxx");
        task.await();
        engine.shutdown();
        System.exit(0);
    }

    static int successWhen5 = 1;

    private static Task<Object> createTask(int id) {
        Task<Object> task = Task.async("Task " + id, () -> {
            SettablePromise<Object> promise = Promises.settable();
            poolExecutor.execute(() -> {
                try {
                    Thread.sleep(id + 10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info("id = {} 开始执行", id);

                if (!(successWhen5 % 5 == 0) && id == 20) {
                    // LOGGER.info("id = {} 执行失败", id);
                    successWhen5++;
                    Exception exception = new RuntimeException();
                    promise.fail(exception);
                } else if (id == 20) {
                    // LOGGER.info("id = {} 执行成功", id);
                    successWhen5++;
                    promise.done("Task " + id + " done!!!");
                } else {
                    // LOGGER.info("id = {} 执行成功", id);
                    promise.done("Task " + id + " done!!!");
                }
                LOGGER.info("id = {} 执行完成", id);
                // return null;
            });
            return promise;
        });
        taskList.add(task);
        return task;
    }

    private static Task createCompoundTask() {
        Function<Throwable, ErrorClassification> errorClassifier =
                error -> error instanceof Exception ? ErrorClassification.RECOVERABLE
                        : ErrorClassification.UNRECOVERABLE;

        RetryPolicy retryPolicy = new RetryPolicyBuilder()
                .setTerminationPolicy(TerminationPolicy.limitAttempts(2))
                // .setBackoffPolicy()
                .setErrorClassifier(errorClassifier)
                .build();

        Task re = Task.withRetryPolicy(retryPolicy, () -> {
            LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Task<Object> result = createTask(20);
            return result;
        }).map("mapRe", (x) -> {
            LOGGER.info("输出一下结果, {}", x);
            return x;
        });
        Task x2 = createTask(0).map("mapX2", (x) -> {
            LOGGER.info("输出一下结果, {}", x);
            return x;
        });
        Task task = Task.par(re, x2);
        taskList.add(task);
        return task;
    }

    public static class AsyncCallerRunsPolicy implements RejectedExecutionHandler {

        private static final Logger LOGGER = LoggerFactory.getLogger(AsyncCallerRunsPolicy.class);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            LOGGER.error("异步执行线程池任务执行已经被打满，请检查当前队列任务数据线程数据情况r={}, executor={}", r.toString(), executor.toString());
            throw new RuntimeException("访问页面失败，请重试");
        }

    }
}
