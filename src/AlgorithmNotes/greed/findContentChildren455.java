package AlgorithmNotes.greed;

import java.util.Arrays;

public class findContentChildren455 {
    /**
     * 455. 分发饼干
     */

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        // 大饼干先胃口尺寸大的孩子
        int cnt = 0; // 满足的数量
        int n =g.length;
        int sidx = s.length-1;
        for (int i = n-1; i >= 0; i--) {
            if (g[i] <= s[sidx]) {
                cnt++;
                sidx--;
            }
        }
        return cnt;
    }
}
