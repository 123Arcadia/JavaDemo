package AlgorithmNotes.dp;

import org.junit.Test;

public class fib {

    // 0 1 1 2 3 5 8 13 21 34 55 89 144 233
    public int fib(int n) {
//        if (n == 1 || n == 2) {
//            return 1;
//        }
//        return fib(n-1) + fib(n-2);


        //dp 做法:0 <= n <= 30
        if (n == 0) return 0;
        if (n <= 2) return 1;
        int[] dp = new int[2];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            int t  = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = t;
        }
        return dp[1];
    }


    @Test
    public void test () {
        System.out.println(fib(10));
        System.out.println(fib(4));
    }
}
