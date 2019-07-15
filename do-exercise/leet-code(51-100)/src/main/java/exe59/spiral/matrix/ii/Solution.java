package exe59.spiral.matrix.ii;

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] r = new int[n][n];
        int value = 1;
        int spiralLoop = (int) Math.ceil(n / 2.0);
        for (int i = 0; i < spiralLoop; i++) {
            for (int j = i; j < n - i; j++) {
                r[i][j] = value++;
            }
            for (int j = i + 1; j < n - i; j++) {
                r[j][n - 1 - i] = value++;
            }
            for (int j = i + 1; j < n - i; j++) {
                r[n - 1 - i][n - 1 - j] = value++;
            }
            for (int j = i + 1; j < n - i - 1; j++) {
                r[n - 1 - j][i] = value++;
            }
        }
        return r;
    }
}