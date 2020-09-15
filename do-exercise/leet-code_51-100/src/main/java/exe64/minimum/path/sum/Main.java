/*

Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

 */

package exe64.minimum.path.sum;


/**
 * @author Manfred since 2019/7/16
 */
public class Main {
    public static void main(String[] args) {
        int[][] x = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        Solution solution = new Solution();
        System.out.println(solution.minPathSum(x));
    }
}
