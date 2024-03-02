package AlgorithmNotes.greed;

import java.util.ArrayList;
import java.util.List;

public class partitionLabels763 {

    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];// 记录字符最后出现的位置,
        int len = s.length();
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i)-'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < len; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']); // 片段位置end必须大于等于最后一个字母出现的位置
            if (i == end) {
                // 找到一个片段
                start = end+1;
                res.add(end-start+1);
            }
        }
        return res;
    }
}
