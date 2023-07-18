package exe01._07.rotate.matrix.lcci;

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) {
            return;
        }
        // 多个正方形的旋转。
        for (int row = 0; row < n / 2; row++) {
            for (int column = row; column < n - row - 1; column++) {
                int tmp = matrix[row][column];
                matrix[row][column] = matrix[n - 1 - column][row];
                matrix[n - 1 - column][row] = matrix[n - 1 - row][n - 1 - column];
                matrix[n - 1 - row][n - 1 - column] = matrix[column][n - 1 - row];
                matrix[column][n - 1 - row] = tmp;
            }
        }
    }
}