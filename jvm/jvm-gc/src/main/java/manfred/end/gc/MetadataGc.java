package manfred.end.gc;

/**
 * JKD版本：jdk1.8.0_111
 * JVM参数：-XX:+UseParallelGC -XX:+UseParallelOldGC -XX:MetaspaceSize=256k
 *
 * @author Bo.Zhao
 * @since 19/4/21
 */
public class MetadataGc {
    public static void main(String[] args) {
        System.out.println("hello world");
    }

}