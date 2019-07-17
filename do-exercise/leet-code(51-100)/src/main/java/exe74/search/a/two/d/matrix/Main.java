/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false

 */
package exe74.search.a.two.d.matrix;

/**
 * @author Manfred since 2019/7/17
 */
public class Main {
    public static void main(String[] args) {
        int[][] x = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        Solution solution = new Solution();
        System.out.println(solution.searchMatrix(x, 3));
        System.out.println(solution.searchMatrix(x, 13));

        x = new int[][]{
                {1, 1}
        };

        System.out.println(solution.searchMatrix(x, 2));
        x = new int[][]{
                {1, 3}
        };
        System.out.println(solution.searchMatrix(x, 1));
        System.out.println(solution.searchMatrix(x, 3));

    }
}
