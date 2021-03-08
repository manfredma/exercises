package manfred.end.algorithm.sort.quick;

public class QuickSort {

    public static int[] sort(int[] x) {
        realSort(x, 0, x.length - 1);
        return x;
    }

    private static void realSort(int[] x, int begin, int end) {
        if (begin >= end) {
            return;
        }

        int left = begin;
        int right = end - 1;

        int c = x[end];

        while (left < right) {
            if (x[left] < c) {
                left++;
            } else if (x[right] >= c) {
                right--;
            } else {
                int tmp = x[left];
                x[left] = x[right];
                x[right] = tmp;
                left++;
                right--;
            }
        }
        if (x[left] > c) {
            int tmp = x[left];
            x[left] = c;
            x[end] = tmp;
        }

        realSort(x, begin, left);
        realSort(x, left + 1, end);
    }
}
