package com.manfred.parseq.study;

import static org.slf4j.LoggerFactory.getLogger;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.function.Tuple2;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;

public class IntegrateOutAsyncError {

    private static ExecutorService poolExecutor =
            new ThreadPoolExecutor(
                    20,
                    50,
                    0L, TimeUnit.MILLISECONDS,
                    new SynchronousQueue<>(),
                    new ThreadFactoryBuilder().setDaemon(false).setNameFormat("out-%d").build(),
                    new AsyncCallerRunsPolicy());

    private static ExecutorService enginePoolExecutor =
            new ThreadPoolExecutor(
                    20,
                    50,
                    0L, TimeUnit.MILLISECONDS,
                    new SynchronousQueue<>(),
                    new ThreadFactoryBuilder().setDaemon(false).setNameFormat("parseq-%d").build(),
                    new AsyncCallerRunsPolicy());

    private static final Logger LOGGER = getLogger(IntegrateOutAsyncError.class);

    public static void main(String[] args) throws IOException, InterruptedException {


        Engine engine = new EngineBuilder()
                .setTaskExecutor(enginePoolExecutor)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();
        Task<Tuple2<Object, Object>> task = createCompoundTask();
        engine.run(task, "xxx");
        task.await();
        LOGGER.info("任务整体执行完成");
        engine.shutdown();
        System.exit(0);
    }

    private static Task<Object> createTask(int id) {
        Task<Object> task = Task.async("Task " + id, () ->
        {
            throw new RuntimeException("xxx");
        });
        return task;
    }

    private static Task<Tuple2<Object, Object>> createCompoundTask() {
        return Task.par(createTask(0), createTask(1));
    }

    public static class AsyncCallerRunsPolicy implements RejectedExecutionHandler {

        private static final Logger LOGGER = getLogger(AsyncCallerRunsPolicy.class);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            LOGGER.error("异步执行线程池任务执行已经被打满，请检查当前队列任务数据线程数据情况r={}, executor={}", r.toString(),
                    executor.toString());
            throw new RuntimeException("访问页面失败，请重试");
        }

    }
}
