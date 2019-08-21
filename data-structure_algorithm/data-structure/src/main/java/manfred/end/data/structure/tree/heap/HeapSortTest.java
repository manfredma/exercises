package manfred.end.data.structure.tree.heap;

import org.junit.Test;

/**
 * @author Manfred since 2019/8/21
 */
public class HeapSortTest {

    @Test
    public void test() {
        int[] src = new int[]{0, 1, 101, 1, 105, 10, 11, 6, 13, 15, 18, 9, 8, 4,};
        int[] dst = new HeapSort().sort(src);

        for (int i : dst) {
            System.out.print(i + "->");
        }
    }
}
