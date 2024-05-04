package AlgorithmNotes;

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

class FindElements {
//    TreeNode root;
//
//    public FindElements(TreeNode root) {
//        root.val = 0;
//        this.root = root;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            if (node.left != null) {
//                node.left.val = node.val * 2 + 1;
//                queue.offer(node.left);
//            }
//            if (node.right != null) {
//                node.right.val = node.val * 2 + 2;
//                queue.offer(node.right);
//            }
//        }
//    }
//
//    public boolean find(int target) {
//        return dfs(root, target);
//    }
//
//    private boolean dfs(TreeNode node, int target) {
//        if (node == null) return false;
//        if (node.val == target) return true;
//        return dfs(node.left,target) || dfs(node.right, target);
//    }

    //-------------------------------------------------------------------------
    /**
     * 方法一：深度优先搜索 + 哈希表
     */
//    private Set<Integer> valSet;
//
//    public FindElements(TreeNode root) {
//        this.valSet = new HashSet<>();
//        dfs(root, 0);
//    }
//
//    public boolean find(int target) {
//        return valSet.contains(target);
//    }
//
//    private void dfs(TreeNode node, int val) {
//        if (node == null) {
//            return;
//        }
//        node.val = val;
//        valSet.add(val);
//        dfs(node.left, val * 2 + 1);
//        dfs(node.right, val * 2 + 2);
//    }

    //-------------------------------------------------------------------------


    /**
     * 方法二：二进制即路径:
     * https://leetcode.cn/problems/find-elements-in-a-contaminated-binary-tree/solutions/2681672/liang-chong-fang-fa-ha-xi-biao-wei-yun-s-6m7w/
     */
}