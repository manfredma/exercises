package exe239.sliding.window.maximum;

import java.util.*;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length - k + 1];
        Stack<Integer> stack = new Stack<>();
        for (int i = k - 1; i < nums.length; i = i + k) {
            if (k > 1) {
                stack.push(nums[i - 1]);
            }
            for (int j = 1; j < k - 1; j++) {
                stack.push(Math.max(stack.peek(), nums[i - 1 - j]));
            }

            int preMax = nums[i];
            if (i + k - 1 < nums.length) {
                for (int j = 0; j < k - 1; j++) {
                    preMax = Math.max(nums[i + j], preMax);
                    result[i + j - k + 1] = Math.max(preMax, stack.pop());
                }
                result[i] = Math.max(preMax, nums[i + k - 1]);
            } else {
                for (int j = 0; j < k - 1 && i + j < nums.length; j++) {
                    preMax = Math.max(nums[i + j], preMax);
                    result[i + j - k + 1] = Math.max(preMax, stack.pop());
                }
            }
        }
        return result;
    }
}