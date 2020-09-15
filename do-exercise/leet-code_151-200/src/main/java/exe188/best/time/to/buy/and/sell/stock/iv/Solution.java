package exe188.best.time.to.buy.and.sell.stock.iv;

class Solution {
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int n = prices.length;
        if (k >= n/2) {
        	int maxPro = 0;
        	for (int i = 1; i < n; i++) {
        		if (prices[i] > prices[i-1]) 
        			maxPro += prices[i] - prices[i-1];
        	}
        	return maxPro;
        }
        
        int[][] dp = new int[k + 1][2];
        for(int i = 0; i <= k; i++){
            dp[i][0] = Integer.MIN_VALUE;
            dp[i][1] = 0;
        }
        for(int curPrice : prices){
            for(int i = 1; i <= k; i++){
                if(dp[i][0] < dp[i-1][1] - curPrice)
                    dp[i][0] = dp[i-1][1] - curPrice;
                if(dp[i][1] < dp[i][0] + curPrice)
                    dp[i][1] = dp[i][0] + curPrice;
            }
        }
        return dp[k][1];
    }
}