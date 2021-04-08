package manfred.end.guava.eventbus;

class Event {
    public String toString() {
        return Thread.currentThread() + ": 我是一条消息！" + System.identityHashCode(this);
    }
}
