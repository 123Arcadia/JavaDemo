package AlgorithmNotes.hot100.Tree;


/**
 * 124. 二叉树中的最大路径和
 */
public class maxPathSum {
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // 节点贡献值: 在以该节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {

        if (node == null) return  0;
        int leftmax = Math.max(0, maxGain(node.left));
        int rightmax = Math.max(0, maxGain(node.right));

        maxSum = Math.max(maxSum, node.val + leftmax + rightmax);
        // 返回以node为根节点的和起点的最长路径
        return node.val + Math.max(leftmax, rightmax);
    }


}
