package exe53.maximum.subarray;

class Solution {
    public int maxSubArray(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        int result = nums[0];
        int maxResult = result;

        for (int i = 1; i < nums.length; i++) {
            if (result < 0) {
                result = nums[i];
            } else {
                result = nums[i] + result;
            }

            maxResult = result > maxResult ? result : maxResult;
        }
        return maxResult;
    }
}