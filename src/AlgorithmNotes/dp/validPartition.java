package AlgorithmNotes.dp;

import org.junit.Test;

public class validPartition {

    /**
     * 2369. 检查数组是否存在有效划分
     * @param nums
     * @return
     */
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        // nums 的前 i 个数能否有效划分
        boolean[] dp = new boolean[n];

        dp[0] = false;
        dp[1] = nums[0] == nums[1];
        if (n ==2) return dp[1];
        dp[2] = dp[1] && nums[1] == nums[2] || nums[0] == nums[1]-1 && nums[0] == nums[2]-2;
        for (int i = 3; i < n; i++) {
            if (i >= 2) {
                dp[i] = dp[i-2] && (nums[i-1] == nums[i]);
            }
            if (i >= 3) {
                dp[i] =dp[i] || (dp[i-3] && threeNum(nums[i], nums[i-1], nums[i-2]));

            }
        }
        return dp[n-1];
    }
    private boolean threeNum(int num, int num1, int num2) {
        return (num==num1 && num1 == num2) || (num == num1 +1 && num1 == num2 + 1);
    }

    @Test
    public void test1() {
        int[] nums = {993335,993336,993337,993338,993339,993340,993341};
        System.out.println(validPartition(nums));
    }
}
