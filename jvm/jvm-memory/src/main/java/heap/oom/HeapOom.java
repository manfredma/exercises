package heap.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HeapOom {
    public static void main(String[] args) throws Exception {
        List<byte[]> x = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            // TimeUnit.MILLISECONDS.sleep(100);
            x.add(new byte[1024 * 1024]);
        }
        System.in.read();
    }
}
