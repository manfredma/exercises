/*

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
 */
package exe44.wildcard.matching;

import java.util.regex.Pattern;

/**
 * @author Manfred since 2019/5/31
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.isMatch(
                "aaaaaabbaabaaaaabababbabbaababbaabaababaaaaabaaaabaaaabababbbabbbbaabbababbbbababbaaababbbabbbaaaaaaabbaabbbbababbabbaaabababaaaabaaabaaabbbbbabaaabbbaabbbbbbbaabaaababaaaababbbbbaabaaabbabaabbaabbaaaaba",
                "*bbb*a*abb*b*a*bbbbaab*b*aaba*a*b*a*abb*aa*b*bb*abbbb*b*bbbabaa*b*ba*a*ba*b*a*a*aaa") + ", expect = true");
        System.out.println(solution.isMatch("abcd", "abc*d") + ", expect = true");
        System.out.println(solution.isMatch("c", "*?*") + ", expect = true");
        System.out.println(solution.isMatch("a", "a*") + ", expect = true");
        System.out.println(solution.isMatch("mississippi", "m??*ss*?i*pi") + ", expect = false");
        System.out.println(solution.isMatch("aaaa", "**a") + ", expect = true");
        System.out.println(solution.isMatch("abefcdgiescdfimde", "ab*cd?i*de") + ", expect = true");
        System.out.println(solution.isMatch("adceb", "*a*b") + ", expect = true");
        System.out.println(solution.isMatch("aa", "a") + ", expect = false");
        System.out.println(solution.isMatch("aa", "*") + ", expect = true");
        System.out.println(solution.isMatch("cb", "?a") + ", expect = false");
        System.out.println(solution.isMatch("acdcb", "a*c?b") + ", expect = false");
        Pattern p2 =  Pattern.compile("*bbb*a*abb*b*a*bbbbaab*b*aaba*a*b*a*abb*aa*b*bb*abbbb*b*bbbabaa*b*ba*a*ba*b*a*a*aaa");

    }
}
