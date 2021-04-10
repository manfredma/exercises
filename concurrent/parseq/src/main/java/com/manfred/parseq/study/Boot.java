package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

public class Boot {

    private Engine engine;

    @Before
    public void init() {
        ExecutorService poolExecutor =
                new ThreadPoolExecutor(20, 50, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                        new ThreadFactoryBuilder().setDaemon(false)
                                .setNameFormat("mall-async-engine-thread-%d")
                                .build(), new Basic.AsyncCallerRunsPolicy());

        engine = new EngineBuilder().setTaskExecutor(poolExecutor)
                .setTimerScheduler(Executors.newScheduledThreadPool(2))
                .build();
    }


    @Test
    public void testFlatmap() throws InterruptedException {
        Task<String> task = Task.value("hello", "xxxxx");
        Task<Boot> finalTask = task.flatMap("flatmap to Boot", s -> Task.value(new Boot()));
        engine.run(finalTask);
        finalTask.await();
        System.out.println(finalTask.get());
    }

    @After
    public void destroy() {
        engine.shutdown();
    }
}
