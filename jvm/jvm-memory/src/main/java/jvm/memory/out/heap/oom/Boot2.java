package jvm.memory.out.heap.oom;

import java.nio.ByteBuffer;

/**
 * -Xmx64m
 * -Xms64m
 * -Xmn32m
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails
 * -XX:+DisableExplicitGC//限制GC限制调用
 * -XX:MaxDirectMemorySize=10M//堆外10M
 * <p>
 * native memory满了，但是young区没满，没有发生young gc回收DirectByteBuffer，
 * 所以堆外OOM（如果去掉DisableExplicitGC参数程序会一直有Full GC的信息输出，
 * 因为分配native memory的时候会主动调用System.GC()）
 *
 * @author Manfred since 2019/5/31
 */
public class Boot2 {
    public static void main(String[] args) {
        int i = 1;
        while (true) {
            i++;
            if (i % 10000 == 0) {
                System.out.println("第" + i + "次");
            }
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1);
        }
    }
}
