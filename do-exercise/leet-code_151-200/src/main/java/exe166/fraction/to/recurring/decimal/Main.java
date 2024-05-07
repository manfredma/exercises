/*

Given two integers representing the numerator and denominator of a fraction,
return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"

 */
package exe166.fraction.to.recurring.decimal;

/**
 * @author Manfred since 2019/9/16
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fractionToDecimal(-1, -2147483648));
        System.out.println(solution.fractionToDecimal(7, -12));
        System.out.println(solution.fractionToDecimal(-50, 8));
        System.out.println(solution.fractionToDecimal(1, 6));
        System.out.println(solution.fractionToDecimal(2, 3));
        System.out.println(solution.fractionToDecimal(1, 2));
        System.out.println(solution.fractionToDecimal(2, 1));
    }
}
