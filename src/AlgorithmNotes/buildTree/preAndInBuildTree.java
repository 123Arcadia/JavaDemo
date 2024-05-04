package AlgorithmNotes.buildTree;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
public class preAndInBuildTree {
    /**
     * 前序遍历和中序遍历，构建二叉树
     */
    @Test
    public void buildTree() {

    }
    //输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    //输出: [3,9,20,null,null,15,7]

    public TreeNode buildTree01(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0) {
            return null;
        }
        int leftSize= indexOf(inorder, preorder[0]); // 该节点的索引
        int[] pre1 = Arrays.copyOfRange(preorder, 1, 1 + leftSize);
        int[] pre2 = Arrays.copyOfRange(preorder, 1 + leftSize, n);
        int[] in1 = Arrays.copyOfRange(inorder, 0, leftSize);
        int[] in2 = Arrays.copyOfRange(inorder, leftSize + 1, n);
//        TreeNode leftTree = buildTree(pre1, in1);
        TreeNode leftTree = buildTree01(pre1, in1);
//        TreeNode rightTree = buildTree(pre2, in2);
        TreeNode rightTree = buildTree01(pre2, in2);
        return new TreeNode(preorder[0], leftTree, rightTree);
    }

    /**
     * 返回x的下标 (这里使用hash优化为O(1)查询)
     * @param inorder
     * @param x
     * @return
     */
    private int indexOf(int[] inorder, int x) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 2. 使用hash优化
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree02(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer, Integer> index= new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            index.put(inorder[i], i);
        }
        return dfs(preorder, 0, n, inorder, 0, n, index); //左闭右开
    }

    private TreeNode dfs(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, Map<Integer, Integer> index) {
        if (preL == preR) {
            return null;
        }
        int leftSize = index.get(preorder[preL]) - inL;
        TreeNode l = dfs(preorder, preL + 1, preL + 1 + leftSize
                        , inorder, inL, inL + leftSize, index);
        TreeNode r = dfs(preorder, preL + 1 + leftSize, preR
                , inorder, inL + leftSize + 1, inR, index);
        return new TreeNode(preorder[preL], l, r);
    }



    //-----------------------------------------------------

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
//        if (n == 0) return null;
//        // 由preorder[0]找到inorder中的root的索引
//        int leftSize = indexOf(inorder, preorder[0]);
//        int[] pre1 = Arrays.copyOfRange(preorder, 1, 1 + leftSize);
//        int[] in1 = Arrays.copyOfRange(preorder, 0 , leftSize);
//        int[] pre2 = Arrays.copyOfRange(preorder, 1 + leftSize, n);
//        int[] in2 = Arrays.copyOfRange(inorder, 1 + leftSize, n);
//        TreeNode left = buildTree01(pre1, in1);
//        TreeNode right = buildTree01(pre2, in2);
//        return new TreeNode(preorder[0], left, right);
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i : inorder) {
            idxMap.put(inorder[i], i);
        }
        return dfsBuiTree(preorder, 0, n, inorder,0, n, idxMap);
    }

    private TreeNode dfsBuiTree(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, Map<Integer, Integer> idxMap) {
        if (preL >= preR) return null;
        int leftSize = idxMap.get(inorder[preL]) - inL;
        TreeNode left = dfsBuiTree(preorder, preL + 1, preL + leftSize + 1, inorder, inL, inL + leftSize + 1, idxMap);
        TreeNode right = dfsBuiTree(preorder, preL + leftSize + 1, preR, inorder, inL + leftSize + 1, inR, idxMap);
        return new TreeNode(preorder[preL], left, right);

    }


}
