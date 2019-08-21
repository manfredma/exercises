package manfred.end.data.structure.tree.heap;

/**
 * 大顶堆
 *
 * @author Manfred since 2019/8/21
 */
public class Heap {
    /**
     * 数组，从下标 1 开始存储数据
     */

    private int[] data;

    /**
     * 堆可以存储的最大数据个数
     */
    private int capacity;

    /**
     * 堆中已经存储的数据个数
     */
    private int count;

    public Heap(int capacity) {
        data = new int[capacity + 1];
        this.capacity = capacity;
        count = 0;
    }

    public void insert(int insertData) {
        // 判断堆是否已满
        if (count >= capacity) {
            return;
        }

        // 如果堆中还有空间,则从小往上堆化
        ++count;

        int cur = count;
        data[cur] = insertData;
        int p = cur / 2;
        while (p != 0) {
            if (data[p] < data[cur]) {
                int tmp = data[cur];
                data[cur] = data[p];
                data[p] = tmp;

                cur = p;
                p = cur / 2;
            } else {
                break;
            }
        }
    }


    public int removeMax() {
        // 判断堆中是否数据
        if (isEmpty()) {
            throw new RuntimeException("heap is Empty");
        }
        int result = data[1];
        data[1] = data[count];
        --count;
        heapify(1);
        return result;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 自上往下堆化
     */
    private void heapify(int cur) {

        int leftChildIndex = cur * 2;
        if (leftChildIndex > count) {
            // 已经堆化到叶子节点
            return;
        }
        int maxChildIndex = leftChildIndex;
        int rightChildIndex = cur * 2 + 1;
        if (rightChildIndex <= count && data[rightChildIndex] > data[leftChildIndex]) {
            maxChildIndex = rightChildIndex;
        }

        if (data[cur] < data[maxChildIndex]) {
            int tmp = data[cur];
            data[cur] = data[maxChildIndex];
            data[maxChildIndex] = tmp;
            heapify(maxChildIndex);
        }
    }

}
