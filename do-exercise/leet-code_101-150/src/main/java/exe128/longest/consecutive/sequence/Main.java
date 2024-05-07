/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

 */
package exe128.longest.consecutive.sequence;

import java.util.Arrays;

/**
 * @author manfred on 2019/9/7.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestConsecutive(new int[]{
                4, 0, -4, -2, 2, 5, 2, 0, -8, -8, -8, -8, -1, 7, 4, 5, 5, -4, 6, 6, -3}));
        System.out.println(solution.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        int[] x = new int[]{
                4, 0, -4, -2, 2, 5, 2, 0, -8, -8, -8, -8, -1, 7, 4, 5, 5, -4, 6, 6, -3};
        Arrays.sort(x);
        System.out.println(Arrays.toString(x));
    }
}
