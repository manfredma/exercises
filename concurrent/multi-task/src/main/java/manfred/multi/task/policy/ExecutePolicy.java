package manfred.multi.task.policy;

public interface ExecutePolicy {

    long timeout();

    boolean submitOrder();
}
