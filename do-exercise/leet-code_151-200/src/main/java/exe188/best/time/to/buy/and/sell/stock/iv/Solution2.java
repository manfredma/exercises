package exe188.best.time.to.buy.and.sell.stock.iv;

class Solution2 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        if (k >= prices.length / 2) {
            int maxPro = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxPro += prices[i] - prices[i - 1];
                }
            }
            return maxPro;
        }
        int[][] localProfit = new int[prices.length][k + 1];
        int[][] globalProfit = new int[prices.length][k + 1];

        // 只有一个元素是没有交易意义的，所以从第二个元素开始判断（下标为0）
        for (int i = 1; i < prices.length; i++) {
            // j 表示第j次交易，没有交易就没有收益，所以 j == 0 的时候，值为0即可。
            // 循环中就不需要判断 j==0 这个边界条件
            for (int j = 1; j <= k; j++) {
                // 第 j 天进行交易
                int profit = Math.max(prices[i] - prices[i - 1], 0);
                localProfit[i][j] = Math.max(globalProfit[i - 1][j - 1] + profit, localProfit[i - 1][j] + prices[i] - prices[i - 1]);
                // 无论第j天是否交易，最大值 = max(第j天不进行交易的最大值,第j天进行交易的最大值)
                globalProfit[i][j] = Math.max(globalProfit[i - 1][j], localProfit[i][j]);
            }
        }
        return globalProfit[prices.length - 1][k];
    }
}