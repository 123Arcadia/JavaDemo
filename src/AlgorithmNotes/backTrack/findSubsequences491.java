package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.List;

public class findSubsequences491 {
    /**
     * 491.递增子序列
     *
     * 1 <= nums.length <= 15
     * -100 <= nums[i] <= 100
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int start) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }
        int[] used = new int[201]; // -100~100 记录是否使用
        for (int i = start; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) || used[nums[i] + 100] == 1)   {
                continue;
            }
            used[nums[i] + 100] = 1;
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.remove(path.size() - 1);
            used[nums[i] + 100] = 0;
            // used不用复原
        }
    }
}
