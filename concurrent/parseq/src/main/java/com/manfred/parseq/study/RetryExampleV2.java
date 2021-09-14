package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.ParTask;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

public class RetryExampleV2 {

    private static List<Task> taskList = new ArrayList<>();

    private static ExecutorService outPool =
            new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                    new ThreadFactoryBuilder().setDaemon(false)
                            .setNameFormat("out-%d")
                            .build(), new AsyncCallerRunsPolicy());

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryExampleV2.class);

    private static int x = 1;

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService enginePool =
                new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                        new ThreadFactoryBuilder().setDaemon(false)
                                .setNameFormat("parseq-%d")
                                .build(), new AsyncCallerRunsPolicy());

        Engine engine = new EngineBuilder().setTaskExecutor(enginePool)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();
        Task task = createRetryTask();
        engine.run(task, "xxx");
        task.await();
        engine.shutdown();
        System.exit(0);
    }

    private static Task<Object> createAsyncTask(String desc, boolean isException) {
        Task<Object> task = Task.async("Task " + desc, () -> {
            SettablePromise<Object> promise = Promises.settable();
            outPool.execute(() -> {
                LOGGER.info("id = {} 开始在外部线程池执行", desc);
                if (isException) {
                    promise.fail(new RuntimeException(desc));
                } else {
                    promise.done("Task " + desc + " result!!!");
                }
                LOGGER.info("id = {} 执行完成", desc);
            });
            return promise;
        });
        taskList.add(task);
        return task;
    }

    private static Task createRetryTask() {
        Function<Throwable, ErrorClassification> errorClassifier =
                error -> error instanceof Exception ? ErrorClassification.RECOVERABLE
                        : ErrorClassification.UNRECOVERABLE;

        RetryPolicy retryPolicy = new RetryPolicyBuilder()
                .setTerminationPolicy(TerminationPolicy.limitAttempts(20))
                .setErrorClassifier(errorClassifier)
                .build();

        Task<Object> re = Task.withRetryPolicy(retryPolicy, () -> {
            Task<Object> result = createAsyncTask("retry-" + x++ + "", true);
            return result;
        }).map("map-print-1", (x) -> {
            LOGGER.info("输出一下结果, {}", x);
            return x;
        });
        Task<Object> x2 = createAsyncTask("0", false).map("map-print-2", (x) -> {
            LOGGER.info("输出一下结果, {}", x);
            return x;
        });
        ParTask<Object> task = Task.par(Arrays.asList(re, x2));
        taskList.add(task);
        return task;
    }

    public static class AsyncCallerRunsPolicy implements RejectedExecutionHandler {

        private static final Logger LOGGER = LoggerFactory.getLogger(AsyncCallerRunsPolicy.class);

        private static int i = 0;

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            LOGGER.error("异步执行线程池任务执行已经被打满，请检查当前队列任务数据线程数据情况r={}, executor={}", r.toString(),
                    executor.toString());
            throw new RuntimeException("访问页面失败，请重试 " + i++);
        }

    }
}
