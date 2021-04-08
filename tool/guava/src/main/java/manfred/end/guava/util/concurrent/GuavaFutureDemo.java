package manfred.end.guava.util.concurrent;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuavaFutureDemo {

    private static final org.slf4j.Logger LOGGER =
            org.slf4j.LoggerFactory.getLogger(GuavaFutureDemo.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("开始:" + LocalDateTime.now());

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ListeningExecutorService service = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<Integer> future = service.submit(() -> {
            LOGGER.info("开始耗时计算:" + LocalDateTime.now());
            Thread.sleep(2_000);
            LOGGER.info("结束耗时计算:" + LocalDateTime.now());
            return 100;
        });

        future.addListener(() -> LOGGER.info("调用成功"), executorService);

        Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(@Nullable Integer result) {
                LOGGER.info("调用成功！！");
            }

            @Override
            public void onFailure(Throwable t) {
                LOGGER.info("出现异常");
            }
        }, MoreExecutors.directExecutor());
        LOGGER.info("结束:" + LocalDateTime.now());
        new CountDownLatch(1).await();
    }
}