package manfred.end.thread.local;

public class ThreadLocalHolder {
    public static ThreadLocal<Object> x = ThreadLocal.withInitial(() -> new Object());
}
