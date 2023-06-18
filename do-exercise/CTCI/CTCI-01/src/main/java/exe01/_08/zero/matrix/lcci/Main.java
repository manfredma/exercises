package exe01._08.zero.matrix.lcci;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] input = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        solution.setZeroes(input);
        System.out.println("++++++++++");
        for (int[] ints : input) {
            System.out.println(Arrays.toString(ints));
        }

    }
}
