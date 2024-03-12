package AlgorithmNotes.hot100.Tree;

public class mergeTree {

    /**
     * 617. 合并二叉树
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 1. dfs
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);
        return node;

//        //2. bfs记录每层节点，计算每层的和
//        if (root1 == null && root2 == null) return null;
//        if (root1 == null) return root2;
//        if (root2 == null) return root1;
//        Queue<TreeNode> q1 = new LinkedList<>();
//        Queue<TreeNode> q2 = new LinkedList<>();
//        q1.offer(root1);
//        q2.offer(root2);
//        Queue<TreeNode> qTotal = new LinkedList<>();
//        TreeNode root = new TreeNode(root1.val + root2.val);
//        qTotal.offer(root);
//        // 这里已经把新根节点添加了，所以下面只需处理各自的子节点的层数之和
//        while (!q1.isEmpty() && !q2.isEmpty()) {
//            TreeNode node1 = q1.poll();
//            TreeNode node2 = q2.poll();
//            TreeNode node = qTotal.poll();
//            TreeNode left1 = node1.left, right1 = node1.right;
//            TreeNode left2 = node2.left, right2 = node2.right;
//            if (left1 != null && left2 != null) {
//                TreeNode left = new TreeNode(left1.val + left2.val);
//                node.left = left;
//                qTotal.offer(left);
//                q1.offer(left1);
//                q2.offer(left2);
//            } else if (left1 == null) {
//                node.left = left2;
//                // 这里没必须向queue们添加了s
//            } else {
//                node.right = right1;
//            }
//            if (right1 != null && right2 != null) {
//                TreeNode right = new TreeNode(right1.val + right2.val);
//                node.right = right;
//                qTotal.offer(right);
//                q1.offer(right2);
//                q2.offer(right2);
//            } else if (right1 == null) {
//                node.right = right2;
//                // 这里没必须向queue们添加了s
//            } else {
//                node.right = right1;
//            }
//        }
//        return root;
    }


}
