package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.promise.Promises;
import com.linkedin.parseq.promise.SettablePromise;
import com.linkedin.parseq.trace.TraceUtil;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.slf4j.LoggerFactory.getLogger;

public class IntegrateOutAsync2 {

    private static List<Task> taskList = new ArrayList<>();

    private static ExecutorService poolExecutor =
            new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                    new ThreadFactoryBuilder().setDaemon(false)
                            .setNameFormat("out-%d")
                            .build(), new AsyncCallerRunsPolicy());

    private static final Logger LOGGER = getLogger(IntegrateOutAsync2.class);

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
        String trace = TraceUtil.getJsonTrace(task);
        System.out.println(trace);
        LOGGER.info("任务整体执行完成");
        engine.shutdown();
        System.exit(0);
    }

    private static Task<Object> createTask(int id) {
        Task<Object> task = Task.async("Task " + id, () ->
        {
            SettablePromise<Object> promise = Promises.settable();
            poolExecutor.submit(() -> {
                Thread.sleep(id * 100 + 1000L);
                LOGGER.info("Task id = {} 执行完成", id);
                promise.done("Task " + id + " done!!!");
                return null;
            });

            return promise;
        }).andThen("Task after " + id, (x) -> {
            LOGGER.info("Task after " + id + " done!!!");
        });
        taskList.add(task);
        return task;
    }

    private static Task createCompoundTask() {
        Task task = Task.par(createTask(0), createTask(1));
        taskList.add(task);
        return task;
    }

    public static class AsyncCallerRunsPolicy implements RejectedExecutionHandler {

        private static final Logger LOGGER = getLogger(AsyncCallerRunsPolicy.class);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            LOGGER.error("异步执行线程池任务执行已经被打满，请检查当前队列任务数据线程数据情况r={}, executor={}", r.toString(), executor.toString());
            throw new RuntimeException("访问页面失败，请重试");
        }

    }
}
