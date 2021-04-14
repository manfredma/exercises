package manfred.end.jvm.off.heap.unsafe;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class NativeOOM {
    public static void main(String[] args) {
        List<ByteBuffer> holder = new ArrayList<>();
        while (true) {
            holder.add(ByteBuffer.allocateDirect(1024 * 1024 * 1024));
        }
    }
}
