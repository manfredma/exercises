/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */
package exe200.number.of.islands;

/**
 * @author manfred on 2019/9/1.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] c = new char[4][];
        c[0] = "11110".toCharArray();
        c[1] = "11010".toCharArray();
        c[2] = "11000".toCharArray();
        c[3] = "00000".toCharArray();
        System.out.println(solution.numIslands(c));

        c[0] = "11000".toCharArray();
        c[1] = "11000".toCharArray();
        c[2] = "00100".toCharArray();
        c[3] = "00011".toCharArray();
        System.out.println(solution.numIslands(c));
    }

}
