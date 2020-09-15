package exe300.longest.increasing.subsequence;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int result = 0;
        if (nums.length == 0) {
            return result;
        }
        result = 1;
        int[] max = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            max[i] = 1;
            for (int j = 0; j < i; j++) {
                if (max[i] <= max[j] && nums[j] < nums[i]) {
                    max[i] = max[j] + 1;
                    if (max[i] > result) {
                        result = max[i];
                    }
                }
            }
        }

        return result;
    }
}