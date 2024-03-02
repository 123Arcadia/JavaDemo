package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Comone47 {
    /**
     * 40.组合总和II
     * <p>
     * 提示:
     * 1 <= candidates.length <= 100
     * 1 <= candidates[i] <= 50
     * 1 <= target <= 30
     */

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res;
    }

    /**
     * @param candidates
     * @param target 目标和
     * @param start
     */
    private void dfs(int[] candidates, int target, int start) {
        if (target < 0) return ;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return ;
        };
        for (int i = start; i < candidates.length; i++) {
            // 选择
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1);
            path.remove(path.size()-1);
        }
    }
}
