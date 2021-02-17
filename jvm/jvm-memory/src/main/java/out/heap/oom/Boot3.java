package out.heap.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx64m
 * -Xms64m
 * -Xmn32m
 * -XX:+PrintGCDetails
 * -XX:MaxTenuringThreshold=1//设置进去old gen的寿命是1，默认是15次才进入old gen
 * -XX:MaxDirectMemorySize=10M
 * -XX:+DisableExplicitGC//不能显示full gc
 *
 * <p>
 * 大量DirectByteBuffer对象移动到old gen。没有Full gc的发生，
 * 导致在程序中可能死掉的DirectByteBuffer对象没有回收掉，native memory则满了，发生OOM
 *
 * @author Manfred since 2019/5/31
 */
public class Boot3 {
    public static void main(String[] args) {
        int i = 1;
        List<ByteBuffer> list = new ArrayList<>();
        while (true) {
            i++;
            if (i % 10000 == 0) {
                System.out.println("第" + i + "次");
            }
            //分配堆内内存
            ByteBuffer.allocate(1024 * 1024);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 / 2 / 2 / 2);
            //保证引用不被younggc
            list.add(byteBuffer);
        }
    }
}
