package AlgorithmNotes.hot100.profit;

import java.util.Arrays;

public class maxprofit188 {

    private int[] prices;
    private int[][][] memo;

    public int maxProfit(int k, int[] prices) {
//        this.prices = prices;
//        int n = prices.length;
//        // 前n个数中进行了k笔交易（1：持有股票， 0：没有股票）
//        this.memo = new int[n][k + 1][2];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < k + 1; j++) {
//                Arrays.fill(memo[i][j], -1); // -1表示没有计算
//            }
//        }
//        // 我们求的是0..n-1个数进行了k个交易,此时没有股票的最大利润
//        return dfs(n - 1, k, 0);

        //优化成递推
        int n = prices.length;
        // 表示0..n的k次交易, 1有股票，0没有
        int[][][] dp = new int[n + 1][k + 2][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k + 2; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE / 2);
            }
        }
        for (int i = 0; i < k + 1; i++) {
            dp[0][i][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < k + 2; j++) {
                dp[i + 1][j][0] = Math.max(dp[i][j][0], dp[i][j-1][1] + prices[i]);
                dp[i + 1][j][1] = Math.max(dp[i][j][1], dp[i][j-1][0] - prices[i]);
            }
        }
        return dp[n][k+1][0];
    }

    private int dfs(int i, int j, int hold) {
        if (i < 0) return Integer.MIN_VALUE / 2;
        // 如果hole=1代表dfs代表错误, hole=0代表起始是从没有股票开始的
        if (j < 0) return hold == 1 ? Integer.MIN_VALUE / 2 : 0;
        if (memo[i][j][hold] != -1) {
            return memo[i][j][hold];
        }
        if (hold == 1) {
            return memo[i][j][hold] = Math.max(dfs(i - 1, j, 1), dfs(i - 1, j - 1, 0) - prices[i]);
        }
        return memo[i][j][hold] = Math.max(dfs(i - 1, j, 0), dfs(i - 1, j, 0) + prices[i]);
    }

}
