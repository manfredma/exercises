/*

Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.

 */
package exe179.largest.number;

/**
 * @author manfred on 2019/9/16.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestNumber(new int[]{3, 43, 48, 94, 85, 33, 64, 32, 63, 66}));
        System.out.println(solution.largestNumber(new int[]{10, 2}));
        System.out.println(solution.largestNumber(new int[]{3, 30, 34, 5, 9}));
    }
}
