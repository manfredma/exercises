package manfred.multi.task;


import manfred.multi.task.policy.DefaultExecutePolicy;
import manfred.multi.task.policy.ExecutePolicy;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * @author manfred
 * @since 2019-12-11 下午12:02
 */
public class ExecutorParallelPool implements ParallelPool {

    private Executor executor = ForkJoinPool.commonPool();

    private ExecutePolicy defaultExecutePolicy = DefaultExecutePolicy.builder()
            .timeout(-1)
            .submitOrder(false)
            .build();

    public ExecutorParallelPool(ExecutePolicy executePolicy, Executor executor) {
        if (executePolicy != null) {
            this.defaultExecutePolicy = executePolicy;
        }
        if (executor != null) {
            this.executor = executor;
        }
    }

    public ExecutorParallelPool() {
    }

    @Override
    public <V> MultiFutureTask<V> submitTaskPair(List<TaskPair<V>> taskPairs) {
        return MultiFutureTask.ofTaskPair(convertFrom(defaultExecutePolicy), taskPairs);
    }

    @SafeVarargs
    @Override
    public final <V> MultiFutureTask<V> submitTaskPair(TaskPair<V>... taskPairs) {
        return MultiFutureTask.ofTaskPair(convertFrom(defaultExecutePolicy), taskPairs);
    }

    @SafeVarargs
    @Override
    public final <V> MultiFutureTask<V> submitTaskPair(ExecutePolicy policy, TaskPair<V>... taskPairs) {
        return MultiFutureTask.ofTaskPair(convertFrom(policy), taskPairs);
    }

    @Override
    public <V> MultiFutureTask<V> submit(List<Callable<V>> taskPairs) {
        return MultiFutureTask.ofCallable(convertFrom(defaultExecutePolicy), taskPairs);
    }

    @SafeVarargs
    @Override
    public final <V> MultiFutureTask<V> submit(Callable<V>... calls) {
        return MultiFutureTask.ofCallable(convertFrom(defaultExecutePolicy), calls);
    }

    @SafeVarargs
    @Override
    public final <V> MultiFutureTask<V> submit(ExecutePolicy policy, Callable<V>... calls) {
        return MultiFutureTask.ofCallable(convertFrom(policy), calls);
    }

    private MultiTaskContext convertFrom(ExecutePolicy policy) {
        return MultiTaskContext.builder()
                .executor(executor)
                .timeoutInMs(policy.timeoutInMs())
                .completeSubmitOrder(policy.submitOrder())
                .beginTime(System.currentTimeMillis())
                .build();
    }
}
