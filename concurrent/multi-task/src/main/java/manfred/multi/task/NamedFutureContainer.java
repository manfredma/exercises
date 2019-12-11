package manfred.multi.task;

import java.util.concurrent.Future;

/**
 *
 * @author manfred
 * @since 2019-12-11 下午2:44
 */
public interface NamedFutureContainer<V> {

    Future<V> get(String futureName);
}
