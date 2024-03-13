package AlgorithmNotes.hot100;

import AlgorithmNotes.hot100.Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class rob337 {


    Map<TreeNode, Integer> g = new HashMap<>();
    Map<TreeNode, Integer> f = new HashMap<>();
    public int rob(TreeNode root) {
        //f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
        //g(o) 表示不选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
        // l 和 r 代表 o 的左右孩子。
        // o被选中： f(o) = g(l) + g(r)
        // o没被选中: 其左右子节点可以别选中也可以不被选中，
        // g(o)  = max(f(l), g(l)) +max(f(r), g(r))
        // dfs(root);
        // return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));

        // 2. 优化
        int[] ret = dfs(root);
        // ret[0]: 选中该节点, [1]:表示没选中
        return Math.max(ret[0], ret[1]);
    }

    // private void dfs(TreeNode node) {
    //     if (node == null) return ;
    //     dfs(node.left);
    //     dfs(node.right);
    //     // 选择node时
    //     f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
    //     g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0))
    //             + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    // }


    private int[] dfs(TreeNode node) {
        if (node == null){
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int selected = node.val + l[1] + r[1];
        int noselected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return  new int[]{selected, noselected};
    }

}
