package manfred.multi.task;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author manfred
 * @since 2019-12-11 下午12:05
 */
public interface CompletionMultiFuture<V> {

    Future<V> take() throws InterruptedException;

    Future<V> poll();

    Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;

}
