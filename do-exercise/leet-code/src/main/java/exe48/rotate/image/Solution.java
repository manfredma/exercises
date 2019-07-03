package exe48.rotate.image;

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int tmp0 = matrix[i][j];
                int tmp1 = matrix[j][n - 1 - i];
                int tmp2 = matrix[n - 1 - i][n - 1 - j];
                int tmp3 = matrix[n - 1 - j][i];

                matrix[j][n - 1 - i] = tmp0;
                matrix[n - 1 - i][n - 1 - j] = tmp1;
                matrix[n - 1 - j][i] = tmp2;
                matrix[i][j] = tmp3;
            }
        }
    }
}