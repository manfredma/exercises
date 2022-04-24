package manfred.end.thread.local;

public class ThreadLocalHolderV2 {
    private static ThreadLocal<Object> x = ThreadLocal.withInitial(() -> new Object());

    public static Object getObject() {
        return x.get();
    }
}
