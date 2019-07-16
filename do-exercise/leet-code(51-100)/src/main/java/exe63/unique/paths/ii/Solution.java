package exe63.unique.paths.ii;

class Solution {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) {
            return 0;
        }
        int n = obstacleGrid[0].length;
        if (n == 0) {
            return 0;
        }
        int[] x = new int[10201];
        return doUniquePathsWithObstacles(obstacleGrid, m, n, x);
    }

    private int doUniquePathsWithObstacles(int[][] obstacleGrid, int m, int n, int[] x) {
        if (obstacleGrid[obstacleGrid.length - m][obstacleGrid[0].length - n] == 1) {
            return 0;
        }
        int s = m * 101 + n;
        if (0 != x[s]) {
            return x[s];
        }
        int r = 0, c = 0;
        if (m > 1) {
            r = doUniquePathsWithObstacles(obstacleGrid, m - 1, n, x);
        }
        if (n > 1) {
            c = doUniquePathsWithObstacles(obstacleGrid, m, n - 1, x);
        }
        if (m == 1 && n == 1) {
            x[s] = 1;
        } else {
            x[s] = r + c;
        }
        return x[s];
    }
}