/*
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
 */
package exe41.first.missing.positive;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(s.firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(s.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }
}
