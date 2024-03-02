package AlgorithmNotes.kmp;

import java.util.Arrays;

/**
 * Z 函数（扩展 KMP）: https://www.bilibili.com/video/BV1it421W7D8/?vd_source=251d34e72212fc9fc32b4762c33f197f
 * <p>
 * 扩展KMP（也称为Z algorithm)
 * <p>
 * 能够以线性的时间复杂度求出一个字符串 s 和它的任意后缀
 * s[i] ... s[n]的最长公共前缀的长度。
 * 注意其与KMP算法求出的 next 数组的区别，一个是以字符s[i]结束，另一个是从字符s[i]开始。<p>
 * KMP: 以 s[i] 结束的后缀与s的前缀匹配的最大长度
 * Z algorithm: 以 s[i] 开始的后缀与s的前缀匹配的最大长度(对于 i，我们称区间 [i, i+z[i]-1] 是 i 的 匹配段，也可以叫 Z-box)
 */
public class Z_kmpExten {
    // aaaaaa
    // z = [-, 5, 4, 3, 2 1]
    // 对应后缀: aaaaa, aaaa, aaa, aa, a

    //aaabaab: 0
    //aabaab: 2
    // abaab: 1
    //  baab: 0
    //   aab: 2
    //    ab: 1
    //     b: 0
    // z[] = 0 2 1 0 2 1 0
    //对于 i，我们称区间 [i,i+z[i]-1] 是 i 的 匹配段，也可以叫 Z-box。
    // i = 0: [0, 0 + z[0] -1] = [0, -1]
    // i = 1: [1, 1 + z[1] -1] = [1, 2]
    // i = 2: [2, 2 + z[2] -1] = [2, 2]

    //s[l, r] 是 s 的前缀

    //在计算 z[i] 的过程中：
    //如果 i <= r，那么根据 [l,r] 的定义有 s[i,r] = s[i-l,r-l]，因此 z[i] >= (z[i-l],r-i+1)。这时：
    //若 z[i-l] < r-i+1，则 z[i] = z[i-l]。
    //否则 z[i-l]\ge r-i+1，这时我们令 z[i] = r-i+1，然后暴力枚举下一个字符扩展 z[i] 直到不能扩展为止。
    //如果 i>r，那么我们直接按照朴素算法，从 s[i] 开始比较，暴力求出 z[i]。
    //在求出 z[i] 后，如果 i+z[i]-1>r，我们就需要更新 [l,r]，即令 l=i, r=i+z[i]-1。


    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int m = pattern.length;
        int[] s = Arrays.copyOf(pattern, m + nums.length);
        s[m] = 2; // 2作为分隔符
        for (int i = 1; i < nums.length; i++) {
            s[m + 1] = Integer.compare(nums[i], nums[i - 1]);
        }

        int n = s.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) { // 在Z-box中
                z[i] = Math.min(z[i - l], r - i + 1); // 在前缀的z[i]和距离Z-box的有边界长度去最小
            }
            // 暴力匹配，从i到i + z[i]尽量更多的匹配，得到l, r, z[i]
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) { // 在Z-box中,z[i]表示从前缀的第几个开始匹配, s[i + z[i]对应s[z[i]]
                //s[z[i]] == s[i + z[i]] 这里比的是下一个元素是否相等
                l = i;
                r = i + z[i];
                z[i]++;
            }
        }

        int ans = 0;
        for (int i = m + 1; i < n; i++) {
            if (z[i] == m) {
                ans++;
            }
        }
        return ans;
    }

    //--------------------------------------------------------------------

    class Node {
        Node[] son = new Node[26];
        int cnt;
    }

    /**
     * 3042. 统计前后缀下标对 I
     *
     * @param words
     * @return
     */
    public int countPrefixSuffixPairs(String[] words) {
        // 1. z + 字典树
        long ans = 0;
        Node root = new Node();
        for (String T : words) {
            char[] t = T.toCharArray();
            int n = t.length;
            int[] z = new int[n];
            int l = 0, r = 0;
            for (int i = 1; i < n; i++) {
                if (l <= r) {
                    // 在Z-box中
                    z[i] = Math.min(z[i - l], r - i + 1);
                }
                while (i + z[i] < n && t[z[i]] == t[i + z[i]]) {
                    l = i;
                    r = i + z[i];
                    z[i]++;
                }
            }
            z[0] = n;
            Node cur = root;
            for (int i = 0; i < n; i++) {
                int c = t[i] - 'a';
                if (cur.son[c] == null) {
                    cur.son[c] = new Node();
                }
                cur = cur.son[c];
                if (z[n - 1 - i] == i + 1) {
                    // t长度为i + 1的前后相同
                    ans += cur.cnt;
                }
            }
            cur.cnt++;
        }
        return (int) ans;
    }


    public int countMatchingSubarrays1(int[] nums, int[] pattern) {
        int m = pattern.length;
        int[] s = Arrays.copyOf(pattern, m + nums.length);
        s[m] = 2;
        for (int i = 1; i < nums.length; i++) {
            s[i+m] = Integer.compare(nums[i],nums[i-1]);
        }
        int n = s.length;
        int[] z = new int[n]; // z函数
        int l =0, r=0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                // 前者是取执勤啊已计算的结果，后者以下一个元素为准（设为1）
                z[i] = Math.min(z[i-l], r-i+1);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
        }

        int ans=0;
        for (int i = m + 1;i < n; i++) {
            if (z[i] == m) { // 代表匹配完全成功一次
                ans++;
            }
        }
        return ans;
    }

}









