package manfred.end.guava.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

public class EventBusTest {
    public static void main(String[] args) {

    }

    /**
     *
     */
    @Test
    public void simpleTest() {
        EventBus eventBus = new EventBus("Test");
        eventBus.register(new Listener());
        eventBus.post(new Event());
    }
}

