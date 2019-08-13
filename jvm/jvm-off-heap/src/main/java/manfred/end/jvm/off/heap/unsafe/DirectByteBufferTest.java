package manfred.end.jvm.off.heap.unsafe;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author Manfred since 2019/8/9
 */
public class DirectByteBufferTest {

    @Test
    public void testProperties() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.putChar('a');
        buffer.putChar('c');
        System.out.println("插入完数据 " + buffer);
        // 记录mark的位置
        buffer.mark();
        // 设置的position一定要比mark大，否则mark无法重置
        buffer.position(30);
        System.out.println("reset前 " + buffer);
        // 重置reset ，reset后的position=mark
        buffer.reset();
        System.out.println("reset后 " + buffer);
        //清除标记，position变成0，mark变成-1
        buffer.rewind();
        System.out.println("清除标记后 " + buffer);
        System.out.println("getChar(1) = " + buffer.getChar() + "; " + buffer);
        System.out.println("getChar(2) = " + buffer.getChar() + "; " + buffer);
    }

    @Test
    public void testWriteRead() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        System.out.println("初始化后 " + buffer);
        byte[] data = {1, 2};
        buffer.put(data);
        System.out.println("写byte[]后 " + buffer);
        buffer.clear();//清空了
        //5个byte
        buffer.put("hello".getBytes());
        System.out.println("hello".getBytes().length);
        System.out.println("写string后 " + buffer);
    }

    @Test
    public void testReadToArray() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        buffer.put(new byte[]{1, 2, 3, 4});
        System.out.println("刚写完数据 " + buffer);
        buffer.flip();
        System.out.println("flip之后 " + buffer);
        byte[] target = new byte[buffer.limit()];
        //自动读取target.length个数据
        buffer.get(target);
        for (byte b : target) {
            System.out.println(b);
        }
        System.out.println("读取完数组 " + buffer);
    }

    @Test
    public void testCompact() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        buffer.put(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println("刚写完数据 " + buffer);
        buffer.position(2);
        System.out.println("position之后 " + buffer);
        buffer.compact();
        System.out.println("compact之后 " + buffer);
        buffer.rewind();
        byte[] target = new byte[buffer.limit()];
        //自动读取target.length个数据
        buffer.get(target);
        for (byte b : target) {
            System.out.print(b + " ");
        }
        System.out.println("读取完数组 " + buffer);
    }
}
