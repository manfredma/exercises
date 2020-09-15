package exe213.house.robber.ii;

import java.util.Arrays;

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(rob2(Arrays.copyOfRange(nums, 1, nums.length)), rob2(Arrays.copyOfRange(nums, 0, nums.length - 1)));
    }

    private int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] rob = new int[nums.length];
        int[] cool = new int[nums.length];
        rob[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cool[i] = rob[i - 1];
            rob[i] = Math.max(rob[i - 1], cool[i - 1] + nums[i]);
        }
        return Math.max(rob[nums.length - 1], cool[nums.length - 1]);
    }
}