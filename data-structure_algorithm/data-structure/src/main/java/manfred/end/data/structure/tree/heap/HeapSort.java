package manfred.end.data.structure.tree.heap;

/**
 * @author Manfred since 2019/8/21
 */
public class HeapSort {

    public int[] sort(int[] source) {
        Heap heap = new Heap(source.length);

        for (int i : source) {
            heap.insert(i);
        }

        int[] result = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            result[source.length - 1 - i] = heap.removeMax();
        }
        return result;
    }
}
