package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.List;

public class Combine39 {
    /**
     * 39. 组合总和
     *
     * 提示：
     * 1 <= candidates.length <= 30
     * 2 <= candidates[i] <= 40
     * candidates 的所有元素 互不相同
     * 1 <= target <= 40
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> state = new ArrayList<>();
        // Arrays.sort(candidates);
        int start = 0;
        List<List<Integer>> res = new ArrayList<>();
        backTrack(state, candidates, target, start, res);
        return res;
    }

    /**
     *
     * @param state
     * @param candidates
     * @param target 目标和
     * @param start
     * @param res
     */
    private void backTrack(List<Integer> state, int[] candidates, int target, int start, List<List<Integer>> res) {
        if (target < 0) {
            // 不符合
            return ;
        }
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 在此剪枝，需要排序
            // if (target - candidates[i] < 0) {
            //     break; // 不是continue，因为小到大排序
            // }
            state.add(candidates[i]);
            backTrack(state, candidates, target - candidates[i], i, res);
            state.remove(state.size()-1);
        }
    }


}
