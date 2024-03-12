package AlgorithmNotes.hot100.Tree;

public class TreeLength {


    /**
     * 543. 二叉树的直径
     * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度
     */

    private int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        this.ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int lenL = dfs(node.left);
        int lenR = dfs(node.right);
        ans=Math.max(ans, lenL + lenR);
        return Math.max(lenL, lenR) + 1;
    }


}
