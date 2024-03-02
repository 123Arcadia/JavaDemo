package AlgorithmNotes.kmp;

import org.junit.Test;

import java.util.*;

public class KmpTest {
    /**
     * 构造模式串 pattern 的最大匹配数表
     *
     * 所有要与甲匹配的字符串（称之为模式串），必须先自身匹配：对每个子字符串 [0...i]，算出其「相匹配的真前缀与真后缀中，最长的字符串的长度」。
     * @param pattern
     * @return
     */
    public int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLen = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLen[maxLength - 1];
                // 一直不匹配，就会推到maxMatchLen[0] = 0
            }
            if (pattern.charAt(maxLength) == pattern.charAt(i)) {
                maxLength++;
            }
            maxMatchLen[i] = maxLength;
        }
        return maxMatchLen;
    }

    /**
     * kmp:
     * @param text 文本
     * @param pattern 模式串
     * @return 返回所有匹配的位置开头
     */
    public List<Integer> search(String text, String pattern) {
        List<Integer> positions = new ArrayList<>(text.length());
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count-1];
                // 不匹配就退回
            }
            if (pattern.charAt(count) == text.charAt(i)) {
                count ++;
            }
            if (count == pattern.length()) {
                // 匹配模式串全部
                positions.add(i - pattern.length() + 1);
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }

    /**
     * leetCode[3036]: {https://leetcode.cn/problems/number-of-subarrays-that-match-a-pattern-ii/description/}
     * @param nums
     * @param pattern
     * @return
     */
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        // 把nums转换为长度为n-1的映射1,0,-1的数组
        // 先求出next数组
//        int m = pattern.length;
//        int[] next = new int[m];
//        int cnt = 0;
//        for (int i = 1; i < m; i++) {
//            int v = pattern[i];
//            while (cnt > 0 && pattern[cnt] != v) {
//                cnt = next[cnt-1];
//            }
//            if (v == pattern[cnt]) {
//                cnt++;
//            }
//            next[i] = cnt;
//        }
//        int ans = 0;
//        cnt=0;
//        for (int i = 1; i < nums.length; i++) {
//            int v = Integer.compare(nums[i], nums[i-1]);
//            while (cnt>0 && pattern[cnt] != v) {
//                cnt = next[cnt-1];
//            }
//            if (pattern[cnt] == v) {
//                cnt++;
//            }
//            if (cnt == m) {
//                ans++;
//                cnt = next[cnt-1];
//            }
//        }
//        return ans;

        /**
         * Z 函数（扩展 KMP）
         */



        return 0;
    }

    @Test
    public void test01() {
//        String str = "abbaab";
        String str = "abababzababab";
        int[] next = calculateMaxMatchLengths(str);
        System.out.println(Arrays.toString(next));
        //[0, 0, 0, 1, 1, 2]
        //"abababzababab":[0, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 6]


        //**对每个子字符串 [0...i]**，算出其「相匹配的真前缀与真后缀中，最长的字符串的长度」
        // 把[0...i]的每个子字符串的前后缀列出来，找其最大匹配的字符串长度即可
        //a : 0
        //"ab":0
        //"aba":1
        // 前: a ab
        // 后: a ba
        //"abab":2
        // 前: a ab aba
        // 后: a ab bab
        //ababa:3
        // 前: a ab aba abab
        // 后: a ba aba baba
        // ababab:4
        // 前: a ab aba abab ababa
        // 后: a ab bab abab babab
        // abababz:0
        // 前: a ab aba abab ababa ababab
        // 后: z bz abz babz ababz bababz
        //...

        //最后abababzababab:6
        // 前: a ab aba abab ababa ababab abababz abababza abababzab abababzaba abababzabab abababzababa
        // 后: a ab bab abab babab ababab zababab bzababab abzababab babzababab ababzababab bababzababab

        // 所有next: [0, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 6]
    }

}
