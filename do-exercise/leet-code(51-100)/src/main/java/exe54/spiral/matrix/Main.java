/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */

package exe54.spiral.matrix;

/**
 * @author Manfred since 2019/7/15
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.spiralOrder(new int[][]{
                {3},
                {2}
        }));
        System.out.println(solution.spiralOrder(new int[][]{
                {6, 9, 7}
        }));
        System.out.println(solution.spiralOrder(new int[][]{
                {0, 1},
                {2, 3}
        }));
        System.out.println(solution.spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        }));
        System.out.println(solution.spiralOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));

    }
}
