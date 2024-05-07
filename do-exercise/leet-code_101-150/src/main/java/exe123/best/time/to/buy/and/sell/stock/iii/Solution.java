package exe123.best.time.to.buy.and.sell.stock.iii;

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int leftMin = Integer.MAX_VALUE;
        int rightMax = Integer.MIN_VALUE;


        int[] leftCMax = new int[prices.length];
        int[] rightCMax = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            leftMin = Math.min(leftMin, prices[i - 1]);
            rightMax = Math.max(rightMax, prices[prices.length - i]);

            leftCMax[i] = Math.max(leftCMax[i - 1], prices[i] - leftMin);
            rightCMax[prices.length - 1 - i] = Math.max(rightCMax[prices.length - i], rightMax - prices[prices.length - 1 - i]);
        }

        int result = leftCMax[prices.length - 1];
        for (int i = 0; i < prices.length - 1; i++) {
            result = Math.max(result, leftCMax[i] + rightCMax[i + 1]);
        }
        return result;
    }
}