package exe16.sum3.closest;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        long minDiff = Long.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int i1 = i + 1; i1 < nums.length - 1; i1++) {
                for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                    long diff = Math.abs((long) nums[i] + (long) nums[i2] + (long) nums[i1] - target);
                    if (diff < minDiff) {
                        result = nums[i] + nums[i2] + nums[i1];
                        minDiff = diff;
                    }
                }
            }
        }
        return result;
    }
}