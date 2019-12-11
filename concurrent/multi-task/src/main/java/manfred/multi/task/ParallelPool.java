package manfred.multi.task;


import manfred.multi.task.policy.ExecutePolicy;

import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public interface ParallelPool {

    <V> MultiFutureTask<V> submitTaskPair(List<TaskPair<V>> taskPairs);

    <V> MultiFutureTask<V> submitTaskPair(TaskPair<V>... taskPairs);

    <V> MultiFutureTask<V> submitTaskPair(ExecutePolicy policy, TaskPair<V>... taskPairs);

    <V> MultiFutureTask<V> submit(List<Callable<V>> callableList);

    <V> MultiFutureTask<V> submit(Callable<V>... calls);

    <V> MultiFutureTask<V> submit(ExecutePolicy policy, Callable<V>... calls);

}