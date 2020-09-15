/*
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.

 */
package exe322.coin.change;

/**
 * @author Manfred since 2019/8/30
 */
public class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();


        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(solution.coinChange(new int[]{186, 419, 83, 408}, 6249));
        System.out.println(solution.coinChange(new int[]{2}, 3));


    }
}
