package manfred.multi.task;


import java.util.concurrent.Executor;

/**
 * @author manfred
 * @since 2019-12-11 上午9:48
 */
public class MultiTaskContext {
    /**
     * 是否以提交顺序返回
     */
    private boolean completeSubmitOrder = false;

    /**
     * 超时时间，-1 表示不超时
     * 单位： ms（毫秒）
     */
    private long timeoutInMs = -1;

    /**
     * 并行任务使用的线程池，如果没有传入则使用
     */
    private Executor executor;

    /**
     * 任务开始时间
     */
    private long beginTime;

    public static Builder builder() {
        return new Builder();
    }


    public boolean isCompleteSubmitOrder() {
        return completeSubmitOrder;
    }

    public void setCompleteSubmitOrder(boolean completeSubmitOrder) {
        this.completeSubmitOrder = completeSubmitOrder;
    }

    public long getTimeoutInMs() {
        return timeoutInMs;
    }

    public void setTimeoutInMs(long timeoutInMs) {
        this.timeoutInMs = timeoutInMs;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public static class Builder {
        /**
         * 是否以提交顺序返回
         */
        private boolean completeSubmitOrder = false;

        /**
         * 超时时间，-1 表示不超时
         * 单位： ms（毫秒）
         */
        private long timeoutInMs = -1;

        /**
         * 并行任务使用的线程池，如果没有传入则使用
         */
        private Executor executor;

        /**
         * 任务开始时间
         */
        private long beginTime;

        public Builder completeSubmitOrder(boolean completeSubmitOrder) {
            this.completeSubmitOrder = completeSubmitOrder;
            return this;
        }

        public Builder timeoutInMs(long timeoutInMs) {
            this.timeoutInMs = timeoutInMs;
            return this;
        }

        public Builder executor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public Builder beginTime(long beginTime) {
            this.beginTime = beginTime;
            return this;
        }

        public MultiTaskContext build() {
            MultiTaskContext multiTaskContext = new MultiTaskContext();
            multiTaskContext.setBeginTime(this.beginTime);
            multiTaskContext.setCompleteSubmitOrder(this.completeSubmitOrder);
            multiTaskContext.setExecutor(this.executor);
            multiTaskContext.setTimeoutInMs(this.timeoutInMs);
            return multiTaskContext;
        }
    }

}
