package manfred.end.algorithm.sort.bubble;

public class BubbleSort {
    public static int[] sort(int[] x) {
        if (x == null || x.length == 0 || x.length == 1) {
            return x;
        }

        for (int i = 0; i < x.length; i++) {
            for (int j = 1; j < x.length - i; j++) {
                if (x[j] < x[j - 1]) {
                    int tmp = x[j - 1];
                    x[j - 1] = x[j];
                    x[j] = tmp;
                }
            }
        }
        return x;
    }
}
