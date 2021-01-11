package manfred.end.java.io.bio.m2n;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author manfred on 2019/9/8.
 */
public class TimeServerHandlerExecutorPool {
    private ExecutorService executorService;

    public TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize) {
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

}
