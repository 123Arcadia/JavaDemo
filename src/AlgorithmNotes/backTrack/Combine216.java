package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.List;

public class Combine216 {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    /**
     * 216. 组合总和 III
     *
     * 2 <= k <= 9
     * 1 <= n <= 60
     */

    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(n, k, 1, 0);
        return res;
    }

    /**
     * sum 当前的和
     * n 目标和
     */
    private void dfs(int n, int k, int start, int sum) {
        if (sum > n) {
            return ;
        }
        if (k == path.size()) {
            if (sum == n) {
                res.add(new ArrayList<>(path));
            }
            return ; // 不管sum、n是否相等，数量都为k必须停止遍历
        }
        // 数据要求1 ~ 9
        for (int i = start; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            dfs(n, k, i + 1, sum + i);
            path.remove(path.size()-1);
        }
    }
}
