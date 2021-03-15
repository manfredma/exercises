package exe215.kth.largest.element.in.an.array;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int[] x = new int[20001];
        for (int num : nums) {
            x[num + 10000]++;
        }
        int sum = 0;

        for (int i = x.length - 1; i >= 0; i--) {
            sum += x[i];
            if (sum >= k) {
                return i - 10000;
            }
        }
        return Integer.MIN_VALUE;
    }
}