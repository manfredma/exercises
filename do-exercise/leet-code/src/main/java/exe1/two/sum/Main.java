/*
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:

 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
package exe1.two.sum;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[]{
                -1, -1
        };

        Map<Integer, Integer> checked = new HashMap<Integer, Integer>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (checked.containsKey((target - nums[i]))) {
                result[0] = checked.get(target - nums[i]);
                result[1] = i;
            } else {
                checked.put(nums[i], i);
            }

        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int i : result) {
            System.out.println(i);
        }
    }
}