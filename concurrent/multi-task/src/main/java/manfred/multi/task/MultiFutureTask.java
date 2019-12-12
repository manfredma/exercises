package manfred.multi.task;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author manfred
 * @since 2019-12-11 上午9:47
 */
public class MultiFutureTask<V> implements TimeoutFuture<List<V>>, CompletionMultiFuture<V>, NamedFutureContainer<V> {

    private CompletionService<V> completionService;

    private Map<String, Future<V>> futures;

    private MultiTaskContext context;

    private MultiFutureTask(MultiTaskContext context, List<TaskPair<V>> calls) {
        initConfig(context);
        addTask(calls);
    }

    @SafeVarargs
    public static <V> MultiFutureTask<V> ofCallable(MultiTaskContext context, Callable<V>... calls) {
        List<Callable<V>> callableList = new ArrayList<>(calls.length);
        callableList.addAll(Arrays.asList(calls));
        return ofCallable(context, callableList);
    }

    @SafeVarargs
    public static <V> MultiFutureTask<V> ofTaskPair(MultiTaskContext context, TaskPair<V>... calls) {
        List<TaskPair<V>> taskPairs = new ArrayList<>(calls.length);
        taskPairs.addAll(Arrays.asList(calls));
        return ofTaskPair(context, taskPairs);
    }

    public static <V> MultiFutureTask<V> ofCallable(MultiTaskContext context, List<Callable<V>> calls) {
        List<TaskPair<V>> taskPairs = new ArrayList<>(calls.size());
        for (int i = 0; i < calls.size(); i++) {
            taskPairs.add(new TaskPair<V>(String.valueOf(i), calls.get(i)));
        }
        return ofTaskPair(context, taskPairs);
    }

    public static <V> MultiFutureTask<V> ofTaskPair(MultiTaskContext context, List<TaskPair<V>> calls) {
        return new MultiFutureTask<V>(context, calls);
    }


    private void initConfig(MultiTaskContext context) {
        this.context = context;
        Executor executor = null == context.getExecutor() ? ForkJoinPool.commonPool() : context.getExecutor();
        if (context.isCompleteSubmitOrder()) {
            completionService = new SubmitOrderedCompletionService<>(executor);
        } else {
            completionService = new ExecutorCompletionService<>(executor);
        }
    }

    private void addTask(List<TaskPair<V>> calls) {
        futures = new HashMap<>(calls.size());
        for (TaskPair<V> taskPair : calls) {
            futures.put(taskPair.getName(), completionService.submit(taskPair.getCallable()));
        }
    }

    @Override
    public Future<V> takeCompleted() throws InterruptedException {
        return completionService.take();
    }

    @Override
    public Future<V> pollCompleted() {
        return completionService.poll();
    }

    @Override
    public Future<V> pollCompleted(long timeout, TimeUnit unit) throws InterruptedException {
        return completionService.poll(timeout, unit);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean result = true;
        for (Future<V> future : futures.values()) {
            result = future.cancel(mayInterruptIfRunning) && result;
        }
        return result;
    }

    @Override
    public boolean isCancelled() {
        boolean result = true;
        for (Future<V> future : futures.values()) {
            result = result && future.isCancelled();
        }
        return result;
    }

    @Override
    public boolean isDone() {
        boolean result = true;
        for (Future<V> future : futures.values()) {
            result = result && future.isDone();
        }
        return result;
    }

    @Override
    public List<V> get() throws InterruptedException, ExecutionException {
        List<V> result = new ArrayList<>();
        for (Future<V> future : futures.values()) {
            result.add(future.get());
        }
        return result;
    }

    @Override
    public List<V> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        List<V> result = new ArrayList<>();
        for (Future<V> future : futures.values()) {
            result.add(future.get(timeout, unit));
        }
        return result;
    }

    @Override
    public Future<V> get(String futureName) {
        return futures.get(futureName);
    }

    @Override
    public boolean isTimeout() {
        return context.getTimeoutInMs() >= 0 && context.getTimeoutInMs() + context.getBeginTime() < System.currentTimeMillis();
    }

    @Override
    public List<V> getInDefaultTimeout() throws InterruptedException, ExecutionException, TimeoutException {
        if (futures == null || futures.size() == 0) {
            return new ArrayList<>();
        }
        if (context.getTimeoutInMs() < 0) {
            return get();
        }
        long beginTime = context.getBeginTime();
        long timeoutInMs = context.getTimeoutInMs();
        Collection<Future<V>> futureList = futures.values();
        for (Future<V> vFuture : futureList) {
            long costTime = System.currentTimeMillis() - beginTime;
            long leftTime = timeoutInMs - costTime;
            if (leftTime < 0) {
                throw new TimeoutException();
            }
            // 阻塞
            vFuture.get(leftTime, TimeUnit.MILLISECONDS);
        }
        return get();
    }
}
