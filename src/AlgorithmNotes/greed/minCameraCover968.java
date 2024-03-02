package AlgorithmNotes.greed;

class TreeNode {
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

public class minCameraCover968 {
    /**
     * minCameraCover
     * 给定树的节点数的范围是 [1, 1000]。
     * 每个节点的值都是 0
     */

    /**
     * 0: 该节点无覆盖 1: 该节点有摄像头 2: 该节点有覆盖
     */
    int res = 0;
    public int minCameraCover(TreeNode root) {
        if (dfs(root) == 0) {
            // 如果此时root还是吴覆盖的状态,res++;
            return res+1;
        }
        return res;
    }

    /**
     * 返回的是node的状态
     * @param node
     * @return
     */
    private int dfs(TreeNode node) {
        if (node == null) {
            return 2;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left == 2 && right == 2) {
            return 0;
        }
        if (left == 0 || right == 0) {
            // 仅有一个被覆盖，这种必须加摄像头
            res++;
            return 1;
        }
        return 2; //
    }
}
