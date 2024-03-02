package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.List;

public class reBuildIPAddress93 {
    /**
     * 93.复原IP地址
     */

    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {
            return res;
        }
        StringBuilder sb = new StringBuilder(s);
        dfs(sb.toString(), 0, 0);
        return res;
    }

    /**
     *
     * @param sb
     * @param start
     * @param dotCount 逗点数量(==3时分割结束，判断是否有效)
     */
    private void dfs(String sb, int start, int dotCount) {
        if (dotCount == 3) {
            if (isVailed(sb, start, sb.length()-1)) { // 这一段数据是否有效
                res.add(sb);
            }
            return ;
        }
        for (int i = start; i < sb.length(); i++) {
            if (isVailed(sb, start, i)) {
                // 选择sb[i]
                sb = sb.substring(0, i + 1) + "." + sb.substring(i+1);
                dfs(sb, i + 2, dotCount+1);
                sb = sb.substring(0, i + 1) + sb.substring(i+2);
            } else {
                break;
            }
        }
    }

    private boolean isVailed(String sb, int start, int end) {
        if (start > end) {
            return false;
        }
        // 不能前导0
        if (sb.charAt(start) == '0' && start != end) {
            return false;
        }
        // 只能是数字
        int num = 0;
        for (int i = start; i <= end; i++) {
             if (sb.charAt(i) > '9' && sb.charAt(i) < '0') {
                 return false;
             }
             num = num * 10 + (sb.charAt(i) - '0');
             if (num > 255) {
                 return false;
             }
        }
        return true;

    }
}
