/*
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list,
and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.

 */
package exe66.plus.one;

/**
 * @author Manfred since 2019/7/16
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] x = solution.plusOne(new int[]{1, 2, 3});
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + ", ");
        }
        System.out.println();
        x = solution.plusOne(new int[]{4, 3, 2, 1});
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + ", ");
        }
        System.out.println();
    }
}
