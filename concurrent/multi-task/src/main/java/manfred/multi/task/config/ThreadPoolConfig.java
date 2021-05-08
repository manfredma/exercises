package manfred.multi.task.config;

import java.time.Duration;

/**
 * @author manfred
 * @since 2019-12-11 下午7:26
 */

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

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Duration getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Duration keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public String getThreadFactoryClassName() {
        return threadFactoryClassName;
    }

    public void setThreadFactoryClassName(String threadFactoryClassName) {
        this.threadFactoryClassName = threadFactoryClassName;
    }

    public String getRejectedExecutionHandlerClassName() {
        return rejectedExecutionHandlerClassName;
    }

    public void setRejectedExecutionHandlerClassName(String rejectedExecutionHandlerClassName) {
        this.rejectedExecutionHandlerClassName = rejectedExecutionHandlerClassName;
    }

    public boolean isAllowCoreThreadTimeout() {
        return allowCoreThreadTimeout;
    }

    public void setAllowCoreThreadTimeout(boolean allowCoreThreadTimeout) {
        this.allowCoreThreadTimeout = allowCoreThreadTimeout;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    public boolean isSubmitOrder() {
        return submitOrder;
    }

    public void setSubmitOrder(boolean submitOrder) {
        this.submitOrder = submitOrder;
    }
}
