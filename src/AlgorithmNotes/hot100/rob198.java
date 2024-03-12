package AlgorithmNotes.hot100;

public class rob198 {

    public int rob(int[] nums) {
        // 分奇偶: 各自就是股票问题

//        int n = nums.length;
//        if (n == 0) return 0;
//        if (n == 1) return nums[0];
//        int[] dp = new int[n];
//        dp[0] = nums[0];
//        dp[1] = Math.max(nums[0],nums[1]);
//        for (int i = 2; i < n; i++) {
//            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
//        }
//        return dp[n-1];

        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int pre = nums[0], cur = Math.max(nums[0],nums[1]);
        int maxProfit = 0;
        for (int i = 2; i < n; i++) {
            int max = Math.max(pre + nums[i], cur);
            maxProfit = Math.max(max, maxProfit);
            pre = cur;
            cur = maxProfit;
        }
        return maxProfit;
    }
}
