package AlgorithmNotes.test;

import AlgorithmNotes.hot100.Tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Graph {

    List<int[]>[] graph;

    public Graph(int n, int[][] edges) {
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int x = e[0], y = e[1], w = e[2];
            graph[x].add(new int[]{y, w});
        }

    }

    public void addEdge(int[] edge) {
        int x = edge[0], y = edge[1], w = edge[2];
        graph[x].add(new int[]{y, w});
    }

    public int shortestPath(int node1, int node2) {
        // 使用堆: [0]: 代价 [1]: 点
        PriorityQueue<int[]> qp = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        qp.offer(new int[]{0, node1}); // 到node1的cost是0
        while (!qp.isEmpty()) {
            int[] arr = qp.poll();
            int c = arr[0], cur = arr[1];
            if (cur == node2) {
                return c;
            }
            for (int[] nxt : graph[cur]) {
                int nxtNode = nxt[0], nxtCost = nxt[1];
                if (dist[nxtNode] > c + nxtCost) {
                    dist[nxtNode] = c + nxtCost;
                    qp.offer(new int[]{c + nxtCost, nxtNode});
                }
            }
        }
        return -1;
    }


    //-----------------------------------
    public int countSubstrings(String s) {
        // dp[i][j]: i .. j 是否是回文
        // s[i] == s[j] && (j - i < 2 || dp[i+1][j-1])是, dp[i][j] = true;
//        boolean[][] dp = new boolean[s.length()][s.length()];
//        int ans = 0;
//        for (int j = 0; j < s.length(); j++) {
//            for (int i = 0; i <= j; i++) {
//                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
//                    dp[i][j] = true;
//                    ans++;
//                }
//            }
//        }
//        return ans;

        // 中心扩展法
        int ans = 0;
        // 中心是单个字符和所有的两字符(两个字符有单个字符扩展一次,最后一个字符不能扩展排除)
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            int l = center / 2;
            int r = center / 2 + center % 2; // center是偶数, l == r; 否则r = l + 1;
            while (l >= 0 & r < s.length() && s.charAt(l) == s.charAt(r)) {
                ans++;
                l--;
                r++;
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }
        for (int i = 0; i < s.length() - p.length(); i++) {
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(i) + p.length() - 'a']++; // 后续再减就等于不变
            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }
        return ans;

    }


    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        // 包含root
        long ret = rootSum(root, (long) targetSum);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);
        return (int) ret;

    }

    private long rootSum(TreeNode root, long targetSum) {
        if (root == null) return 0;
        long ret = 0;
        if (root.val == targetSum) {
            ret++;
        }
        ret += pathSum(root.left, (int) (targetSum - root.val));
        ret += pathSum(root.right, (int) (targetSum - root.val));
        return ret;
    }

    int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
    boolean[][] vis;

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int maxArea = 0;
        this.vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));

                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {
        vis[i][j] = true;
        int len = dir.length;
        int sum = 1;

        grid[i][j] = 0;
        for (int k = 0; k < len; k++) {
            int x = i +dir[k][0], y = j + dir[k][1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !vis[x][y] && grid[x][y] == 1) {
                sum += dfs(grid, x, y);
            } else {
                sum = 0;
            }
        }
        return sum;


    }


}