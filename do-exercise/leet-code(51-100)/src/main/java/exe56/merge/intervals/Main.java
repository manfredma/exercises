/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

 */
package exe56.merge.intervals;

/**
 * @author Manfred since 2019/7/15
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] r = solution.merge(new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        });
        print(r);

    }

    private static void print(int[][] r) {
        System.out.println();
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[i].length; j++) {
                System.out.print(r[i][j] + " ");
            }
            System.out.println();
        }
    }
}
