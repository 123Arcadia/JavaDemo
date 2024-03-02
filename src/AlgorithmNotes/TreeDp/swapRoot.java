package AlgorithmNotes.TreeDp;

import java.util.*;

public class swapRoot {

    /**
     * rootCount: 2581. 统计可能的树根数目
     *
     * （1）换根
     * （2）数据压缩 / hash -> 两个 4 字节 int 压缩成一个 8 字节 long : (long)e[0] << 32 | e[1]
     */
    private List<Integer>[] g; // 该节点 - 子节点
    private Set<Long> s = new HashSet<>();
    private int k , ans, cnt0;

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        this.k = k;
        g = new List[edges.length+1];
        Arrays.setAll(g, s -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        for (int[] e : guesses) {
            s.add((long)e[0] << 32 | e[1]); // 两个 4 字节 int 压缩成一个 8 字节 long
        }
        // 先以0为根节点，后续只需要交换根节点即可
        dfs(0, -1);
        redfs(0, -1, cnt0);
        return ans;
    }

    private void redfs(int x, int fa, int cnt0) {
        if (cnt0 >= k) { // 此时 cnt0 就是以 x 为根时的猜对次数
            ans++;
        }
        for (int y : g[x]) { // 这里第二次遍历，假设换根
            if (y != fa) {
                int c = cnt0;
                if (s.contains((long) x << 32 | y)) c--; // 遇见原来，则猜对次数减1
                if (s.contains((long) y << 32 | x)) c++;
                redfs(y,x, c);
            }
        }
    }

    private void dfs(int x, int fa) {
        for (int y : g[x]) {
            if (y != fa) {
                if (s.contains((long)x << 32 | y)) {
                    cnt0++; //猜对了一次
                }
                dfs(y, x);
            }
        }
    }
}
