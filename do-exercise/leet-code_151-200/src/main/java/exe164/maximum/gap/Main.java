/*

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

Example 1:

Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
Example 2:

Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
Note:

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
Try to solve it in linear time/space.

 */
package exe164.maximum.gap;

/**
 * @author Manfred since 2019/9/16
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumGap(new int[]{1, 1, 1, 1}));
        System.out.println(solution.maximumGap(new int[]{1, 1, 1, 1, 1, 5, 5, 5, 5, 5}));
        System.out.println(solution.maximumGap(new int[]{3, 6, 9, 1}));
        System.out.println(solution.maximumGap(new int[]{10}));
    }
}
