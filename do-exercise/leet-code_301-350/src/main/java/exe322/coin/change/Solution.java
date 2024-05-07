package exe322.coin.change;

import java.util.Arrays;

/*
递归遍历版本
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        return doCoinChange(coins, amount, coins.length - 1);
    }

    private int doCoinChange(int[] coins, int amount, int index) {
        int result = Integer.MAX_VALUE;
        int loop = amount / coins[index];
        amount = amount % coins[index];
        if (amount == 0) {
            return loop;
        }
        if (index > 0) {
            int subIndex = index - 1;
            for (int i = 0; i <= loop; i++) {
                int subResult = doCoinChange(coins, amount, subIndex);
                if (subResult != -1) {
                    result = Math.min(loop - i + subResult, result);
                }
                amount = amount + coins[index];
            }
        }

        if (result == Integer.MAX_VALUE) {
            return -1;
        } else {
            return result;
        }
    }
}
