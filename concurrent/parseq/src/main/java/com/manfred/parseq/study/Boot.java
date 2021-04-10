package com.manfred.parseq.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.*;

public class Boot {

    private Engine engine;

    @BeforeClass
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
    public void testFlatmap() {
        Task<String> task = Task.value("xxxxx");
    }
}
