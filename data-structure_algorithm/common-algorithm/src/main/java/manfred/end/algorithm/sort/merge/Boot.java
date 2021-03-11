package manfred.end.algorithm.sort.merge;

import java.util.Arrays;

public class Boot {

    public static void main(String[] args) {
        int[] x = new int[]{5, 4, 3, 2, 1};
        int[] x2 = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        int[] x3 = {1, 2, 3, 4, 5};
        int[] x4 = {1, 32, 23, 14, 25};
        int[] x5 = {};
        int[] x6 = {-1};

        String before = (Arrays.toString(x));
        MergeSort.sort(x);
        System.out.printf("before: %s \n after: %s\n", before, Arrays.toString(x));
        before = (Arrays.toString(x2));
        MergeSort.sort(x2);
        System.out.printf("before: %s \n after: %s\n", before, Arrays.toString(x2));
        before = (Arrays.toString(x3));
        MergeSort.sort(x3);
        System.out.printf("before: %s \n after: %s\n", before, Arrays.toString(x3));
        before = (Arrays.toString(x4));
        MergeSort.sort(x4);
        System.out.printf("before: %s \n after: %s\n", before, Arrays.toString(x4));
        before = (Arrays.toString(x5));
        MergeSort.sort(x5);
        System.out.printf("before: %s \n after: %s\n", before, Arrays.toString(x5));
        before = (Arrays.toString(x6));
        MergeSort.sort(x6);
        System.out.printf("before: %s \n after: %s\n", before, Arrays.toString(x6));
    }
}
