package exe01._07.rotate.matrix.lcci;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16},
        };

        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        solution.rotate(matrix);

        System.out.println("++++++++++");
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
