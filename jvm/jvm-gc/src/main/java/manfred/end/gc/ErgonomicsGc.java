package manfred.end.gc;

import java.io.IOException;

/**
 * JDK版本：jdk1.8.0_111
 * JVM参数：-XX:+PrintGC -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xms200m -Xmx200m -Xmn30m -XX:+UseParallelGC -XX:+UseParallelOldGC
 *
 */
public class ErgonomicsGc {
    public static void main(String[] args) throws IOException {
        // 大对象直接放入老年代
        byte[] bigObj1 = new byte[1024 * 1024 * 160];
        // 下次创建新对象时，发现新生代内存不足；
        // 但是如果把新生代现有的对象挪到老年代之后，新生代即可容纳新对象。因此触发内存担保。
        byte[] bigObj2 = new byte[1024 * 1024 * 20];
        System.in.read();
    }
}
