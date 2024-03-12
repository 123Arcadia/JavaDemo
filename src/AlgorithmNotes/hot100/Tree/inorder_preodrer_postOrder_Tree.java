package AlgorithmNotes.hot100.Tree;

import java.util.*;

public class inorder_preodrer_postOrder_Tree {

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }


    /**
     * 前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        // node != null为了在叶子叶子结点left,right时为空，接着遍历stk中
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    /**
     * 后序遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        // 当遍历到叶子节点时使用prev记录当前节点（上一个root的left节点）
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); // null

            if (root.right == null || root.right == prev) {
                res.add(root.val);
                // 避免重复访问右子树[记录当前节点便于下一步对比]
                prev = root;
                // 避免重复访问左子树[设空节点]
                root = null;
            } else {
                // 2.遍历最左子节点的右子树(右子树存在 && 未访问过)
                stack.push(root);
                root = root.right;
            }
        }
        return res;

        //        //迭代：
        //        List<Integer> res = new ArrayList<>();
        //        if (root == null) {
        //            return res;
        //        }
        //        Stack<TreeNode> stk = new Stack<TreeNode>();
        //        stk.add(root);
        //        while (!stk.isEmpty()) {
        //            TreeNode node = stk.pop();
        //            res.add(node.val);
        //            if (node.left != null) {
        //                stk.add(node.left);
        //            }
        //            if (node.right != null) {
        //                stk.add(node.right);
        //            }
        //        }
        //        // 后序：左右中
        //        Collections.reverse(res);
        //        return res;
    }


    /**
     * 层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
            }
        }
        return res;
    }


    //--------------------------------N叉树--------------------------------

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    /**
     * 590. N 叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        //     List<Integer> list =new ArrayList<>();
        //     if (root == null) {
        //         return list;
        //     }
        // //    dfs(root, list);
        //     Deque<Node> stack = new ArrayDeque<>();
        //     // Stack<Node> stack = new Stack<>();
        //     stack.add(root);
        //     while (!stack.isEmpty()) {
        //         Node pop = stack.pop();
        //         list.add(pop.val);
        //         if (pop.children != null) {
        //             for (int i = pop.children.size() - 1; i >= 0; i--) {
        //                 stack.add(pop.children.get(i));
        //             }
        //         }
        //     }

        //     Collections.reverse(list);
        //     return list;

        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Map<Node, Integer> map = new HashMap<Node, Integer>();
        Deque<Node> stack = new ArrayDeque<Node>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                List<Node> children = node.children;
                if (children != null && children.size() > 0) {
                    map.put(node, 0);
                    node = children.get(0);
                } else {
                    node = null;
                }
            }
            node = stack.peek();
            int index = map.getOrDefault(node, -1) + 1;
            List<Node> children = node.children;
            if (children != null && children.size() > index) {
                map.put(node, index);
                node = children.get(index);
            } else {
                res.add(node.val);
                stack.pop();
                map.remove(node);
                node = null;
            }
        }
        return res;

    }

    private void dfs(Node node, List<Integer> list) {
        if(node == null) {
            return ;
        }
        for (int i = 0; i < node.children.size(); i++) {
            dfs(node.children.get(i), list);
        }
        list.add(node.val );
    }
}
