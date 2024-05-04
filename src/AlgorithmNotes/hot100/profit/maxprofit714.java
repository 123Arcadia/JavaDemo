package AlgorithmNotes.hot100.profit;

public class maxprofit714 {

    public int maxProfit(int[] prices, int fee) {
        // dp[i][0]: 没有股票
        // dp[i][1]:
        // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i] - fee)
        // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            // 只有在卖出的时候才计入fee
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }
}
