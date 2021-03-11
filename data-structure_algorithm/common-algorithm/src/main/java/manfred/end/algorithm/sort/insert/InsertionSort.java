package manfred.end.algorithm.sort.insert;

public class InsertionSort {
    public static int[] sort(int[] x) {
        if (x == null || x.length == 0 || x.length == 1) {
            return x;
        }

        for (int i = 1; i < x.length; i++) {
            // 查找过程可以使用二分查找优化，但是节点需要依次后移无法优化！
            for (int j = 0; j < i; j++) {
                if (x[i] < x[j]) {
                    int tmp = x[i];
                    x[i] = x[j];
                    x[j] = tmp;
                }
            }
        }
        return x;
    }
}
