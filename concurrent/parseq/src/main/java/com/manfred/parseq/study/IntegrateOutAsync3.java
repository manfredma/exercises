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
import java.util.concurrent.*;

import static org.slf4j.LoggerFactory.getLogger;

public class IntegrateOutAsync3 {

    private static ExecutorService outExecutor =
            new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                    new ThreadFactoryBuilder().setDaemon(false)
                            .setNameFormat("out-%d")
                            .build(), new AsyncCallerRunsPolicy());

    private static ExecutorService poolExecutor2 =
            new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                    new ThreadFactoryBuilder().setDaemon(false)
                            .setNameFormat("parseq-%d")
                            .build(), new AsyncCallerRunsPolicy());

    private static final Logger LOGGER = getLogger(IntegrateOutAsync3.class);

    public static void main(String[] args) throws IOException, InterruptedException {


        Engine engine = new EngineBuilder().setTaskExecutor(poolExecutor2)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();
        Task task = createCompoundTask();
        engine.run(task, "xxx");
        task.await();
        String trace = TraceUtil.getJsonTrace(task);
        LOGGER.info("任务整体执行完成");
        System.out.println(trace);
        engine.shutdown();
        System.exit(0);
    }

    private static Task<Object> createTask(int id) {
        Task<Object> task = Task.async("Task 1", () -> {
            SettablePromise<Object> promise = Promises.settable();
            outExecutor.submit(() -> {
                Thread.sleep(id * 100 + 1000L);
                LOGGER.info("Task id = {} 执行完成", id);
                promise.done("Task 1 done!!!");
                return null;
            });
            return promise;
        }).andThen("Task  2", (x) -> {
            LOGGER.info("Task 2 done!!!");
        });
        return task;
    }

    private static Task createCompoundTask() {
        return createTask(1);
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
