package AlgorithmNotes.backTrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class telephone17 {

    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    // 对应 0 - 9 按键
    String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return res;
        char[] chars = digits.toCharArray();
        int n = chars.length;
        dfs(n, chars, 0);
        return res;

    }

    private void dfs(int n, char[] chars, int start) {
        if (start >= n) {
            res.add(path.toString());
            return ;
        }
        String str = map[chars[start] - '0'];
        for (int i = 0; i < str.length(); i++) {
            path.append(str.charAt(i));
            dfs(n, chars, start + 1);
            path.deleteCharAt(path.length()-1);
        }
    }


    /**
     * 17. 电话号码的字母组合
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     * 
     * 字典序
     */
//    public List<String> letterCombinations(String digits) {
//        if (digits == null || digits.length() <= 0) {
//            return res;
//        }
//        char[] chars = digits.toCharArray();
//        int n = chars.length;
//        dfs(n, chars, 0); // 从0开始map遍历
//        return res;
//    }
//
//    private void dfs(int n, char[] chars, int start) {
//        if (start >= chars.length) {
//            res.add(path.toString());
//            return ;
//        }
//        // 从chars选择: start遍历的是chars
//        String str = map[chars[start] - '0'];
//        // i遍历的是str(两者的索引是对应按键的)
//        for (int i = 0; i < str.length(); i++) {
//            // 选择
//            path.append(str.charAt(i));
//            dfs(n, chars, start + 1); // 这里不是i + 1,因为是从chars遍历
//            path.deleteCharAt(path.length() - 1);
//        }
//    }

    @Test
    public void test() {
        String digits = "23";
        System.out.println(Arrays.toString(digits.toCharArray()));
        //[2, 3]
    }
}
