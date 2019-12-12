package manfred.multi.task.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import manfred.multi.task.ExecutorParallelPool;
import manfred.multi.task.policy.DefaultExecutePolicy;
import manfred.multi.task.policy.ExecutePolicy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author manfred
 * @since 2019-12-11 下午7:24
 */
@Configuration
@ConfigurationProperties(prefix = "threadpool")
@Data
@Slf4j
public class ParallelPoolConfig {

    private ThreadPoolConfig realNameQuery;

    @Bean("realNameQueryPool")
    public ExecutorParallelPool realNameQueryPool() {

        // 创建线程池
        BlockingQueue<Runnable> queue = createQueue(realNameQuery.getQueueSize());
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        try {
            threadFactory = (ThreadFactory) Class.forName(realNameQuery.getThreadFactoryClassName()).newInstance();
        } catch (Exception e) {
            // ignore 线程池，一般只会设置名称或者后台，不影响使用，记录日志
            log.error("ParallelPoolConfig#realNameQueryPool create threadFactory error by reflect!", e);
        }
        RejectedExecutionHandler rejectedExecutionHandler;
        try {
            rejectedExecutionHandler = (RejectedExecutionHandler) Class.forName(realNameQuery.getRejectedExecutionHandlerClassName()).newInstance();
        } catch (Exception e) {
            // ignore 线程池
            log.error("ParallelPoolConfig#realNameQueryPool create rejectedExecutionHandler error by reflect!", e);
            throw new RuntimeException("使用反射创建线程池的拒绝策略失败", e);
        }
        ThreadPoolExecutor executor = createExecutor(realNameQuery.getCorePoolSize(), realNameQuery.getMaximumPoolSize(),
                (int) realNameQuery.getKeepAliveTime().getSeconds(),  queue, threadFactory,rejectedExecutionHandler);
        if (realNameQuery.isAllowCoreThreadTimeout()) {
            executor.allowCoreThreadTimeOut(true);
        }

        // 创建任务的默认配置
        long taskTimeout = -1L;
        if (null != realNameQuery.getTimeout()) {
            taskTimeout = realNameQuery.getTimeout().toMillis();
        }
        ExecutePolicy defaultExecutePolicy = DefaultExecutePolicy.builder()
                .timeout(taskTimeout)
                .submitOrder(realNameQuery.isSubmitOrder())
                .build();

        return new ExecutorParallelPool(defaultExecutePolicy, executor);

    }

    private BlockingQueue<Runnable> createQueue(int queueCapacity) {
        if (queueCapacity > 0) {
            return new LinkedBlockingQueue<>(queueCapacity);
        } else {
            return new SynchronousQueue<>();
        }
    }

    protected ThreadPoolExecutor createExecutor(
            int corePoolSize, int maxPoolSize, int keepAliveSeconds, BlockingQueue<Runnable> queue,
            ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {

        return new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveSeconds, TimeUnit.SECONDS, queue, threadFactory, rejectedExecutionHandler);
    }

}
