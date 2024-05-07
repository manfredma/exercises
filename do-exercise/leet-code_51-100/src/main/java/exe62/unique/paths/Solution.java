package exe62.unique.paths;

class Solution {

    private int[] x = new int[10201];

    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        int s = m * 101 + n;

        if (0 != x[s]) {
            return x[s];
        }
        int result = uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
        x[s] = result;
        return result;
    }
}