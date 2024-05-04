package AlgorithmNotes.hot100.profit;

public class maxprofit {
    public int maxProfit(int[] prices) {
        // dp[i][0]: 没有股票
        // dp[i][1]: 有股票
        // dp[i][2]: 没有股票， 处于冻结
        // dp[n-1][0]
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
//            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1] + prices[i],  dp[i-1][2]));
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            dp[i][2] = dp[i-1][1] + prices[i];
        }
        return Math.max(dp[n-1][0], dp[n-1][2]);
    }
}
