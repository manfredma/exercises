package manfred.end.guava.eventbus;

import com.google.common.eventbus.Subscribe;

class Listener {
    @Subscribe
    public void doNothing(Event e) {
        System.out.println("收到一条消息！[" + e + "]");
    }
}
