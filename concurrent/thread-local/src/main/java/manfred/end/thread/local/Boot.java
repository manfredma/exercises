package manfred.end.thread.local;

/**
 * @author manfred on 2021/5/9.
 */
public class Boot {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("xxx");
        System.out.println(threadLocal.get());

        System.out.println(ThreadLocalHolder.x.get());
    }
}
