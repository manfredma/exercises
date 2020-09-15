/*

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0

 */
package exe201.bitwise.and.of.numbers.range;

/**
 * @author manfred on 2019/9/17.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rangeBitwiseAnd(5, 7));
        System.out.println(solution.rangeBitwiseAnd(5, 6));
        System.out.println(solution.rangeBitwiseAnd(2, 4));
        System.out.println(solution.rangeBitwiseAnd(0, 1));
    }
}
