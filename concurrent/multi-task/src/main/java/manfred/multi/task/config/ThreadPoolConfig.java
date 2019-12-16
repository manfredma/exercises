package manfred.multi.task.config;

import lombok.Data;

import java.time.Duration;

/**
 * @author manfred
 * @since 2019-12-11 下午7:26
 */
@Data
public class ThreadPoolConfig {

    // 线程池相关配置

    private int corePoolSize;

    private int maximumPoolSize;

    private Duration keepAliveTime;

    private int queueSize;

    private String threadFactoryClassName;

    private String rejectedExecutionHandlerClassName = "java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy";

    private boolean allowCoreThreadTimeout;

    // 任务组相关配置

    private Duration timeout;

    private boolean submitOrder;


}
