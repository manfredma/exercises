/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
 */
package exe97.interleaving.string;

/**
 * @author manfred on 2019/8/25.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(solution.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
}
