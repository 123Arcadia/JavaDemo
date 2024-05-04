package AlgorithmNotes.test;

import AlgorithmNotes.hot100.mergeAscListNode.ListNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;

public class test {

    public int minIncrements(int n, int[] cost) {
        int ans = 0;
        // 我们从次低一层的节点遍历，对其子节点[x, y]，只要加之子节点值相等即可
        for (int i = n / 2; i > 0; i--) { // 从最后一个非叶节点开始算
            ans += Math.abs(cost[i * 2 - 1] - cost[i * 2]);// 两个子节点变成一样的
            // 这里第i哥节点，是cost[i-1]
            // 累加路径，但是[i*2]和[i*2-1]的值上轮已经相等,加上对原值比大小不影响
            cost[i - 1] += Math.max(cost[i * 2 - 1], cost[i * 2]); // 累加路径，选取路径最大的为准
        }
        return ans;
    }


    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }


    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int[] first = new int[n];
        int[] second = new int[n];
        int j = 0, k = 0;
        first[0] = nums[0];
        second[0] = nums[1];

        for (int i = 2; i < n; i++) {
            int pre = first[j], cur = second[k];
            System.out.println(pre + ", " + cur);
            if (pre > cur) {
                first[++j] = nums[i];
            } else {
                second[++k] = nums[i];
            }
        }

        int last = 0;
//        if (n%2!=0) {
//            if (first.length > 0 && second.length > 0 && first[first.length-1] > second[second.length-1]){
//                last=nums[n-1];
//            }
//        }
//        System.out.println(Arrays.toString(first) + ", j="+j);
//        System.out.println(Arrays.toString(second)+", k = " +k);
        System.arraycopy(first, 0, result, 0, j + 1);
        System.arraycopy(second, 0, result, j + 1, k + 1);
        if (last > 0) {
            result[n - 1] = last;
        }
//        System.out.println(Arrays.toString(result));
//        System.out.println("last = " + last);

        // 奇数位在前，偶数位在后
        // 化成索引就是0 2 4在前， 1 3 5 在后
        return result;
    }

    @Test
    public void test() {
        int[] nums = {2, 1, 3};
        int[] nums1 = {5, 4, 3, 8};
        int[] nums2 = {1, 2, 4};
        System.out.println(Arrays.toString(resultArray(nums)));
        System.out.println(Arrays.toString(resultArray(nums1)));
        System.out.println(Arrays.toString(resultArray(nums2)));
        //last = 4
        //[1, 4, 2]

    }

    public int countPaths(int n, int[][] roads) {
        long mod = 1000_000_007;
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<int[]>();
        }
        for (int[] road : roads) {
            int start = road[0];
            int end = road[1];
            int time = road[2];
            e[start].add(new int[]{end, time});
            e[end].add(new int[]{start, time});
        }
        long[] dis = new long[n]; // 到各个点当前最短路径的长度
        Arrays.fill(dis, Long.MAX_VALUE);
        int[] ways = new int[n]; // 胡斐最少的路径数量
        // 存储[dis, 终点]
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dis[0] = 0;
        ways[0] = 1;
        pq.offer(new long[]{0, 0});
        while (!pq.isEmpty()) {
            long[] poll = pq.poll();
            long t = poll[0]; // 当前的路径juice
            int u = (int) poll[1]; // 该当前终点
            if (t > dis[u]) {
                continue;
            }
            for (int[] ints : e[u]) {
                int end = ints[0], time = ints[1];
                if (t + time < dis[end]) {
                    // 更新
                    dis[end] = t + time;
                    ways[end] = ways[u];
                    pq.offer(new long[]{t + time, end});
                } else if (t + time == dis[end]) {
                    ways[end] = (int) ((ways[u] + ways[end]) % mod);
                }
            }

        }
        return ways[n - 1];
    }


    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int cnt = 0;
            for (int num : nums) {
//                if (((num >> i) & 1) == 1) {
//                    cnt++;
//                }
//            }
//            if (cnt >= k) {
//                ans |= 1 << i; // 相等于遍历每个num，逐步填充ans二进制的位数
//            }
                cnt += (num >> i) & 1;
            }
            if (cnt >= k) {
                ans |= 1 << i;
            }

        }
        return ans;
    }

    /**
     * 11
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0, j = n - 1;
        int ans = 0;
        while (i < j) {
            ans = (height[i] < height[j]) ?
                    Math.max(height[i++] * (j - i), ans) :
                    Math.max(height[j--] * (j - i), ans);
        }
        return ans;
    }

    @Test
    public void test2() {
        int[] nums = {7, 12, 9, 8, 9, 15};
        int k = 4;
        System.out.println(findKOr(nums, k));
    }


    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
//        for (; i < n; i++) {
//            if (nums[i] == 0) continue;
//            nums[j++] = nums[i];
//        }
//        for (; j < n; j++) {
//            nums[j] = 0;
//        }
        // 优化
        for (; i < n; i++) {
            if (nums[i] != 0) {
                int t = nums[j];
                nums[j++] = nums[i];
                nums[i] = t;
            }
        }

    }


    /**
     * 获取val的二进制1的数量
     *
     * @param val
     * @return
     */
    public int getOneOfBin(int val) {
        int count = 0;
        while (val > 0) {
//            if ((val & 1) == 1)count++;
//            val >>= 1;
            val &= (val - 1); // 将x的最低位的1清零，相当于去掉一个最低位的1
            count++; // 每清除一次最低位的1，ones就加1，因此ones最终记录了“1”的个数
        }
        return count;
    }


    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        // 把原数组转变为hash表
        // 索引: hash后的值(都大于n), 值: +n之后的值
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) { // 为什么取nums[i], 因为上面是有num-1取hash值的
                res.add(i + 1);
            }
        }
        return res;

    }


    /**
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目
     */
    public int hammingDistance(int x, int y) {
        int ans = 0;
        int s = x ^ y;
        while (s > 0) {
            ans++;
            s &= (s - 1);
        }
        return ans;
        //x=1,y=4 (100)

    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        //滑动
        Set<Character> seen = new HashSet<>();
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                seen.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !seen.contains(s.charAt(rk + 1))) {
                seen.add(s.charAt(rk + 1));
                rk++;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        if ((n + m) % 2 == 1) {
            int mid = (n + m) >> 1;
            // 第k大的数
            return getKth(nums1, nums2, mid + 1);
        } else {
            int mid1 = (n + m) / 2, mid2 = (n + m) / 2 + 1;
            return (getKth(nums1, nums2, mid1) + getKth(nums1, nums2, mid2)) / 2.0;
        }
    }

    // 第k大的数
    private double getKth(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int idx1 = 0, idx2 = 0;
        while (true) {
            if (idx1 >= n) return nums2[idx2 + k - 1];
            if (idx2 >= m) return nums1[idx1 + k - 1];

            int half = k / 2;
            int newidx1 = Math.min(idx1 + half, n) - 1;
            int newidx2 = Math.min(idx2 + half, m) - 1;
            int val1 = nums1[newidx1], val2 = nums2[newidx2];
            if (val1 <= val2) {
                k -= (newidx1 - idx1 + 1);
            } else {
                k -= (newidx2 - idx2 + 1);
            }

        }
    }


    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int maxlen = 1;
        int begin = 0;
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i < n; i++) {
                int j = i + L - 1;
                if (j >= n) break;

                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2) dp[i][j] = true;
                    else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j] && j - i + 1 > maxlen) {
                    maxlen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxlen);
    }


    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // f[i][j]表示 sss 的前 i 个字符与 ppp 中的前 j 个字符是否能够匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (matches1(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches1(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }

        }
        return dp[m][n];
    }
    // s="a", p="a"; m=n=1
    // i=0, j=1,matches->false
    // i=1, j=1,matches->true


    /**
     * 匹配s的前i个字符和p的前j个字符是否匹配
     */
    private boolean matches1(String s, String p, int i, int j) {
        if (i == 0) return false;
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            int target = -nums[i];
            if (nums[i] > 0) continue;
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 保证三元组不重复
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                }
            }
        }
        return res;
    }


    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> stk = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stk.push(')');
            } else if (c == '{') {
                stk.push('}');
            } else if (c == '[') {
                stk.push(']');
            } else {
                if (!stk.isEmpty() && !stk.peek().equals(c)) {
                    return false;
                } else if (!stk.isEmpty() && stk.peek().equals(c)) {
                    stk.pop();
                } else {
                    return false;
                }
            }

        }
        return stk.isEmpty();
    }

    @Test
    public void test22() {
        String s = "()";
//        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
    }


    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i - 1]) { // 后到前找到第一个变小的
            i--; // 是递减
        }
        if (i >= 0) {
            int j = nums.length - 1; //在i, n-1找到比nums[i]大的数,交换
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 如果i < 0 ，这是一个建旭数组，直接反转
        // 在i+1到n-1之间导师降序数组
        // 否则反转i+1到n-1的数组
        rerverse(nums, i + 1);
    }

    private void rerverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public int longestValidParentheses(String s) {
//        int n = s.length();
//        Stack<Integer> stk = new Stack<>();
//        int maxlen = 0;
//        stk.push(-1); // 防止s[0]!=')'时, 面对新的')'需要pop时，出错
//        for (int i = 0; i < n; i++) {
//            if (s.charAt(i) == '(') {
//                stk.push(i);
//            } else {
//                stk.pop(); // 上一个做括号出栈后，我们计算i - stk.peek()直接就是符合的字串的长度
//                if (stk.isEmpty()) {
//                    // 出栈后栈为空，更新右括号
//                    stk.push(i);
//                } else {
//                    maxlen = Math.max(maxlen, i - stk.peek());
//                }
//            }
//        }
//        return maxlen;

        // dp[i]: 以s[i]结尾的最长有效字符串长度
        int n = s.length(), maxLen = 0;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                // 如果是"...()"
                if (s.charAt(i) == ')' && s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                }
                // 如果是"..))"
                if (s.charAt(i) == ')' && s.charAt(i - 1) == ')' && i - dp[i - 1] > 0
                        && s.charAt(i - (dp[i - 1] + 1)) == '(') {
                    // 取s[i-1]为结尾的最长有效长度 + dp[i - (dp[i-1] + 1) - 1]
                    dp[i] = dp[i - 1] + ((i - (dp[i - 1] + 1) - 1 >= 0) ? dp[i - (dp[i - 1] + 1) - 1] : 0) + 2;
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
//        int l = 0, r = 0, maxlength = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) =='(') {
//                l++;
//            } else {
//                r++;
//            }
//            if (l == r) {
//                maxlength = Math.max(maxlength, 2 * r);
//            } else if (r > l) {
//                l = r = 0;
//            }
//        }
//        l = r = 0;
//        for (int i = s.length() - 1; i >= 0; i--) {
//            if (s.charAt(i) == '(') {
//                l++;
//            } else {
//                r++;
//            }
//            if (l == r) {
//                maxlength = Math.max(maxlength, 2 * l);
//            } else if (l > r) {
//                l = r = 0;
//            }
//        }
//        return maxlength;
    }

    /**
     * 33. 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        // 题目值旋转了一次，所以从mid分割，肯定有一半是有序的(可以二分), 对另一半
        int n = nums.length;
        int l = 0, r = nums.length - 1;
        if (n == 0) return -1;
        if (n == 1) {
            return target == nums[0] ? 0 : -1;
        }
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            // 这表示了前一半[0, mid]有序
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) { // 在该有序部分二分
                    r = mid - 1;
                } else { // target不在该有序部分
                    l = mid + 1;
                }
            } else {
                // 无序
                if (nums[mid] < target && target <= nums[n - 1]) { // target在该部分(mid, n-1]
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }

            }

        }
        return -1;
    }


    /**
     *
     */
    public void rotate(int[][] matrix) {
        // 先135°独角仙交换，在col=[0, n - 1]交换(从外到内交换)
        // n: 行数, m: 列数
        // y = x - i;
        int n = matrix.length, m = matrix[0].length;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (i == 0) { //[0,3] [3,0]; [0,2][2,0]
//                    int temp = matrix[i][j];
//                    matrix[i][j] =  matrix[j][i];
//                    matrix[j][i] = temp;
//                }
//            }
//        }
        for (int i = 0; i < n; i++) {
            int temp = matrix[0][i];
            matrix[0][i] = matrix[i][0];
            matrix[i][i] = temp;
        }


//        for (int i = 0; i < matrix[0].length; i++) {
//            int temp = matrix[0][i];
//            matrix[0][1] = matrix[i][0];
//            matrix[0][i] = temp;
//        }
        System.out.println(Arrays.deepToString(matrix));
        // 在列交换(对每个matrix[i]头尾交换)
        for (int i = 0; i < n; i++) {
            int l = 0, r = m - 1;
            while (l < r) {
                int temp = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = temp;
                l++;
                r--;
            }
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binSearch(nums, target, true);
        int rightIdx = binSearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target
                && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    /**
     * 如果 lowetrue truetrue，则查找第一个大于等于 target 的下标，
     * 否则查找第一个大于 target 的下标
     */
    private int binSearch(int[] nums, int target, boolean lower) {
        int ans = nums.length;
        int l = 0, r = ans - 1;
        // 先找到第一个taget的idx,
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }


    public boolean canJump(int[] nums) {
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i < end) {
                int skip = nums[i] + i;
                if (end < skip) end = skip;
                if (end >= nums.length - 1) return true;
            }
        }
        return false;
    }


    int sumOfPath;

    public int uniquePaths(int m, int n) {
//        this.sumOfPath = 0;
//        if (m == 1 || n == 1) {
//            return 1;
//        } else {
//            dfs1(m, n, 1, 1);
//            return sumOfPath;
//        }


        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) { // 行数
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) { // 行数
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    private void dfs1(int m, int n, int x, int y) {
        if (x >= m || y >= n) {
            sumOfPath++;
            return;
        }
        dfs1(m, n, x + 1, y);
        dfs1(m, n, x, y + 1);
    }


    int[][] xy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        char start = word.charAt(0);
        int n = board.length, m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == start) {
                    if (dfsWord(i, j, word, board, visited, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfsWord(int i, int j, String word, char[][] board, boolean[][] visited, int h) {
        int n = board.length, m = board[0].length;
        if (board[i][j] != word.charAt(h)) return false;
        else if (h == word.length() - 1) return true;
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int dx = xy[k][0], dy = xy[k][1];
            if (i + dx < n && i + dx >= 0
                    && j + dy < m && j + dy >= 0) {
                if (!visited[i + dx][j + dy]) {
                    int x = dx + i, y = dy + j;
                    if (dfsWord(x, y, word, board, visited, h + 1)) {
                        return true;
                    }
                }

            }
        }
        visited[i][j] = false;
        return false;
    }


    public int largestRectangleArea(int[] heights) {
        int n = heights.length;// 顶到底递增
        Stack<Integer> stk = new Stack<>();
        stk.push(0);
        int[] newHeight = new int[n + 2];
        for (int i = 0; i < n; i++) {
            newHeight[i + 1] = heights[i];
        }
        int maxArea = 0;
        // 两边加上0而停止搜索
        for (int i = 1; i < n; i++) {
            while (!stk.isEmpty() && newHeight[i] > newHeight[stk.peek()]) {

                int top = stk.pop();
                int width = i - stk.peek();
                int height = newHeight[i];
                int area = width * height;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
            stk.push(i);

        }
        return maxArea;
    }


    public int maximalRectangle(char[][] matrix) {
//        int m = matrix.length;
//        if (m == 0) {
//            return 0;
//        }
//        int n = matrix[0].length;
//        int[][] left = new int[m][n]; // matrix[i][j]的左边是连续1的个数
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (matrix[i][j] == '1') {
//                    left[i][j] = (j == 0 ? 0 : left[i][j-1]) + 1;
//                }
//            }
//        }
//        int ret = 0;
//        // 找到matrix[i][j]为右下角的矩形大小
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (matrix[i][j] == '1') {
//                    int width = left[i][j];
//                    int area = width;
//                    for (int k = i-1;  k >= 0; k--) {
//                        width = Math.min(left[k][j],width);
//                        area = Math.max(area, width*(i - k +1));
//                    }
//                    ret = Math.max(ret, area);
//                }
//            }
//        }
//        return ret;

        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }
            System.out.println(Arrays.toString(up) + "," + Arrays.toString(down));
            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }


    public int numTrees(int n) {
        //可以遍历每个数字 i，将该数字作为树根，将 1⋯(i−1)i序列作为左子树，将 (i+1)⋯n(i+1)i序列作为右子树。
        // 接着我们可以按照同样的方式递归构建左子树和右子树
        int[] G = new int[n + 1]; // G[n] 序列长度为n的二叉树个数
        G[0] = 1;
        G[1] = 1;
        // F(i, n) : 长度为n，以i为根节点的子树的个数
        // F(i, n) = G(i-1) * G(n-i);
        // G(n) = ∑F(i, n), i = 1...n
        // G(n) = ∑G(i-1)G(n-i)

        // 也可以总结规律:
        // dp[i] = i个不同的数组成的二叉搜索数的个数
        // 假设 i = 5
        // 当根节点等于 1 时 ，其余数字都比1大，只能在右边 dp[i] += dp[4]
        // 当根节点等于 2 时，左边有一个1比2小，右边有三个比2大的数字 dp[i] += dp[1] * dp[3]
        // 当根节点等于 3 时，左边有两个数比3小，右边有两个数比3大的数字 dp[i] += dp[2] * dp[2]
        // ...
        // 知道根节点等于5，左边有4个数字比5小，只能放在5的左边,dp[i] += dp[4]
        //  */
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST(TreeNode root) {
        // dfs
//        if (root == null) return false;
//        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // 中序遍历：只要是一个升序列表就是二叉树
        double inorder = -Double.MAX_VALUE;
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        while (!stk.isEmpty() || root != null) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            //当前节点的值是否大于前一个中序遍历到的节点的值即可
            root = stk.pop();
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * @param root
     * @param l    root.val >= l
     * @param r    root.val <= r
     */
    private boolean helper(TreeNode root, int l, int r) {
        if (root == null) return true;
        if (root.val >= l && root.val <= r) {
            return helper(root.left, l, root.val) && helper(root.right, root.val, r);
        }
        return false;
    }


    /**
     * 二叉树展开为链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
//        preBackTraverse(root, list);
//        for (int i = 1; i < list.size(); i++) {
//            TreeNode pre = list.get(i-1);
//            TreeNode cur = list.get(i);
//            pre.right = cur;
//            pre.left = null;
//        }

        // 迭代
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        TreeNode node = root;
        while (!stk.isEmpty() || node != null) {
            while (node != null) {
                list.add(node);
                stk.push(node);
                node = node.left;
            }
            node = stk.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.right = cur;
            pre.left = null;
        }

    }

    private void preBackTraverse(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preBackTraverse(root.right, list);
            preBackTraverse(root.right, list);
        }
    }


    /**
     * 128. 最长连续序列
     */
    public int longestConsecutive(int[] nums) {
//        if (nums.length <= 1) return nums.length;
//        Arrays.sort(nums);
//        System.out.println(Arrays.toString(nums));
//        int maxLen = 1;
//        int count = 1;
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] == nums[i-1]) continue;
//            else if (nums[i] == nums[i-1] + 1) {
//                count++;
//                if (i == nums.length-1) return maxLen;
//            } else {
//                count =1;
//            }
//            maxLen = Math.max(maxLen, count);
//        }
//        return maxLen;

        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }
        int longestreak = 0;
        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int cur = num;
                int curStreak = 1;
                while (num_set.contains(cur + 1)) {
                    cur++;
                    curStreak++;
                }
                longestreak = Math.max(longestreak, curStreak);
            }
        }
        return longestreak;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        //dp[i] = dp[j] && check(j,.. i-1)   / j前的字符串符合, j后的直达船需要check
        // j: 分割点
        Set<String> wordDicttSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1]; // dp[i]表示0, i-1的这个船符合
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 找到可以符合的分割点就返回true
                if (dp[j] && wordDicttSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public String capitalizeTitle(String title) {
        String[] strings = title.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() <= 2) {
                strings[i] = strings[i].toLowerCase();
            } else {
                strings[i] = strings[i].substring(0, 1).toUpperCase() + strings[i].substring(1).toLowerCase();
            }
        }
        return String.join(" ", strings);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next, soft = head;
        while (fast != null && fast.next != null) {
            soft = soft.next;
            fast = fast.next.next;
        }
        // tmp在中间断开
        ListNode tmp = soft.next;
        soft.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        // 最后合并两个链表(之前做过)
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dum = new ListNode(-1);
        ListNode cur = dum;
        if (left == null) return right;
        if (right == null) return left;
        while (left != null && right != null) {
            if (left.val < right.val) {
                dum.next = left;
                left = left.next;
            } else {
                dum.next = right;
                right = right.next;
            }
            dum = dum.next;
        }
        if (left != null) dum.next = left;
        if (right != null) dum.next = right;
        return cur.next;
    }


    public int maxProduct(int[] nums) {
        // 便利长度为[0...n-1]
        int n = nums.length;
        //令imax为当前最大值, 由于存在负数，那么会导致最大的变最小的，最小的变最大的
//        int max = Integer.MIN_VALUE, imax = 1 , imin = 1;
//        for (int i = 0; i < n; i++) {
//            if (nums[i] < 0) {
//                int t = imax;
//                imax = imin;
//                imin = t;
//            }
//            imax = Math.max(nums[i], nums[i] * imax);
//            imin = Math.min(nums[i], nums[i] * imin);
//            max  = Math.max(max, imax);
//        }
//        return max;


        // maxF,minF
        int maxRes = nums[0], maxF = nums[0], minF = nums[0];
        for (int i = 1; i < n; i++) {
            maxF = Math.max(maxF * nums[i], Math.max(minF * nums[i], nums[i]));
            minF = Math.min(minF * nums[i], Math.min(maxF * nums[i], nums[i]));
            maxRes = Math.max(maxF, maxRes);
        }
        return maxRes;

    }


    /**
     * 383周赛
     * 3029. 将单词恢复初始状态所需的最短时间 I
     */
    public int minimumTimeToInitialState(String word, int k) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        int l = 0, r = 0;
        int[] z = new int[n];
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }

            while (i + z[i] < n && chars[z[i]] == chars[i + z[i]]) {
                l = i;
                r = l + z[i];
                z[i]++;
            }
            if (i % k == 0 && z[i] >= n - i) {
                return i / k;
            }
        }
        return (n - 1) / k + 1;
    }

    //-------------------------------------
    public int numSquares(int n) {
        //f[i] 表示最少需要多少个数的平方来表示整数 i
        // [1, sqrt(n)]
        //假设当前枚举到 j，那么我们还需要取若干数的平方，构成 i−j^2
        // dp[0]无意义
        int[] dp = new int[n + 1];
        for (int i = 1; i <= Math.sqrt(n); i++) {
            int minn = Integer.MAX_VALUE;
            // 在1...i范围内的完全平方数
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, dp[i - j * j]);
            }
            dp[i] = minn;
        }
        return dp[n];

    }

    public int findDuplicate(int[] nums) {
//        Arrays.sort(nums);
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] == nums[i-1]) return nums[i];
//        }
//        return  -1;


        // 快慢指针: 数组映射链表
        int slow = 0, fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        // 如果不重复，映射会成为一个单项链表；否则会有环
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 第一次相遇
        int slowIdx = 0, fastIdx = slow;
        while (slowIdx != fastIdx) {
            slowIdx = nums[slowIdx];
            fastIdx = nums[fastIdx];
        }
        return slowIdx;

        // 3. 二分：统计小于等于数字i的数量

    }


    //--------------------------------------------------------------------

    /**
     * 序列化
     */

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return encodeSerialize(root, "");
    }

    private String encodeSerialize(TreeNode node, String str) {
        if (node == null) {
            str += "None,";
        } else {
            str += String.valueOf(node.val) + ",";
            str = encodeSerialize(node.left, str);
            str = encodeSerialize(node.right, str);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strList = data.split(",");
        List<String> nodeList = new ArrayList<>(Arrays.asList(strList));
        return decodeDeserialize(nodeList);
    }

    private TreeNode decodeDeserialize(List<String> strList) {
        if (strList != null && "None".equals(strList.get(0))) {
            strList.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(strList.get(0)));
        strList.remove(0);
        node.left = decodeDeserialize(strList);
        node.right = decodeDeserialize(strList);
        return node;
    }

    //--------------------------------------------------------------------


    public int lengthOfLIS(int[] nums) {
//        int n = nums.length;
//        if (n <= 0) return 0;
//        int[] dp = new int[n]; // 以第 i 个数字结尾的最长上升子序列的长度
//        dp[0] = 1;
//        int maxans = 0;
//        for (int i = 1; i < n; i++) {
//            dp[i] = 1;
//            for (int j = 0; j < i; j++) // 0..i-1的数的最长递增子序列
//            {
//                if (nums[i] > nums[j]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//            maxans = Math.max(maxans, dp[i]);
//        }
//        return maxans;

        //2. 贪心 + 二分查找
        List<Integer> res = new ArrayList<>();
        for (int x : nums) {
            int j = lowerbound(res, x); // 找到res中第一个大于等于x的值的索引
            if (j == res.size()) { // res中找不到大于res[j]的值
                res.add(x);
            } else {
                res.set(j, x); // 替换res[j]的位置
            }
        }
        return res.size();

    }

    private int lowerbound(List<Integer> res, int x) {
        int n = res.size();
        int l = -1, r = n;
        while (l + 1 < r) {
            int mid = (l + r) >> 1;
            if (res.get(mid) < x) {
                ;
                l = mid;
            } else {
                // res.get(mid) >= x
                r = mid; // 第一个大于等于x的值
            }
        }
        return r; // 或者l+1;
    }

    //-------------------------------------------------------

    //--------------------------------------------------------------
//    public int maxProfit(int[] prices) {
//        //状态: 0->无股票, 1->无股票，但是冻结 2 -> 有股票
//        // dp[i][3]: 第i天结束的收益
//        // i -1 买入
//        //dp[i][0] = max(dp[i-1][1], dp[i-1][0]) // 第i天都不买
//        // dp[i][1] = dp[i-1][0] + prices[i] // 第i天卖出
//        //dp[i][2] = max(dp[i-1][2], dp[i-1][0] - prices[i]) // 第i天买入
//        // dp[0][0] = 0;
//        // dp[0][1] = 0;
//        // dp[0][2] = -prices[0];
//        int n = prices.length;
//        int[][] dp = new int[n][3];
//        dp[0][2] = -prices[0];
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]); // 第i天都不买
//            dp[i][1] = dp[i - 1][2] + prices[i]; // 第i天卖出
//            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] - prices[i]); // 第i天买入
//        }
//        return Math.max(dp[n - 1][0], dp[n - 1][1]);
//    }

    //--------------------------------------------------------------

    public int[][] rec;
    public int[] val;

    public int maxCoins(int[] nums) {
//        //将全过程看作是每次添加一个气球
//        //令 solve(i,j) 表示将开区间 (i,j) 内的位置全部填满气球能够得到的最多硬币数
//        int n = nums.length;
//        val = new int[2 + n];
//        for (int i = 1; i <= n; i++) {
//            val[i] = nums[i - 1];
//        }
//        val[0] = val[n] = 1;
//        rec = new int[n+2][n+2];
//        for (int i = 0; i <= n+1; i++) {
//            Arrays.fill(rec[i], -1);
//        }
//        return solve(0, n + 1);

        // 2. 动态规划
        // dp[i][j]: 开区间 (i,j) 能得到的最多硬币数
        //i >= j -1时, dp[i][j] = 0;
        // i < j -1, dp[i][j] = ∑(val[i] * val[j] * val[k] + dp[i][k] + dp[k][j])

        int n = nums.length;
        int[][] rec = new int[n + 2][n + 2];
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += rec[i][k] + rec[k][j];
                    rec[i][j] = Math.max(rec[i][j], sum);
                }
            }
        }
        return rec[0][n + 1];

    }

    //l,r开区间
    private int solve(int l, int r) {
        if (l >= r - 1) return 0;
        if (rec[l][r] != -1) {
            // 计算过
            return rec[l][r];
        }
        for (int i = l + 1; i < r; i++) {
            int sum = val[l] * val[i] * val[r]; // 添加气球计算乘积
            sum += solve(l, i) + solve(i, r);
            rec[l][r] = Math.max(rec[l][r], sum);
        }
        return rec[l][r];
    }


    //---------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------

    public int[] countBits(int n) {
        int count = 0;
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = getBit(i);
        }
        return res;
    }

    // 110
    // 101
    // 100
    private int getBit(int x) {
        int ans = 0;
        while (x > 0) {
            x &= (x - 1);
            ans++;
        }
        return ans;
    }


    //---------------------------------------------------------------------------------

    public int[] topKFrequent(int[] nums, int k) {
        // 优先队列（限制数量为k）O(nlogk)
        // 桶排序: 把各个元素的频率当做下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 桶
        List<Integer>[] bucket = new List[nums.length + 1]; // 因为0是起始，但实际上没有频率为0的元素
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getKey();
            int count = entry.getValue();
            if (bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(val);
        }
        // 倒序遍历bucket
        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucket[i] == null) {
                continue;
            }
            res.addAll(bucket[i]);
        }
        return res.stream().mapToInt(s -> s).toArray();
    }


    //---------------------------------------------------------------------------------------
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        LinkedList<Integer> stk_Mutil = new LinkedList<>();
        LinkedList<String> stk_res = new LinkedList<>();
        int mutil = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                stk_Mutil.addLast(mutil);
                stk_res.addLast(res.toString());
                mutil = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                int repeat = stk_Mutil.removeLast();
                for (int i = 0; i < repeat; i++) {
                    temp.append(res);
                }
                res = new StringBuilder(stk_res.removeLast() + temp);
            } else if (Character.isDigit(c)) {
                mutil = mutil * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
        // mutil * 10 ?
        //"100[leetcode]"

    }

    //---------------------------------------------------------------------------------------

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
//        // 1. bfs
//        //首先需要遍历 equations\textit{equations}equations 数组，找出其中所有不同的字符串，并通过哈希表将每个不同的字符串映射成整数
//        int nvars = 0;
//        // 映射为整数
//        Map<String, Integer> variables = new HashMap<>();
//        int n = equations.size();
//        for (int i = 0; i < n; i++) {
//            if (!variables.containsKey(equations.get(i).get(0))) {
//                variables.put(equations.get(i).get(0), nvars++);
//            }
//            if (!variables.containsKey(equations.get(i).get(1))) {
//                variables.put(equations.get(i).get(1), nvars++);
//            }
//        }
//        // 建立图
//        // nvars: 表示一共多少个整数(对应的字符串数)
//        // Pair: 保存链接到底点和权值
//        List<Pair>[] edges = new List[nvars];
//        for (int i = 0; i < nvars; i++) {
//            edges[i] = new ArrayList<>();
//        }
//        for (int i = 0; i < n; i++) {
//            // {a, b}映射的整数->{va, vb}
//            // a / b = values[i]: a = b * values[i], b = a / values[i]
//            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
//            edges[va].add(new Pair(vb, values[i]));
//            edges[vb].add(new Pair(va, 1.0 / values[i]));
//        }
//        int queryCount = queries.size();
//        // 存储计算的结果
//        double[] ret = new double[queryCount];
//        for (int i = 0; i < queryCount; i++) {
//            List<String> query = queries.get(i);
//            double result = -1.0;
//            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
//                int qa = variables.get(query.get(0)), qb = variables.get(query.get(1));
//                // 询问的起点和终点
//                if (qa == qb) result = 1.0;
//                else {
//                    Queue<Integer> points = new LinkedList<>();
//                    points.offer(qa);
//                    double[] ratio = new double[nvars];
//                    Arrays.fill(ratio, -1.0);
//                    ratio[qa] = 1.0;
//                    // bfs查询权重之乘积, 之道ratio[qb] == -1.0(<0)
//                    while (!points.isEmpty() && ratio[qb] < 0) {
//                        int x = points.poll();
//                        for (Pair pair : edges[x]) {
//                            int y = pair.idx;
//                            double val = pair.val;
//                            if (ratio[y] < 0) { // 如果ratio[y]没有计算过
//                                ratio[y] = ratio[x] * val;
//                                points.offer(y);
//                            }
//                        }
//                    }
//                    result = ratio[qb];
//                }
//            }
//            ret[i] = result;
//        }
//        return ret;

        // 2. Floyd算法(预先求出每两点之间的距离(权重乘积))
//        int nvars = 0;
//        // 映射为整数
//        Map<String, Integer> variables = new HashMap<>();
//        int n = equations.size();
//        for (int i = 0; i < n; i++) {
//            if (!variables.containsKey(equations.get(i).get(0))) {
//                variables.put(equations.get(i).get(0), nvars++);
//            }
//            if (!variables.containsKey(equations.get(i).get(1))) {
//                variables.put(equations.get(i).get(1), nvars++);
//            }
//        }
//        // 建立图
//        // nvars: 表示一共多少个整数(对应的字符串数)
//        // Pair: 保存链接到底点和权值
//
//        // 建图[][]二维数字
//        double[][] graphic = new double[nvars][nvars];
//        for (int i = 0; i < nvars; i++) {
//            Arrays.fill(graphic[i], -1.0);
//        }
//        for (int i = 0; i < n; i++) {
//            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
//            graphic[va][vb] = values[i];
//            graphic[vb][va] = 1.0 / values[i];
//        }
//        // 预先求出每两点的距离
//        // i到j有k条边，都要计算
//        for(int k = 0; k < nvars; k++) {
//            for (int i = 0; i < nvars; i++) {
//                for (int j = 0; j < nvars; j++) {
//                    if (graphic[i][k] > 0 && graphic[k][j] > 0) {
//                        graphic[i][j] = graphic[i][k] * graphic[k][j];
//                    }
//                }
//            }
//        }
//
//        int queryCount = queries.size();
//        // 存储计算的结果
//        double[] ret = new double[queryCount];
//        for (int i = 0; i < queryCount; i++) {
//            List<String> query = queries.get(i);
//            double result = -1.0;
//            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
//                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
//                if (graphic[ia][ib] > 0) {
//                    result = graphic[ia][ib];
//                }
//            }
//            ret[i] = result;
//        }
//        return ret;

        // 3. 并查集
        int eqSize = equations.size();
        UnionFind unionFind = new UnionFind(2 * eqSize);

        Map<String, Integer> map = new HashMap<>();
        int id = 0;
        for (int i = 0; i < eqSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            if (!map.containsKey(var1)) {
                map.put(var1, id++);
            }
            if (!map.containsKey(var2)) {
                map.put(var2, id++);
            }
            unionFind.union(map.get(var1), map.get(var2), values[i]);
        }
        int size = queries.size();
        double[] res = new double[size];
        for (int i = 0; i < size; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);
            Integer id1 = map.get(var1);
            Integer id2 = map.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0;
            } else {
                res[i] = unionFind.isConn(id1, id2);
            }
        }
        return res;
    }

    class Pair {
        int idx;
        double val;

        public Pair(int idx, double val) {
            this.idx = idx;
            this.val = val;
        }
    }


    // 并查集
    private class UnionFind {
        private int[] parent;
        /**
         * 指向的父结点的权值
         */
        private double[] weight;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0;
            }
        }

        public void union(int x, int y, double value) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) return;
            parent[rootx] = rooty;
            weight[rootx] = weight[y] * value / weight[x];
        }

        private int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }

            return parent[x];
        }

        public double isConn(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            return (rootx == rooty) ? (weight[x] / weight[y]) : -1.0;
        }
    }

    public boolean canPartition(int[] nums) {
        // dp[i][j]表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），
        // 是否存在一种选取方案使得被选取的正整数的和等于 j
        int n = nums.length;
        if (n < 2) return false;

        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num; // 和
            maxNum = Math.max(maxNum, num); // 最大值
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        // dp[i][j]: 是否存在0...n内的数和为j
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                } else {
                    // 相当于不选择
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }


    //------------------------------------------------------------
    public int pathSum(TreeNode root, int targetSum) {
        // if (root == null) return  0;
        // // 以root为起点的和待遇target的路径数目
        // long ret = rootSum(root, (long)targetSum);
        // ret += pathSum(root.left, targetSum);
        // ret += pathSum(root.right, targetSum);
        // return (int)ret;


        /// 2.前缀和
        // 前缀和: 路径条数
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfsSum(root, prefix, 0L, targetSum);


    }

    private int dfsSum(TreeNode node, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (node == null) return 0;
        int ret = 0;
        curr += node.val;
        // 取出该node计算的前缀和
        // 如果在root和node之前的节点中存在节点p，使得root到p节点前缀和为curr - targetSum，
        // 则p节点到node节点的前缀和等于 targetSum
        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfsSum(node.left, prefix, curr, targetSum);
        ret += dfsSum(node.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return ret;
    }


    private long rootSum(TreeNode node, long targetSum) {
        if (node == null) return 0;
        long ret = 0;
        if (node.val == targetSum) ++ret;
        ret += rootSum(node.left, targetSum - node.val);
        ret += rootSum(node.right, targetSum - node.val);
        return ret;
    }
    //------------------------------------------------------------


    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<>();
        int[] sCOunt = new int[26];
        int[] pCOunt = new int[26];
        for (int i = 0; i < pLen; i++) {
            sCOunt[s.charAt(i) - 'a']++;
            pCOunt[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCOunt, pCOunt)) {
            // 从0开始到pLen是异位词
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; i++) {
            sCOunt[s.charAt(i) - 'a']--;
            sCOunt[s.charAt(i + pLen) - 'a']++;
            if (Arrays.equals(sCOunt, pCOunt)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }


    //--------------------------------------------------------------------
    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
//        dfs(nums, target, 0, 0);
//        return count;

        // 动态规划:
        //问题转化成在数组 nums\textit{nums}nums 中选取若干元素，使得这些元素之和等于 neg\textit{neg}neg，计算选取元素的方案数
        // (sum - neg) - neg = sum - 2 * neg = target
        // neg = (sum - target) / 2, 都是非负偶数

        // dp[i][j]: 表示前i个数之和为j的方案数
        // dp[0][j] = 0; (j > 0)
        // dp[0][j] = 1; (j = 0)

        // (不选)n >= i >= 0: dp[i][j] = dp[i-1][j]
        // (选) dp[i][j] = dp[i-1][j-nums[i]] +dp[i-1][num]
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum < target || (sum - target) % 2 == 1) {
            return 0;
        }
        int neg = (sum - target) / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];

    }

    private void dfs(int[] nums, int target, int idx, int sum) {
        if (idx == nums.length) { // 非负整数数组所以可以直接返回无需继续搜索
            if (sum == target)
                count++;
        } else {
            // "+", "-"
            dfs(nums, target, idx + 1, sum + nums[idx]);
            dfs(nums, target, idx + 1, sum - nums[idx]);
        }
    }

    //--------------------------------------------------------------------

    // 累加树
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return null;
    }
    //--------------------------------------------------------------------

    public int subarraySum(int[] nums, int k) {
//        int cnt = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int sum =0 ;
//            for (int j = i; j >= 0; j--) {
//                sum += nums[j];
//                if (sum == k) {
//                    cnt++;
//                }
//            }
//        }
//        return cnt;


        // 2. 前缀和
        //pre[i] = pre[i-1] + nums[i]
        // pre[i] - pre[j-1] = k , 假设和为k的子数组nums[j..i]
        //pre[j-1] = pre[i] - k
        int cnt = 0, pre = 0;
        // 前缀和- 次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                cnt += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return cnt;
    }

    //--------------------------------------------------------------------

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1; // 后到前第一个大于nums[i]的索引
        int minn = Integer.MAX_VALUE, left = -1;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
    //--------------------------------------------------------------------

    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        //如果回文长度是奇数，那么回文中心是一个字符；如果回文长度是偶数，那么中心是两个字符
        for (int i = 0; i < n; i++) {
            // 遍历回文中心点
            for (int j = 0; j <= 1; j++) {
                // j = 0, 中心是一个点，j = 1，中心是两个点
                int l = i;
                int r = i + j;
                while (l >= 0 && r < n && s.charAt(l--) == s.charAt(r++)) {
                    ans++;
                }
            }

        }
        return ans;
    }


    @Data
    @AllArgsConstructor
    class Stu {
        String name;
        Integer age;
    }

    @Test
    public void tes2et() {
        Stu stu1 = new Stu("11", 16);
        Stu stu2 = new Stu("22", 17);
        List<Stu> list = new ArrayList<>();
        list.add(stu1);
        list.add(stu2);
        System.out.println(list);
        stu1.setAge(20);
        System.out.println(list);
    }


    //---------------------------------------------------------------------


    // --------------------------------------------------------------------

    int[][] edges;
    int[] inDegrees;  // 入度

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new List[numCourses];
        Arrays.setAll(edges, k -> new ArrayList<>());
        // p[0]:   p[1]:前置课程
        for (int[] p : prerequisites) {
            inDegrees[p[0]]++;
            edges[p[1]].add(p[0]);

        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                q.offer(i);
            }
        }
        int vis = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : edges[u]) {
                vis++;
                inDegrees[v]--;
                if (inDegrees[v] == 0) {
                    q.offer(v); // v就是该分支的终点
                }
            }
        }
        return vis == numCourses;
    }


    public int distinctIntegers(int n) {
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        // q存放dfs便利出的数字
        q.offer(n);
        while (!q.isEmpty()) {
            int num = q.poll();
            if (num == 1) break;
            if (seen.contains(num)) {
                continue;
            }
            System.out.println(num + "," + q);
            for (int i = 1; i <= num; i++) {
                if (num % i == 1) {
                    seen.add(num);
                    q.offer(num / i);
                }
            }

        }
        return seen.size();


        // 记录(0), 1...n
//        int[] nums = new int[n + 1];
//        nums[n] = 1;
//        for (int k = 0; k < n; k++) {
//            for (int x = 1; x <= n; x++) {
//                if(nums[x] == 0) {
//                    continue;
//                }
//                for (int i = 1; i <= n; i++) {
//                    if (x % i == 1)  {
//                        nums[i] = 1;
//                    }
//                }
//            }
//        }
//        return Arrays.stream(nums).sum();

        //数学: x mod(x−1)=1
//        return n == 1 ? 1 : n-1;
    }


    public int rob(int[] nums) {
        //dp[i] = max(dp[i-2] + nums[i], dp[i-1])
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }


    class Trie {
        private Trie[] children;
        // 是否结束
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie Node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int idx = c - 'a';
                if (Node.children[idx] == null) {
                    Node.children[idx] = new Trie();
                }
                Node = Node.children[idx];
            }
            Node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = seaPrefix(word);
            return node != null ? node.isEnd : false;
        }

        public boolean startsWith(String prefix) {
            return seaPrefix(prefix) != null;
        }

        private Trie seaPrefix(String s) {
            Trie node = this;
            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'a';
                if (node.children[idx] == null) {
                    return null;
                }
                node = node.children[idx];
            }
            return node;
        }
    }


    public int findKthLargest(int[] nums, int k) {
//        int n = nums.length;
//        return quickSort(nums, 0, n - 1, k);


        int n = nums.length;
        buildTree(nums, n);
        // 把倒数k-1个小的数移动到堆顶,然后n--,相当于删除最大数
        for (int i = nums.length - 1; i >= nums.length - (k - 1); i++) {
            swap(nums, i, 0);
            n--;
            maxHeapify(nums, 0, n);
        }
        return nums[0];
    }

    private void buildTree(int[] nums, int heapSize) {
        for (int i = nums.length / 2; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    private void maxHeapify(int[] nums, int i, int heapSize) {
        int l = i * 2, r = 2 * i + 1;
        int largest = i;
        if (l < heapSize && nums[largest] < nums[l]) {
            largest = l;
        }
        if (r < heapSize && nums[largest] < nums[r]) {
            largest = r;
        }
        if (largest != i) {
            swap(nums, i, largest);
            maxHeapify(nums, largest, heapSize);
        }
    }

    private int quickSort(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (l < r) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            }
        }
        if (k <= j) {
            // 倒数第k个数
            return quickSort(nums, l, j, k);
        }
        return quickSort(nums, j + 1, r, k);
    }

    ///-----------------------------------------------------------

    // f:选择节点 g: 不选择
    //
    Map<TreeNode, Integer> f = new HashMap<>();
    Map<TreeNode, Integer> g = new HashMap<>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));

    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.right, 0))
                + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    @Test
    public void testPath() {
//        double x =0.999849, y =0.999683;
        double x = 1, y = 1;
        double res = 100 * (y - x * x) * (y - x * x) + (1 - x) * (1 - x);
        System.out.println("res = " + res);
        //res = 4.536945498845631E-8
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }

        int[] b = Arrays.copyOf(a, a.length);
        b = Arrays.stream(b).boxed().sorted(Comparator.reverseOrder()).mapToInt(s -> s).toArray();
        System.out.println(Arrays.toString(a) + ", " + Arrays.toString(b));

        long s = n * b[0] - sum;
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) { // 就是最高赞
                System.out.println(sum);
                continue;
            }
            if (n == 2) { // 永远也到不了最高赞
                System.out.println(-1);
                continue;
            }
            // 令 cur 记作其余笔记赞变成最多赞数可以执行的次数，如果在cur次之内，可以使得当前笔记的赞数变成最多的，
            // 那么就输出 sum + 2 * diff，diff代表当前笔记赞数和最多赞数的差。
            a[i] = 1; // 每次粉丝量+1
            long diff = b[0] - a[i], cur = s - (b[0] - a[i] + 1), ans = sum + 1;

            if (diff <= cur) {

            }

        }

    }

    int ans = 0;

    int[] coins;
    int[][] memo;

    public int change(int amount, int[] coins) {
        int n = coins.length;
        this.coins = coins;
        memo = new int[n][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(n - 1, amount);
    }

    private int dfs(int i, int c) {
        if (i < 0) {
            return c == 0 ? 1 : 0;
        }
        if (memo[i][c] != -1) {
            return memo[i][c];
        }
        if (c < coins[i]) {
            // 不选这个硬币
            return memo[i][c] = dfs(i - 1, c);
        }
        return memo[i][c] = dfs(i, c - coins[i]) + dfs(i - 1, c);

    }

    //---------------------------------

    /**
     * 求最短路: dijkstra
     */

//    List<int[]>[] graph;
//
//    public Graph(int n, int[][] edges) {
//        graph = new List[n];
//        for (int i = 0; i < n; i++) {
//            graph[i] = new ArrayList<int[]>();
//        }
//        for (int[] edge : edges) {
//            int s = edge[0], e = edge[1], cost = edge[2];
//            graph[s].add(new int[]{e, cost});
//        }
//    }
//
//    public void addEdge(int[] edge) {
//        int x = edge[0], y = edge[1], cost = edge[2];
//        graph[x].add(new int[]{y, cost});
//    }
//
//    public int shortestPath(int node1, int node2) {
//        // int[]{cost, 终点}
//        PriorityQueue<int[]> qp = new PriorityQueue<>((a, b) -> a[0] - b[0]);
//        int[] dist = new int[graph.length];
//        Arrays.fill(dist, Integer.MAX_VALUE);
//        dist[node1] = 0;
//        qp.offer(new int[]{0, node1});
//        while (!qp.isEmpty()) {
//            int[] arr = qp.poll();
//            int cost = arr[0], cur = arr[1]; // 当前所在位置
//            if (cur == node2) {
//                return cost;
//            }
//            for (int[] nxt : graph[cur]) {
//                int nxtCost = nxt[0], nxtNode = nxt[1];
//                if (dist[nxtNode] > cost + nxtCost) {
//                    dist[nxtNode] = cost + nxtCost;
//                    qp.offer(new int[]{cost + nxtCost, nxtNode});
//                }
//            }
//        }
//        return -1;
//    }

    //----------------------------------------
    /**
     * floyd
     */

    // i到j的距离
//    int[][] dist;
//
//    public Graph(int n, int[][] edges) {
//        dist = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(dist[i], Integer.MAX_VALUE);
//            dist[i][i] = 0;
//        }
//        for (int[] edge : edges) {
//            int s = edge[0], e = edge[1], cost = edge[2];
//            dist[s][e] = cost;
//        }
//
//        for (int k = 0; k < n; k++) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
//                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
//                    }
//                }
//            }
//        }
//
//    }
//
//    public void addEdge(int[] edge) {
//        // 当做中间点
//        int x = edge[0], y =edge[1], cost = edge[2];
//        if (cost >= dist[x][y]) {
//            return ;
//        }
//        int n = dist.length;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                // 如果原本存在该边
//                if (dist[i][x] != Integer.MAX_VALUE && dist[y][j] != Integer.MAX_VALUE) {
//                    dist[i][j] = Math.min(dist[i][j], dist[i][x] + cost + dist[y][j]);
//                }
//            }
//        }
//    }
//
//    public int shortestPath(int node1, int node2) {
//        return dist[node1][node2] == Integer.MAX_VALUE ? -1 : dist[node1][node2];
//    }


    /**
     * 朴素Dijkstra
     */

//    int INF = Integer.MAX_VALUE / 2;
//    int[][] graph;
//
//    public Graph(int n, int[][] edges) {
//        graph = new int[n][n];
//        for (int[] arr : graph) {
//            Arrays.fill(arr, INF);
//        }
//        for (int[] e : edges) {
//            addEdge(e);
//        }
//
//    }
//
//    public int shortestPath(int start, int end) {
//        int n = graph.length;
//        int[] dis = new int[n]; // 从s触发到各个点最短路径
//        dis[start] = 0;
//        boolean[] vis = new boolean[n];
//        while (true) {
//            int x = -1; // 搜索的点
//            for (int i = 0; i < n; i++) {
//                if (!vis[i] && (x < 0 || dis[x] > dis[i])) {
//                    x = i; // x是下一个最短路径的点
//                }
//            }
//            if (x < 0 || dis[x] == INF) {
//                return -1; // 没有找到
//            }
//            if (x == end) {
//                return dis[x];
//            }
//            vis[x] = true;
//            // 求由下一个点 dis
//            for (int i = 0; i < n; i++) {
//                dis[i] = Math.min(dis[i], dis[x] + graph[x][i]);
//            }
//
//        }
//    }
//
//    public void addEdge(int[] e) {
//        int x = e[0], y = e[1], c = e[2];
//        graph[x][y] = c;
//    }
    public int minimumSum(int[] nums) {
        int ans = Integer.MAX_VALUE;
//        int n = nums.length, res = 1000, mn = 1000;
//        // left: 表示i之前最小值的索引
//        int[] left = new int[n];
//        for (int i = 1; i < n; i++) {
//            left[i] = mn =  Math.min(nums[i-1], mn);
//        }
//
//        int right = nums[n-1];
//        for(int i = n-2; i >= 0; i--) {
//            if (left[i] < nums[i] && nums[i] > right) {
//                res = Math.min(res, left[i] + nums[i] + right);
//            }
//            right = Math.min(right, nums[i]);
//        }
//        return res < 0100 ? res : -1;

        int n = nums.length;
        int[] l = new int[n];
        l[0] = nums[0];
        for (int i = 1; i < n; i++) {
            l[i] = Math.min(l[i - 1], nums[i]);
        }
        int[] r = new int[n];
        r[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            r[i] = Math.min(r[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > l[i - 1] && nums[i] > r[i + 1]) {
                ans = Math.min(ans, l[i - 1] + nums[i] + r[i + 1]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public int maxProfit(int[] prices) {
        // dp[i][0]第i天持有股票的最大利润
        // dp[i][1]第i天不持有股票, 处理冷冻期
        // dp[i][2]第i天不持有股票
        int n = prices.length;
        int[][] dp = new int[n][2];
        // dp[i][0] = max(dp[i-1][0], p[i-1][2] - prives[i])
        // dp[i][1] = max(dp[i-1][0] +prives[i])
        // dp[i][2] = max(dp[i-1][1], dp[i-1][2])
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 0; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    /**
     * 比较大小实用,Integet.compare, 不要实用a[0] - b[0]: 差值会溢出错误!!
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        // 区间合并(数据溢出)
//        Arrays.sort(points, (a,b) -> Integer.compare(a[1], b[1]));
//        int preL = points[0][0], preR = points[0][1];
//        int n = points.length;
//        List<int[]> list = new ArrayList<>();
//        list.add(new int[]{preL, preR});
//        for (int i = 1; i < n; i++) {
//            int x = points[i][0], y = points[i][1];
//            if (x <= list.get(list.size()-1)[1]) {
//                // 合并
//                list.get(list.size()-1)[1] = Math.min(list.get(list.size()-1)[1], y);
//            } else {
//                list.add(new int[]{x, y});
//            }
//        }
//        return list.size();

        // 只统计不能合并的区间
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        System.out.println(Arrays.deepToString(points));
        int preL = points[0][0], preR = points[0][1];
        int n = points.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (x > preR) {
                preL = points[i][0];
                preR = points[i][1];
                cnt++;
            }
        }
        return cnt;
    }

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Set<Integer>[] anc = new Set[n]; // 记录每个节点的祖先
        for (int i = 0; i < n; i++) {
            anc[i] = new HashSet<Integer>();
        }
        List<Integer>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<>();
        }
        /// 入度表
        int[] indeg = new int[n];
        for (int[] edge : edges) {
            e[edge[0]].add(edge[1]);
            ++indeg[edge[1]];
        }
        // bfs
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : e[u]) {
                anc[v].add(u);
                for (int i : anc[u]) {
                    anc[v].add(i);
                }
                --indeg[v];
                if (indeg[v] == 0) {
                    q.offer(v);
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j : anc[i]) {
                res.get(i).add(j);
            }
            Collections.sort(res.get(i));
        }
        return res;
    }

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        List<int[]> isLand = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int x = cell[0], y = cell[1];
                        isLand.add(cell);
                        for (int k = 0; k < dir.length; k++) {
                            int nx = x + dir[i][0], ny = y + dir[i][1];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                                queue.offer(new int[]{nx, ny});
                                grid[nx][ny] = -1; // 遍历过的cell置为-1
                            }
                        }
                    }
                    // 当前岛屿所有的1遍历
                    for (int[] cell : isLand) {
                        queue.offer(cell);
                    }
                    int step = 0;
                    while (!queue.isEmpty()) {
                        int sz = queue.size(); // 这是岛屿边界点的集合大小, 因为我们从边界扩展bfs
                        for (int k = 0; k < sz; k++) {
                            int[] cell = queue.poll();
                            int x = cell[0], y = cell[1];
                            for (int d = 0; d < dir.length; d++) {
                                int nx = x + dir[d][0], ny = y + dir[d][1];
                                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                                    if (grid[nx][ny] == 0) { // 表示可以扩展
                                        queue.offer(new int[]{nx, ny});
                                        ;
                                        grid[nx][ny] = -1;
                                    } else if (grid[nx][ny] == 1) { // 到达另一个岛屿的1
                                        return step;
                                    }
                                }
                            }
                        }
                        step++; // 一层的bfs结束
                    }
                }
            }
        }
        return 0;
    }


//    List<int[]>[] graph;
//
//    public Graph(int n, int[][] edges) {
//        graph = new List[n];
//        for (int i = 0; i < n; i++) {
//            graph[i] = new ArrayList<>();
//        }
//        for (int[] edge : edges) {
//            graph[edge[0]].add(new int[]{edge[1], edge[2]});
//        }
//    }
//
//    public void addEdge(int[] edge) {
//        graph[edge[0]].add(new int[]{edge[1], edge[2]});
//    }
//
//    public int shortestPath(int node1, int node2) {
//        // cost, 终点
//        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
//        int[] dist = new int[graph.length];
//        Arrays.fill(dist, Integer.MAX_VALUE);
//        dist[node1] = 0;
//        q.offer(new int[]{0, node1});
//        while (!q.isEmpty()) {
//            int[] cell = q.poll();
//            int cost = cell[0], end = cell[1];
//            if (node2 == end) {
//                return cost;
//            }
//            for (int[] arr : graph[end]) {
//                int nxt = arr[0], nxtCost = arr[1];
//                if (dist[nxt] > nxtCost + cost) {
//                    dist[nxt] = nxtCost + cost;
//                    q.offer(new int[]{dist[nxt], nxt});
//                }
//            }
//        }
//        return -1;
//    }


    public boolean isValidSerialization(String preorder) {
//        int n  = preorder.length();
//        int i = 0;
//        //栈中的每个元素，代表了对应节点处剩余槽位的数量
//        //栈顶元素就对应着下一步可用的槽位数量
//        Deque<Integer> stk = new LinkedList<>();
//        //第141个测试用例preorder=“#”，返回true, 根节点需要一个槽位
//        stk.push(1);
//        while (i < n) {
//            if (stk.isEmpty()) {
//                return false;
//            }
//            if (preorder.charAt(i) == ',') {
//                i++;
//            } else if (preorder.charAt(i) == '#') {
//                // 空节点：补充一个槽位
//                int top = stk.pop() - 1;
//                if (top > 0) {
//                    stk.push(top);
//                }
//                i++;
//            } else {
//                // 数字
//                while (i < n && preorder.charAt(i) != ',') {
//                    i++;
//                }
//                int top = stk.pop() - 1;
//                if (top > 0) {
//                    stk.push(top);
//                }
//                stk.push(2);
//            }
//        }
//        return stk.isEmpty();


        //
        int n = preorder.length();
        int count = 0;
        // 表示当前节点剩余的槽位
        Deque<Integer> stk = new LinkedList<>();
        stk.push(1); // 根节点开始剩余一个槽位
        int i = 0;
        while (i < n) {
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                // 空节点: 槽位需要一个槽位
                count--;
                i++;
            } else {
                // 数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                count = count - 1 + 2;

            }
        }
        return count == 0;
    }

    @Test
    public void tets() {
        List<Integer> list = new ArrayList<>(Arrays.asList(7, 2, 4, 4, 6, 5, 4, 1, 2, 1));
//        Random random = new Random(5);
//        for (int i = 0; i < 10; i++) {
//            list.add(random.nextInt(10));
//        }
//        list = []

        System.out.println("list = " + list);
        List<Integer> idx = new ArrayList<>(Arrays.asList(3, 5));
        for (int index : idx) {
            for (int i = index; i < list.size(); i++) {
                list.set(i, list.get(i) + 2);
            }
        }
        System.out.println("list = " + list);

    }


    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode c = head;
        while (c != null) {
            list.add(c);
            c = c.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) break;
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }


    public List<TreeNode> allPossibleFBT(int n) {
//        /// 左子树和右子树的结点数之和是 n-1: (1, n-2), (2, n-3)...
//        // n==1, 是真二叉树
//        // n > 1: 枚举左右子树的节点数, 递归构造子树
//        List<TreeNode> res = new ArrayList<>();
//        if (n < 0) return new ArrayList<>();
//        if (n == 1) {
//            res.add(new TreeNode(0));
//            return res;
//        }
//        if (n % 2 == 0) {
//            return res;
//        }
//        for (int i = 1; i < n; i+=2) {
//            List<TreeNode> leftTree  = allPossibleFBT(i);
//            List<TreeNode> rightTree  = allPossibleFBT(n - i - 1);
//            for (TreeNode subLTree : leftTree) {
//                for (TreeNode subRTree : rightTree) {
//                    TreeNode root = new TreeNode(0, subLTree, subRTree);
//                    res.add(root);
//                }
//            }
//        }
//        return res;

        // dp
        //一棵有 n 个节点的真二叉树恰好有 (n+1) / 2
        // f[i]: i个叶子的真二叉树
        // 左子树有j = 1...i-1个叶子, f[j], 右子树叶子数为i-j, f[i - j]
        // 求f[(n+1)/2]
        if (n % 2 == 0) return new ArrayList<>();
        List<TreeNode>[] f = new List[11];
        Arrays.setAll(f, i -> new ArrayList<>());
        f[1].add(new TreeNode());
        // 计算f[i]
        for (int i = 2; i < f.length; i++) {
            // 枚举左子树叶子
            for (int j = 1; j < i; j++) {
                for (TreeNode left : f[j]) {
                    for (TreeNode right : f[i - j]) {
                        f[i].add(new TreeNode(0, left, right));
                    }
                }
            }
        }
        return f[(n + 1) / 2];
    }


    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
//        return dfs(cloned, target);

        // 同时bfs
        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<TreeNode> q2 = new ArrayDeque<>();
        q1.offer(original);
        q2.offer(cloned);
        while (q1.size() > 0) {
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();
            if (node1 == target) {
                return node2;
            }
            if (node1.left != null) {
                q1.offer(node1.left);
                q2.offer(node2.left);
            }
            if (node1.right != null) {
                q1.offer(node1.right);
                q2.offer(node2.right);
            }
        }
        return null;
    }

    private TreeNode dfs(TreeNode cloned, TreeNode target) {
        if (cloned == null) return null;
        if (cloned.val == target.val) {
            return cloned;
        }
        TreeNode left = dfs(cloned.left, target);
        if (left != null) {
            return left;
        }
        return dfs(cloned.right, target);
    }


    public ListNode reverseList(ListNode head) {
        return reverDfs(null, head);
    }

    private ListNode reverDfs(ListNode pre, ListNode cur) {
        if (cur == null) return pre;
        ListNode t = reverDfs(cur, cur.next);
        cur.next = pre;
        return t; // 返回的是新的头节点
    }


    // ------------------------周赛---------------------------------------
    public int longestMonotonicSubarray(int[] nums) {
        // 设置inc = nums[i+1] > nums[i]: 递增
        // 记录最长子数组左右
        int anslen = 1;
        int n = nums.length;
        int i = 0;
        while (i < n - 1) {
            if (nums[i + 1] == nums[i]) {
                i++;
                continue;
            }
            int i0 = i;
            boolean inc = nums[i] < nums[i + 1];
            i += 2; // 从i + 2开始
            while (i < n && inc && nums[i] != nums[i - 1] && nums[i] > nums[i - 1]) {
                i++;
            }
            // 更新
            anslen = Math.max(anslen, i - i0);
            i--;
        }

        return anslen;


    }

    public String getSmallestString(String s, int k) {
        // 把s[i] -> a的操作次数<=k, 就改变s[i] -> a, k -= 次数
        // 否则s[i] - k, 退出循环
        char[] t = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int ops = Math.min(t[i] - 'a', 'z' - t[i] + 1); //'z' - t[i] + 1: 如果t[i]在26字母中后半段, +1是转为'a'

            if (ops > k) {
                t[i] -= k; // 只转最大能转的次数
                break;
            }
            t[i] = 'a';
            k -= ops;
        }
        return String.valueOf(t);
    }

    public long minOperationsToMakeMedianK(int[] nums, int k) {
        // 前面要求的是k作为中位数
        // 排序后:目前的中位数 假设索引:m
        // 如果nums[m] > k(减小左边的值) 在nums[m]左边(包括m)的的 如果nums[i] > k 则变为k
        // 如果nums[m] < k(增加右边的值) 在nums[m]右边(包括m)的的 如果nums[i] < k 则变为k
        Arrays.sort(nums);
        int n = nums.length;
        int m = nums.length / 2;
        int mid = nums[m];
        long ans = 0;
        if (mid > k) {
            for (int i = 0; i < m + 1 && nums[i] > k; i++) { // 这里不能从0..m便利,因为我们在排序数组中优先减少的是比较傲大的nums[i]
                ans += (nums[i] - k);
            }
        } else if (mid < k) {
            for (int i = m; i < n && nums[i] < k; i++) {
                ans += (k - nums[i]);
            }
        }

        return ans;
    }


    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        // dfs
//        List<int[]>[] g = new List[n];
//        Arrays.setAll(g, i -> new ArrayList<>());
//        for (int[] edge : edges) {
//            int x = edge[0], y = edge[1], w = edge[2];
//            g[x].add(new int[]{y, w});
//            g[y].add(new int[]{x, w});
//        }
//
//        // 使用ids记录所在联通块的编号(dfs中智慧遍历系统联通号的块)
//        int[] ids = new int[n];
//        Arrays.fill(ids, -1);
//        List<Integer> ccAnd = new ArrayList<>(); // 记录边权
//        for (int i = 0; i < n; i++) {
//            if (ids[i] < 0) {
//                // 联通块的编号就是当前ccAnd的大小
//                // i的联通的点
//                ccAnd.add(dfs(i, ccAnd.size(), g, ids));
//            }
//        }
//        int[] ans = new int[query.length];
//        for (int i = 0; i < n; i++) {
//            int s = query[i][0], e = query[i][1];
//            if (s == e) {
//                ans[i] = 0;
//            } else if (ids[s] != ids[e]) {
//                // 不在同一联通块
//                ans[i] = -1;
//            } else {
//                ans[i] = ccAnd.get(s);
//            }
//        }
//        return ans;

        // 并查集
        int[] fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
        int[] and = new int[n]; // 联通块路径
        Arrays.fill(and, -1);
        for (int[] e : edges) {
            int x = find(e[0], fa);
            int y = find(e[1], fa);
            and[y] &= e[2];
            if (x != y) { // 不在同一个联通块下
                and[y] &= and[x];
                fa[x] = y;
            }
        }
        int[] ans = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int s = query[i][0], t = query[i][1];
            ans[i] = s == t ? 0 : find(s, fa) != find(t, fa) ? -1 : and[find(s, fa)];
        }
        return ans;
    }

    public static int find(int x, int[] fa) {
        if (x != fa[x]) fa[x] = find(fa[x], fa);
        return fa[x];
    }

    private int dfs(int x, int curId, List<int[]>[] g, int[] ids) {
        ids[x] = curId;
        int ans = -1; // -1 与任何值x进行&都是x
        for (int[] e : g[x]) {
            ans &= e[1];
            if (ids[e[0]] < 0) {
                ans &= dfs(e[0], curId, g, ids);
            }
        }
        return ans;
    }

    //---------------------------------------------------
    public int trap(int[] height) {
        // stack: 栈底到栈顶递减
        int n = height.length;
        if (n <= 2) return 0;
        Stack<Integer> stk = new Stack<>();
        stk.push(0);
        int maxA = 0;
        for (int i = 1; i < n; i++) {
            while (!stk.isEmpty() && height[i] > height[stk.peek()]) {
                int mid = stk.pop();
                if (!stk.isEmpty()) {
                    // 有水坑
                    int l = stk.peek();
                    int h = Math.min(height[l], height[i]) - height[mid];
                    maxA += h * (i - l + 1);
                }
            }
            stk.push(i);
        }
        return maxA;
    }
}




