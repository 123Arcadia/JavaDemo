package AlgorithmNotes.buildTree;

import org.junit.Test;

import java.util.*;


public class InAndPostBuildTree {
    @Test
    public void test() {

    }

    /**
     * 中序和后序建树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idxMap.put(inorder[i], i);
        }
        return dfs(inorder, 0, n, postorder, 0, n, idxMap);
    }

    private TreeNode dfs(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR, Map<Integer, Integer> idxMap) {
        if (inL >= inR || postL >= postR) {
            return null;
        }
        /* */
        return null;
    }


    public int rangeSumBST(TreeNode root, int low, int high) {
//        Queue<TreeNode> que=  new LinkedList<>();
//        que.add(root);
//        int res = 0;
//        while (!que.isEmpty()) {
//            TreeNode node = que.poll();
//            if (node.val >= low && node.val <= high) {
//                res += node.val;
//            }
//            if (node.left!=null) {
//                que.add(node.left);
//            }
//            if (node.right!=null) {
//                que.add(node.right);
//            }
//        }
//        return res;

        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right,low,high);
    }





}
