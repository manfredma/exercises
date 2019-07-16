/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28

 */
package exe62.unique.paths;

/**
 * @author Manfred since 2019/7/16
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths(3, 2));
        System.out.println(solution.uniquePaths(7, 3));
        System.out.println(solution.uniquePaths(51, 9));
    }
}
