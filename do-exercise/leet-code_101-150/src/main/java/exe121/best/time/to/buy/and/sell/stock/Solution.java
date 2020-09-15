package exe121.best.time.to.buy.and.sell.stock;

class Solution {
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }
        int leftMin = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            leftMin = Math.min(prices[i - 1], leftMin);
            if (prices[i] > leftMin) {
                maxProfit = Math.max(prices[i] - leftMin, maxProfit);
            }
        }
        return maxProfit;
    }
}