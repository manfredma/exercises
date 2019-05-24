/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 */
package exe34.find.first.and.last.position.of.element.in.sorted.array;

/**
 * @author Manfred since 2019/5/24
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        findOnce(solution, 3);
        findOnce(solution, 5);
        findOnce(solution, 6);
        findOnce(solution, 7);
        findOnce(solution, 8);
        findOnce(solution, 10);
        findOnce(solution, 12);
    }

    private static void findOnce(Solution solution, int i2) {
        int[] r = solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, i2);
        for (int i = 0; i < r.length; i++) {
            System.out.print(r[i] + "->");
        }
        System.out.println();
    }

}
