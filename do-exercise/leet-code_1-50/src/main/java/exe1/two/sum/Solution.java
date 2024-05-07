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