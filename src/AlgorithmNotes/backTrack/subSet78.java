package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.List;

public class subSet78 {
    /**
     * 78. 子集
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     */

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return res;

    }

    private void dfs(int[] nums, int start) {
        res.add(new ArrayList<>(path));
        if (start >= nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.remove(path.size() - 1);
        }

    }

    //----------------------------------------------------------------
    /**
     * 第二种z； 二进制映射
     * 1:该数存在
     * 0:该数不存在
     */

    public List<List<Integer>> subsets_02(int[] nums) {
        int n = nums.length;
        //枚举 mask ∈ [0,2^n−1]
        // n =3(11) -> 1 << 3 == 8(1000)
        for (int mask = 0; mask < (1 << n); mask++) {
            path.clear();
            // 从0 ~ (1 << n)中找到mask对应的数
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    // 意味着i这个数存在
                    path.add(nums[i]);
                }
            }
            res.add(new ArrayList<>(path));
        }
        return  res;
    }
}
