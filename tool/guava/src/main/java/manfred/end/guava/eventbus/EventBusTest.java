package manfred.end.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EventBusTest {

    /**
     * 最简单的 EventBus 实例
     */
    @Test
    public void simpleTest() {
        EventBus eventBus = new EventBus("Test");
        eventBus.register(new Listener());
        eventBus.post(new Event());
    }

    @Test
    public void asyncTest() {
        AsyncEventBus asyncEventBus = new AsyncEventBus(
                new ThreadPoolExecutor(
                        2, 3, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30)
                ));
        asyncEventBus.register(new Listener());
        asyncEventBus.post(new Event());

    }
}

