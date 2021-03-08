package manfred.end.algorithm.sort.quick;

import java.util.Arrays;

public class Boot {

    public static void main(String[] args) {
        int[] x = new int[]{5, 4, 3, 2, 1};
        int[] x2 = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        int[] x3 = {1, 2, 3, 4, 5};
        int[] x4 = {1, 32, 23, 14, 25};

        QuickSort.sort(x);
        QuickSort.sort(x2);
        QuickSort.sort(x3);
        QuickSort.sort(x4);

        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(x2));
        System.out.println(Arrays.toString(x3));
        System.out.println(Arrays.toString(x4));
    }
}
