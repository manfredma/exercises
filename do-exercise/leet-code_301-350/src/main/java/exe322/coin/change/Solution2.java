package exe322.coin.change;

import java.util.Arrays;

/*
动态规划版本
 */
class Solution2 {
    public int coinChange(int[] coins, int amount) {
        int[] result = new int[amount + 1];
        Arrays.fill(result, -1);
        result[0] = 0;
        Arrays.sort(coins);
        result[coins[0]] = 1;
        for (int i = coins[0] + 1; i <= amount; i++) {
            int candidate = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                int index = i - coins[j];
                if (index >= 0 && result[index] != -1) {
                    candidate = Math.min(candidate, result[i - coins[j]] + 1);
                }
            }
            result[i] = candidate == Integer.MAX_VALUE ? -1 : candidate;
        }
        return result[amount];
    }
}
