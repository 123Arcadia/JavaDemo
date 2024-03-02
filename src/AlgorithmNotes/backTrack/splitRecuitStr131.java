package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.List;

public class splitRecuitStr131 {
    /**
     * 131. 分割回文串
     * 1 <= s.length <= 16
     * s 仅由小写英文字母组成
     */
    private  List<List<String>> res = new ArrayList<>();
    private  List<String> path = new ArrayList<>();
    private String s;

    /**
     * O(n2^n)
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        this.s = s;
//        dfs(0, 0);
        dfs1(0);
        return res;
    }
    // start 表示当前这段回文子串的开始位置
    private void dfs(int i, int start) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path));
            return ;
        }
        // 不选
        if (i < s.length() - 1) {
            dfs(i + 1, start);
        }
        // 是回文: 选
        if (isPalindrome(start, i)) {
            path.add(s.substring(start, i + 1));
            dfs(i + 1, i + 1); // 下一个子串从 i+1 开始
            path.remove(path.size() - 1);

        }
    }

    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 第二种dfs:
     */
    private void dfs1(int i) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path));
            return ;
        }
        // j是从i开始向后遍历
        // i 是借j + 1不断向后遍历
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(i, j)) {
                path.add(s.substring(i, j + 1));
                dfs1(j + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
