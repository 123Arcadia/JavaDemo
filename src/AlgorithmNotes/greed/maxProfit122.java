package AlgorithmNotes.greed;

public class maxProfit122 {
    /**
     * 122. 买卖股票的最佳时机 II
     */

    int[] prices;
    int[][] memo;

    /**
     * 单独交易日: 今天买，明天卖
     * 连续上涨: 第一天买， 最后一天卖出
     * 连续下降: 不买
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // s[3] - s[1] = s[3] - s[2] + s[2] - s[1]
//        int res = 0;
//        for (int i = 1; i < prices.length; i++) {
//            res += Math.max(0, prices[i] - prices[i - 1]);
//        }
//        return res;

        int n = prices.length;
        int[][] dp = new int[n][2];
        // dp[i][0]: 不买入
        // dp[i][1]: 买入
        dp[0][0] = 0;
        dp[0][1] -= prices[0];
        for (int i = 1; i < n; i++) {
            // i-1如果买入， i抛出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * dfs(暴力==回溯)
     */
    int res = 0;

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int res = 0;
        if (n < 2) {
            return 0;
        }
        dfs(prices, 0, n, 0, res);
        return res;
    }

    /**
     * @param prices
     * @param idx
     * @param n      当idx=n时，停止dfs
     * @param status 0 表示不持有股票，1表示持有股票
     * @param profit 收益
     */
    private void dfs(int[] prices, int idx, int n, int status, int profit) {
        if (idx == n) {
            res = Math.max(res, profit);
            return;
        }
        // 一直不买入
        dfs(prices, idx + 1, n, status, profit);
        if (status == 0) {
            dfs(prices, idx + 1, n, status + 1, profit);
        } else {
            // 1-1=0
            dfs(prices, idx + 1, n, status - 1, profit);

        }
    }


}
