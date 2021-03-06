/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */
package exe10.regular.expression.matching;

/**
 * @author Manfred since 2019/5/9
 */
public class Main {
    public static void main(String[] args) {

        test1();
        System.out.println("===================");
        test2();
    }

    private static void test1() {
        Solution solution = new Solution();
        System.out.println("solution.isMatch(\"a\", \".*..a*\")=" + solution.isMatch("a", ".*..a*"));
        System.out.println("solution.isMatch(\"ab\", \".*c\")=" + solution.isMatch("ab", ".*c"));
        System.out.println("solution.isMatch(\"aab\", \"c*a*b*\")=" + solution.isMatch("aab", "c*a*b*"));
        System.out.println("solution.isMatch(\"aa\", \"a\")=" + solution.isMatch("aa", "a"));
        System.out.println("solution.isMatch(\"aa\", \"a*\")=" + solution.isMatch("aa", "a*"));
        System.out.println("solution.isMatch(\"ab\", \".*\")=" + solution.isMatch("ab", ".*"));
        System.out.println("solution.isMatch(\"mississippi\", \"mis*is*p*.\")=" + solution.isMatch("mississippi", "mis*is*p*."));
    }

    private static void test2() {
        Solution2 solution = new Solution2();
        System.out.println("solution.isMatch(\"a\", \".*..a*\")=" + solution.isMatch("a", ".*..a*"));
        System.out.println("solution.isMatch(\"ab\", \".*c\")=" + solution.isMatch("ab", ".*c"));
        System.out.println("solution.isMatch(\"aab\", \"c*a*b*\")=" + solution.isMatch("aab", "c*a*b*"));
        System.out.println("solution.isMatch(\"aa\", \"a\")=" + solution.isMatch("aa", "a"));
        System.out.println("solution.isMatch(\"aa\", \"a*\")=" + solution.isMatch("aa", "a*"));
        System.out.println("solution.isMatch(\"ab\", \".*\")=" + solution.isMatch("ab", ".*"));
        System.out.println("solution.isMatch(\"mississippi\", \"mis*is*p*.\")=" + solution.isMatch("mississippi", "mis*is*p*."));
    }
}
