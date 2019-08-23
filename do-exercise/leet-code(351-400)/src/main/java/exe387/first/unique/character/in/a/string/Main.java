/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.

 */

package exe387.first.unique.character.in.a.string;

/**
 *
 * @author maxin
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstUniqChar("leetcode"));;
        System.out.println(solution.firstUniqChar("loveleetcode"));;
    }
}
