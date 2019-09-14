package exe153.find.minimum.in.rotated.sorted.array;

import java.util.Arrays;

class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            // 抛出异常可能更合适一些
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }

        int midIndex = nums.length / 2;
        if (nums[midIndex] > nums[0] && nums[midIndex] < nums[nums.length - 1]) {
            return nums[0];
        } else if (nums[midIndex] > nums[0]) {
            return findMin(Arrays.copyOfRange(nums, midIndex + 1, nums.length));
        } else {
            return findMin(Arrays.copyOfRange(nums, 1, midIndex + 1));
        }
    }
}