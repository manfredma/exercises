/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

 */
package exe59.spiral.matrix.ii;

/**
 * @author Manfred since 2019/7/15
 */
public class Main {
    public static void main(String[] args) {
        int[][] r = new Solution().generateMatrix(3);
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
