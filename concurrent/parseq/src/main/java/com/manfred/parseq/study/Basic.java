package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;

public class Basic {
    public static void main(String[] args) throws IOException {

        ExecutorService poolExecutor =
                new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                        new ThreadFactoryBuilder().setDaemon(false)
                                .setNameFormat("mall-async-engine-thread-%d")
                                .build(), new AsyncCallerRunsPolicy());

        Engine engine = new EngineBuilder().setTaskExecutor(poolExecutor)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();

        engine.run(createCompoundTask());
        engine.shutdown();
        System.in.read();
        System.exit(0);
    }

    private static Task<Object> createTask(int id) {
        return Task.callable(
                () -> {
                    System.out.println("BEGIN: time=" + new Date() + ",  Thread: " + Thread.currentThread() + ", id = " + id);
                    Thread.sleep(1000L);
                    System.out.println("END: time=" + new Date() + ",  Thread: " + Thread.currentThread() + ", id = " + id);
                    return "S";
                }
        );
    }

    private static Task createCompoundTask() {
        return Task.par(createTask(0),
                createTask(1),
                createTask(2),
                createTask(3),
                createTask(4),
                createTask(5),
                createTask(6)
                );
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
