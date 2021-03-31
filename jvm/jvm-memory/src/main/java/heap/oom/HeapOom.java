package heap.oom;

import java.util.ArrayList;
import java.util.List;

public class HeapOom {
    public static void main(String[] args) {
        List<byte[]> x = new ArrayList<>();
        while (true) {
            x.add(new byte[1024 * 1024 * 1024]);
        }
    }
}
