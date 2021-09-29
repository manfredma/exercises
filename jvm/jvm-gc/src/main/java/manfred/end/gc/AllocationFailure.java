package manfred.end.gc;

/**
 * JDK版本：jdk1.8.0_111
 * JVM参数：-Xms200m -Xmx200m -Xmn32m -XX:+UseParallelGC -XX:+UseParallelOldGC
 */
public class AllocationFailure {
    public static void main(String[] args) {
        // 设置大对象，新生代内存有32m，不够
        byte[] bigObj1 = new byte[1024 * 1024 * 160];
        byte[] bigObj2 = new byte[1024 * 1024 * 70];
    }
}