package AlgorithmNotes.hot100.profit;

public class maxprofit122 {

    /**
     * 122. 买卖股票的最佳时机 II
     */
    public int maxProfit(int[] prices) {
//        int ans = 0;
//        for (int i = 1; i < prices.length; i++) {
//            ans += Math.max(ans, prices[i] - prices[i-1]);
//        }
//        return ans;

        // dp[i][0]: di天结束的收益(没有股票)
        // dp[i][1]:有股票
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][1] + prices[i], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0] - prices[i], dp[i-1][1]);
        }
        return dp[n-1][0];
    }


}
