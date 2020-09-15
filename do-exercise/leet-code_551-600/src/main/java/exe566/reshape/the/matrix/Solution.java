package exe566.reshape.the.matrix;

class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int or = nums.length;
        int oc = nums[0].length;
        if (or * oc != r * c) {
            return nums;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int index = c * i + j;
                int oi = index / oc;
                int oj = index % oc;
                result[i][j] = nums[oi][oj];
            }
        }
        return result;
    }
}