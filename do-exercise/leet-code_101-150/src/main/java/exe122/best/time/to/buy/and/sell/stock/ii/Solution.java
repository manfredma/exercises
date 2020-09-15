package exe122.best.time.to.buy.and.sell.stock.ii;

class Solution {
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }
        int sum = 0;
        int buy = -1;
        boolean isBuy = true;
        for (int i = 0; i < prices.length - 1; i++) {
            if (isBuy) {
                if (prices[i + 1] > prices[i]) {
                    buy = prices[i];
                    isBuy = false;
                }
            } else {
                if (prices[i + 1] < prices[i]) {
                    sum += prices[i] - buy;
                    isBuy = true;
                }
            }
        }
        if (!isBuy) {
            sum += prices[prices.length - 1] - buy;
        }
        return sum;
    }
}