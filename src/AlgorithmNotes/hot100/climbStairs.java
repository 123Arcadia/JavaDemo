package AlgorithmNotes.hot100;

public class climbStairs {
    /**
     * 70. 爬楼梯
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //dp[i] = dp[i-1] + dp[i-2]
        //dp[0] = 0;
        //dp[1] = 1;
        if (n == 1) return 1;
        if (n == 2) return 2;
        // dp[n]登上第n个台阶
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }





}
