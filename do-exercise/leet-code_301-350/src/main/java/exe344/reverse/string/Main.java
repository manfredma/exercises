/*
Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array,
you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.



Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]

 */
package exe344.reverse.string;

import java.util.Arrays;

/**
 * @author manfred on 2019/9/1.
 */
public class Main {
    public static void main(String[] args) {
        char[] input = new char[] {'h','e','l','l','o'};
        new Solution().reverseString(input);
        System.out.println(Arrays.toString(input));
    }
}
