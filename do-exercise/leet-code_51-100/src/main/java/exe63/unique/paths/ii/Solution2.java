package exe63.unique.paths.ii;

class Solution2 {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) {
            return 0;
        }
        int n = obstacleGrid[0].length;
        if (n == 0) {
            return 0;
        }
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        obstacleGrid[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i != m - 1 && j != n - 1) {
                    if (obstacleGrid[i][j] == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i + 1][j] + obstacleGrid[i][j + 1];
                    } else {
                        obstacleGrid[i][j] = 0;
                    }
                } else if (i != m - 1) {
                    if (obstacleGrid[i][j] == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i + 1][j];
                    } else {
                        obstacleGrid[i][j] = 0;
                    }
                } else if (j != n - 1) {
                    if (obstacleGrid[i][j] == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i][j + 1];
                    } else {
                        obstacleGrid[i][j] = 0;
                    }
                }
            }
        }
        return obstacleGrid[0][0];
    }
}