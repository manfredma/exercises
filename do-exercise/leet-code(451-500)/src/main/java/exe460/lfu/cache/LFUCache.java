package exe460.lfu.cache;

import java.util.HashMap;

class LFUCache {

    Heap heap;

    private HashMap<Integer, Heap.HeapNode> cache;

    public LFUCache(int capacity) {
        heap = new Heap(capacity);
        cache = new HashMap<>(capacity * 2);
    }

    public int get(int key) {
        if (heap.capacity == 0) {
            return -1;
        }
        if (cache.containsKey(key)) {
            Heap.HeapNode heapNode = cache.get(key);
            heapNode.frequency++;
            // 重新堆化一下
            heap.heapToBottom(heapNode.arrayIndex);
            return heapNode.hashValue;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (heap.capacity == 0) {
            return;
        }
        if (cache.containsKey(key)) {
            Heap.HeapNode heapNode = cache.get(key);
            heapNode.hashValue = value;
            if (heapNode.frequency == 1) {
                heap.heapToBottom(heapNode.arrayIndex);
            } else {
                heapNode.frequency = 1;
                heap.heapToTop(heapNode.arrayIndex);
            }

        } else {
            if (heap.isFull()) {
                // 淘汰最少访问的
                Heap.HeapNode removedHeapNode = heap.removeTop();
                cache.remove(removedHeapNode.hashKey);
            }
            Heap.HeapNode heapNode = heap.insert(1, value, key);
            cache.put(key, heapNode);
        }
    }


    /**
     * 小顶堆
     */
    class Heap {


        /**
         * 数组，从下标 1 开始存储数据
         */
        private HeapNode[] data;

        /**
         * 堆可以存储的最大数据个数
         */
        private int capacity;

        /**
         * 堆中已经存储的数据个数
         */
        private int count;

        public Heap(int capacity) {
            data = new HeapNode[capacity + 1];
            this.capacity = capacity;
            count = 0;
        }

        public boolean isFull() {
            return count >= capacity;
        }

        public HeapNode insert(int insertKey, int insertValue, int hashKey) {
            // 判断堆是否已满
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }

            // 如果堆中还有空间,则从小往上堆化
            ++count;
            int cur = count;
            HeapNode result = new HeapNode(insertKey, insertValue, cur, hashKey);
            data[cur] = result;
            heapToTop(cur);
            return result;
        }

        private void heapToTop(int cur) {
            int p = cur / 2;
            while (p != 0) {
                if (data[p].frequency > data[cur].frequency) {
                    HeapNode tmp = data[cur];
                    data[cur] = data[p];
                    data[p] = tmp;

                    // 更新一下索引
                    data[p].arrayIndex = p;
                    data[cur].arrayIndex = cur;

                    cur = p;
                    p = cur / 2;
                } else {
                    break;
                }
            }
        }


        public HeapNode removeTop() {
            // 判断堆中是否数据
            if (isEmpty()) {
                throw new RuntimeException("heap is empty");
            }
            HeapNode result = data[1];
            data[1] = data[count];
            --count;
            heapToBottom(1);
            return result;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        /**
         * 自上往下堆化
         */
        private void heapToBottom(int cur) {

            int leftChildIndex = cur * 2;
            if (leftChildIndex > count) {
                // 已经堆化到叶子节点
                return;
            }
            int minChildIndex = leftChildIndex;
            int rightChildIndex = cur * 2 + 1;
            if (rightChildIndex <= count && data[rightChildIndex].frequency < data[leftChildIndex].frequency) {
                minChildIndex = rightChildIndex;
            }

            if (data[cur].frequency >= data[minChildIndex].frequency) {

                // 交换节点
                HeapNode tmp = data[cur];
                data[cur] = data[minChildIndex];
                data[minChildIndex] = tmp;
                heapToBottom(minChildIndex);

                // 更新一下索引
                data[cur].arrayIndex = cur;
                data[minChildIndex].arrayIndex = minChildIndex;
            }
        }

        private class HeapNode {
            int frequency;
            int hashValue;
            int arrayIndex;

            int hashKey;

            HeapNode(int key, int value, int index, int hashKey) {
                this.frequency = key;
                this.hashValue = value;
                this.arrayIndex = index;
                this.hashKey = hashKey;
            }
        }

    }
}
