/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 */
package exe149.max.points.on.a.line;

/**
 * @author manfred on 2019/9/13.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
        System.out.println(solution.maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        // [[1,1],[1,1],[2,3]]
        System.out.println(solution.maxPoints(new int[][]{{1, 1}, {1, 1}, {2, 3}}));
        // [[0,0],[1,65536],[65536,0]]
        System.out.println(solution.maxPoints(new int[][]{{0, 0}, {1, 65536}, {65536, 0}}));
    }
}
