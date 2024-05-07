/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */
package exe5.longest.palindromic.substring;

/**
 * @author Manfred since 2019/5/7
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("abcbcbc"));
        System.out.println(solution.longestPalindrome("babad"));
        System.out.println(solution.longestPalindrome("cbbd"));

        Solution2 solution2 = new Solution2();
        System.out.println(solution2.longestPalindrome("bb"));
        System.out.println(solution2.longestPalindrome("abcbcbc"));
        System.out.println(solution2.longestPalindrome("babad"));
        System.out.println(solution2.longestPalindrome("cbbd"));

        Solution3 solution3 = new Solution3();
        System.out.println(solution3.longestPalindrome("bb"));
        System.out.println(solution3.longestPalindrome("abcbcbc"));
        System.out.println(solution3.longestPalindrome("babad"));
        System.out.println(solution3.longestPalindrome("cbbd"));
    }
}
