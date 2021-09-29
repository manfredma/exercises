package manfred.end.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * JKD版本：jdk1.8.0_111
 * JVM参数：-XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGC -XX:+PrintGCTimeStamps
 * -XX:+PrintGCDetails -Xms2g -Xmx2g -Xmn1g
 */
public class SysGc {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            createBigObject(21);
            System.gc();
        }
    }

    private static void createBigObject(int n) {
        List<byte[]> bytesList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bytesList.add(new byte[1024 * 1024 * 10]);
        }
        if (bytesList.size() < 20) {
            throw new RuntimeException("this is test");
        }
    }
}
