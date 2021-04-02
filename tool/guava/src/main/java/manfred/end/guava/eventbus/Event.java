package manfred.end.guava.eventbus;

class Event {
    public String toString() {
        return "我是一条消息！" + System.identityHashCode(this);
    }
}
