package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.List;

public class Combine {

    /**
     * 77:组合:给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 1 <= n <= 20
     * 1 <= k <= n
     * @param n
     * @param k
     * @return
     */
//    List<List<Integer>> res= new ArrayList<>();
//    List<Integer> path = new ArrayList<>();
//    int k = 0;
//    public List<List<Integer>> combine(int n, int k) {
//        this.k = k;
//        dfs(n);
//        return res;
//    }
//
//    private void dfs(int i) {
//        // 剩余选择的数
//        int d = k - path.size();
//        if(d <= 0) {
//            res.add(new ArrayList<>(path));
//            return ;
//        }
//        // 遍历d个数
//        for (int j = i; j >= d; j--) {
//            path.add(j); // 选择他
//            dfs(j-1);
//            path.remove(path.size()-1);// 不选该数
//        }
//    }


    /**
     * 2.第二种方法
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1);
        return res;
    }

    private void dfs(int n, int k, int start) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return ;
        }
        // 从start开始
//        for (int i = start; i <= n; i++) {
//            // 选择该数
//            path.add(i);
//            dfs(n, k , i+1); //
//            path.remove(path.size()-1);
//        }
        // 优化: k - path.size() 表示剩余需要的数量
        // 保证for遍历有不少于k - path.size() 的长度,(这里相当于i从1开始) 所以 n - i >= k - path.size()
        // 即 i <= n - (k - path.size()) + 1
        // (额外+1是:当i = 0时, i 最多 开始从n - k + 1处开始遍历
        // n = 4, k = 3; 4 - 3 + 1 = 2 -> [2, 3, 4]
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            // 选择该数
            path.add(i);
            dfs(n, k , i+1); //
            path.remove(path.size()-1);
        }
    }

}
