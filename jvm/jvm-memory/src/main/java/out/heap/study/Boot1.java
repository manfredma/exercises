package out.heap.study;

import java.nio.ByteBuffer;

/**
 * @author Manfred since 2019/5/31
 */
public class Boot1 {
    public static void main(String[] args) {
        int i = 1;
        while (true) {
            System.out.println("第" + (i++) + "次");
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
        }
    }
}
