package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.promise.Promise;
import com.linkedin.parseq.promise.Promises;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;

public class Basic2 {
    public static void main(String[] args) throws IOException {

        ExecutorService poolExecutor =
                new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                        new ThreadFactoryBuilder().setDaemon(false)
                                .setNameFormat("mall-async-engine-thread-%d")
                                .build(), new AsyncCallerRunsPolicy());

        Engine engine = new EngineBuilder().setTaskExecutor(poolExecutor)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();

        engine.run(createCompoundTask2());
        engine.shutdown();
        System.in.read();
        System.exit(0);
    }

    private static Task<Object> createTask(int id) {
        return Task.async(
                () -> {
                    System.out.println("BEGIN: time=" + new Date() + ",  Thread: " + Thread.currentThread() + ", id = " + id);
                    Thread.sleep(1000L);
                    System.out.println("END: time=" + new Date() + ",  Thread: " + Thread.currentThread() + ", id = " + id);
                    return Promises.value(new Object());
                }
        );
    }

    private static Task createCompoundTask(int x) {
        return Task.par(createTask(x),
                createTask(x + 1),
                createTask(x + 2),
                createTask(x + 3),
                createTask(x + 4),
                createTask(x + 5),
                createTask(x + 6)
                );
    }

    private static Task createCompoundTask2() {
        return Task.par(createCompoundTask(0),
                createCompoundTask(7)
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
