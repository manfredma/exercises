package exe220.contains.duplicate.iii;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and two integers k and t, return true if there are two distinct
 * indices i and j in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 2 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^4
 * 0 <= t <= 2^31 - 1
 */
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) {
            return false;
        }
        if (t == 0) {
            Map<Integer, Integer> num2index = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (num2index.containsKey(nums[i]) && i - num2index.get(nums[i]) <= k) {
                    return true;
                }
                num2index.put(nums[i], i);
            }
            return false;
        } else {
            for (int i = 1; i < nums.length; i++) {
                for (int j = Math.max((i - k), 0); j < i; j++) {
                    if (Math.abs((long)nums[i] - nums[j]) <= (long)t) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}