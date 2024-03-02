package AlgorithmNotes.test;

public class test {

    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        // 我们从次低一层的节点遍历，对其子节点[x, y]，只要加之子节点值相等即可
        for (int i = n / 2; i > 0; i--) { // 从最后一个非叶节点开始算
            ans += Math.abs(cost[i*2-1] - cost[i * 2]);// 两个子节点变成一样的
            // 这里第i哥节点，是cost[i-1]
            // 累加路径，但是[i*2]和[i*2-1]的值上轮已经相等,加上对原值比大小不影响
            cost[i-1] += Math.max(cost[i*2-1], cost[i*2]); // 累加路径，选取路径最大的为准
        }
        return ans;
    }


    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp=  new int[n+1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1] ,dp[i-2]+cost[i-2]);
        }
        return dp[n];
    }


}
