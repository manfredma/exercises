package manfred.end.java.io.nio.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author Manfred since 2019/4/19
 */
public class TestByteBuffer {

    @Test
    public void test1() {
        ByteBuffer bb = ByteBuffer.allocate(10000);
        System.out.println("** init: ");
        System.out.println("array: " + new String(bb.array()) + ", limit:" + bb.limit() + ", capacity:" + bb.capacity() + ", position:" + bb.position());

        bb.put("hello, world".getBytes());
        System.out.println("** after put: hello, world");
        System.out.println("array: " + new String(bb.array()) + ", limit:" + bb.limit() + ", capacity:" + bb.capacity() + ", position:" + bb.position());

        bb.flip();
        System.out.println("** after flip: ");
        System.out.println("array: " + new String(bb.array()) + ", limit:" + bb.limit() + ", capacity:" + bb.capacity() + ", position:" + bb.position());
    }
}
