/*
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"

 */
package exe168.excel.sheet.column.title;

/**
 * @author manfred on 2019/9/4.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // AB
        System.out.println(solution.convertToTitle(28));
        // AJHX
        System.out.println(solution.convertToTitle(24568));
        // B
        System.out.println(solution.convertToTitle(2));
        // ZY
        System.out.println(solution.convertToTitle(701));
        // AZ
        System.out.println(solution.convertToTitle(52));
    }
}
