/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

 */
package exe131.palindrome.partitioning;

/**
 * @author manfred on 2019/9/8.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partition("cdd"));
        System.out.println(solution.partition("aab"));
    }
}
