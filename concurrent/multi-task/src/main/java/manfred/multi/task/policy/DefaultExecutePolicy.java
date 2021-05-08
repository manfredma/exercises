package manfred.multi.task.policy;

public final class DefaultExecutePolicy implements ExecutePolicy {

    private long timeout;

    private boolean submitOrder;


    @Override
    public long timeoutInMs() {
        return timeout;
    }

    @Override
    public boolean submitOrder() {
        return submitOrder;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isSubmitOrder() {
        return submitOrder;
    }

    public void setSubmitOrder(boolean submitOrder) {
        this.submitOrder = submitOrder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private long timeout;

        private boolean submitOrder;

        public Builder timeout(long i) {
            this.timeout = i;
            return this;
        }

        public Builder submitOrder(boolean submitOrder) {
            this.submitOrder = submitOrder;
            return this;
        }

        public ExecutePolicy build() {
            DefaultExecutePolicy executePolicy = new DefaultExecutePolicy();
            executePolicy.setTimeout(timeout);
            executePolicy.setSubmitOrder(submitOrder);
            return executePolicy;
        }
    }
}
