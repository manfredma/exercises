/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 */
package exe85.maximal.rectangle;

/**
 * @author Manfred since 2019/7/19
 */
public class Main {
    public static void main(String[] args) {
        char[][] x = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        Solution solution = new Solution();
        System.out.println(solution.maximalRectangle(x));
    }
}
