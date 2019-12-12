package manfred.multi.task.policy;

public interface ExecutePolicy {

    long timeoutInMs();

    boolean submitOrder();
}
