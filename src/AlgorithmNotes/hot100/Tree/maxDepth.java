package AlgorithmNotes.hot100.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class maxDepth {

    int maxDepth = 0;
    public int maxDepth(TreeNode root) {
//        if (root == null) return 0;
//       ;
//        return  dfs(root, 0);

        //. bfs
        if (root == null) return 0;
        Queue<TreeNode> queue=  new LinkedList<>();
        queue.offer(root);
        int ans =0 ;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left!=null) {
                    queue.offer(node.left);
                }
                if (node.right!=null) {
                    queue.offer(node.right);
                }
            }
            ans++;
        }
        return ans;
    }

    private int dfs(TreeNode node, int depth) {
        if (node == null) {
            return depth;
        }

        return Math.max(dfs(node.left, depth+1), dfs(node.right, depth+1));
    }
}
