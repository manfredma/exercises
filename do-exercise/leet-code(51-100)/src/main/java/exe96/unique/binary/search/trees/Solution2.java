package exe96.unique.binary.search.trees;

class Solution2 {
    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        }
        int[] x = new int[n + 1];
        x[0] = 1;
        x[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                x[i] += x[j - 1] * x[i - j];
            }
        }
        return x[n];
    }
}