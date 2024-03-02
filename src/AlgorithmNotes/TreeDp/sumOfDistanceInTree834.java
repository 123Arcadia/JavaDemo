package AlgorithmNotes.TreeDp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sumOfDistanceInTree834 {
    /**
     * 834. 树中距离之和
     * (1) 换根
     */

    //1 <= n <= 3 * 10^4
    //edges.length == n - 1
    //edges[i].length == 2
    //0 <= ai, bi < n
    //a_i != b_i
    private List<Integer>[] g;
    // size: 该子树的大小
    // nas[]: answer[i] 是树中第 i 个节点与所有其他节点之间的距离之和
    private int[] ans, size;


    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        ans = new int[n];
        size = new int[n];
        dfs(0, -1, 0);
        reroot(0, -1);
        return ans;
    }

    private void reroot(int x, int fa) {
        for (int y : g[x]) {
            if (y!=fa) {
                // 以x为根节点, xy交换后，y的子树距离都减1，其他节点的距离加1
                // ans[y] = ans[x] + g.length - 2 * size[y];
                ans[y] = ans[x] + (g.length - size[y])- size[y];
                reroot(y,x);
            }
        }
    }

    private void dfs(int x, int fa, int depth) {
        ans[0] += depth; // 0到x的距离
        size[x] = 1; // 以x为根节点的子树大小
        for (int y : g[x]) {
            if (y!=fa) {
                dfs(y, x, depth+1); // 以y为节点向下遍历
                size[x] += size[y];
            }
        }
    }


}
