package exe54.spiral.matrix;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return result;
        }

        int n = matrix[0].length;
        int spiralLoop = Math.min(m, n) / 2;
        for (int i = 0; i < spiralLoop; i++) {
            for (int j = i; j < n - i; j++) {
                result.add(matrix[i][j]);
            }
            for (int j = i + 1; j < m - i; j++) {
                result.add(matrix[j][n - 1 - i]);
            }
            for (int j = i + 1; j < n - i; j++) {
                result.add(matrix[m - 1 - i][n - 1 - j]);
            }
            for (int j = i + 1; j < m - i - 1; j++) {
                result.add(matrix[m - 1 - j][i]);
            }
        }

        if (m >= n) {
            if (n % 2 == 1) {
                for (int c = n / 2, r = c; r < m - c; r++) {
                    result.add(matrix[r][c]);
                }
            }
        } else {
            if (m % 2 == 1) {
                for (int c = m / 2, r = c; c < n - r; c++) {
                    result.add(matrix[r][c]);
                }
            }
        }
        return result;

    }
}