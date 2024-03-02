package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sortAll {
    /**
     * 46.全排列
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();


    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, 0, used);
        return res;
    }

    private void dfs(int[] nums, int start, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
//            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i-1] || used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums, i + 1, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }


    /**
     * 47. 全排列 II
     * <p>
     * 可包含重复数字
     */
//    List<List<Integer>> res = new ArrayList<>();
//    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs1(nums, 0, used);
        return res;
    }

    private void dfs1(int[] nums, int start, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i-1] || used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums, i + 1, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
