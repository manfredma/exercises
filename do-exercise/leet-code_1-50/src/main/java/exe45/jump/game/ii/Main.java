/*

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.

 */
package exe45.jump.game.ii;

/**
 * @author Manfred since 2019/7/3
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.jump(new int[]{2, 3, 1, 1, 4}));
        int[] i = new int[25002];
        for (int j = 0; j < 25000; j++) {
            i[j] = 25000 - j;
        }
        i[25000] = 1;
        i[25001] = 0;
        System.out.println(solution.jump(i));
    }
}
