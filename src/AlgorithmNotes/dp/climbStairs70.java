package AlgorithmNotes.dp;

import org.junit.Test;

public class climbStairs70 {

    /**
     * 70/爬楼梯
     */

    public int climbStairs(int n) {
        // dp[i][0]: i阶是前一步是垮了1阶
        // dp[i][1]: i阶是前一步是垮了2阶
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp= new int[n+1]; // 记录到达i台阶的方法数
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];

//        int[] dp =new int[2];
//        dp[0] = 1;
//        dp[1] = 2;
//        for (int i = 3; i <= n; i++) {
//            int t = dp[0] + dp[1];
//            dp[0] = dp[1];
//            dp[1] = t;
//        }
//        return dp[1];

        //矩阵快速幂

    }

    @Test
    public void test() {
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
    }
}
