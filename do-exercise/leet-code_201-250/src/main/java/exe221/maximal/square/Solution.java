package exe221.maximal.square;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * Example 1:
 * Input: matrix = [<p>
 * |                 ["1","0","1","0","0"],
 * |                 ["1","0","1","1","1"],
 * |                 ["1","1","1","1","1"],
 * |                 ["1","0","0","1","0"]
 * |                ]
 * Output: 4
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [
 * ["0","1"],
 * ["1","0"]
 * ]
 * <p>
 * Output: 1
 * Example 3:
 * <p>
 * Input: matrix = [["0"]]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
class Solution {
    public int maximalSquare(char[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    int s = 1;
                    int v = i + 1;
                    int h = j + 1;
                    while (h < matrix[i].length && v < matrix.length) {
                        boolean valid = true;
                        // 新加入了一行和一列
                        for (int k = i; k <= v && valid; k++) {
                            if (matrix[k][h] == '0') {
                                valid = false;
                            }
                        }
                        for (int k = j; k <= h && valid; k++) {
                            if (matrix[v][k] == '0') {
                                valid = false;
                            }
                        }
                        if (!valid) {
                            break;
                        }

                        s++;
                        h++;
                        v++;
                    }
                    result = Math.max(s * s, result);
                }
            }
        }
        return result;
    }
}