package manfred.multi.task;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author manfred
 * @since 2019-12-11 下午12:05
 */
public interface CompletionMultiFuture<V> {

    /**
     * Retrieves and removes the Future representing the next
     * completed task, waiting if none are yet present.
     *
     * @return the Future representing the next completed task
     * @throws InterruptedException if interrupted while waiting
     */
    Future<V> takeCompleted() throws InterruptedException;

    /**
     * Retrieves and removes the Future representing the next
     * completed task, or {@code null} if none are present.
     *
     * @return the Future representing the next completed task, or
     *         {@code null} if none are present
     */
    Future<V> pollCompleted();

    /**
     * Retrieves and removes the Future representing the next
     * completed task, waiting if necessary up to the specified wait
     * time if none are yet present.
     *
     * @param timeout how long to wait before giving up, in units of
     *        {@code unit}
     * @param unit a {@code TimeUnit} determining how to interpret the
     *        {@code timeout} parameter
     * @return the Future representing the next completed task or
     *         {@code null} if the specified waiting time elapses
     *         before one is present
     * @throws InterruptedException if interrupted while waiting
     */
    Future<V> pollCompleted(long timeout, TimeUnit unit) throws InterruptedException;

}
