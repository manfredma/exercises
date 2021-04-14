package manfred.end.jvm.off.heap.unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class ObjectInHeap {
    private long address = 0;

    private Unsafe unsafe = GetUnsafeInstance.getUnsafeInstance();

    public ObjectInHeap() {
        address = unsafe.allocateMemory(200 * 1024 * 1024);
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            ObjectInHeap heap = new ObjectInHeap();
            System.out.println("memory address=" + heap.address);
			TimeUnit.MILLISECONDS.sleep(100L);
        }
    }
}