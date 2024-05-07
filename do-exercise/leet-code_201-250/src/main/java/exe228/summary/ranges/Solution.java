package exe228.summary.ranges;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges, and there is no integer
 * x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,2,4,5,7]<p>
 * Output: ["0->2","4->5","7"]<p>
 * Explanation: The ranges are:
 * [0,2] --> "0->2"<p>
 * [4,5] --> "4->5"<p>
 * [7,7] --> "7"<p>
 * Example 2:
 * <p>
 * Input: nums = [0,2,3,4,6,8,9]<p>
 * Output: ["0","2->4","6","8->9"]<p>
 * Explanation: The ranges are:<p>
 * [0,0] --> "0"<p>
 * [2,4] --> "2->4"<p>
 * [6,6] --> "6"<p>
 * [8,9] --> "8->9"<p>
 * Example 3:
 * <p>
 * Input: nums = []<p>
 * Output: []<p>
 * Example 4:
 * <p>
 * Input: nums = [-1]<p>
 * Output: ["-1"]<p>
 * Example 5:
 * <p>
 * Input: nums = [0]<p>
 * Output: ["0"]<p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 20 <p>
 * -2^31 <= nums[i] <= 2^31 - 1 <p>
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 */
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (null != nums && nums.length > 0) {
            Integer begin = null;
            for (int i = 0; i < nums.length; i++) {
                if (begin == null) {
                    begin = nums[i];
                    continue;
                }
                if (nums[i] != nums[i - 1] + 1) {
                    if (begin.equals(nums[i - 1])) {
                        result.add(begin + "");
                    } else {
                        result.add(begin + "->" + nums[i - 1]);
                    }
                    begin = nums[i];
                }

            }
            if (begin.equals(nums[nums.length - 1])) {
                result.add(begin + "");
            } else {
                result.add(begin + "->" + nums[nums.length - 1]);
            }
        }
        return result;
    }
}