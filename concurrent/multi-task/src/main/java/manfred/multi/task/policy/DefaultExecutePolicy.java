package manfred.multi.task.policy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class DefaultExecutePolicy implements ExecutePolicy {

    private long timeout;

    private boolean submitOrder;

    @Override
    public long timeout() {
        return timeout;
    }

    @Override
    public boolean submitOrder() {
        return submitOrder;
    }

}
