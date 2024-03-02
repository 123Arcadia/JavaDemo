package AlgorithmNotes.greed;

public class maxSubArray53 {
    /**
     * 53. 最大子数组和
     */

    public int maxSubArray(int[] nums) {
//        if (nums.length == 1) {
//            return nums[0];
//        }
//        int pre=Integer.MIN_VALUE;
//        int res=0;
//        for (int i = 0; i < nums.length; i++) {
//            res += nums[i];
//            if (res > pre) {
//                pre = res; // 记录
//            }
//            if (res <= 0) {
//                res = 0;
//            }
//        }
//        return res;

        // dp
        int n = nums.length;
        //dp[i] 表示nums[i]在内的上一个最大连续子数组之和
        int[] dp = new int[n];
        int sum = dp[0] = nums[0];
        // int sum = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]); // 这里没有nums[i]大就表示从nums[i]重新开始
            if (sum < dp[i]) sum = dp[i];
        }
        return sum;
    }


}
