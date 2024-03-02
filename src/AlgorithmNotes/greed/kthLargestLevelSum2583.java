package AlgorithmNotes.greed;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class kthLargestLevelSum2583 {
    /**
     * 2583. 二叉树中的第 K 大层和
     *
     * 树中的节点数为 n
     * 2 <= n <= 10^5
     * 1 <= Node.val <= 10^6
     * 1 <= k <= n
     */

    public long kthLargestLevelSum(TreeNode root, int k) {
        // 超出10^9
        List<Long> res = new ArrayList<>();
        List<TreeNode> q = List.of(root);
        while (!q.isEmpty()) {
            long sum =0 ;
            List<TreeNode> temp = q;
            q = new ArrayList<>();
            for (TreeNode node : temp) {
                sum += node.val;
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            res.add(sum);
        }
        if (k > res.size() ) {
            return -1;
        }
        Collections.sort(res);
        return res.get(res.size()- k);
    }


    @Test
    @SneakyThrows
    public void test() {
        int x=  1/0;
        System.out.println("x = " + Integer.MAX_VALUE);


        // 类似于
        /*try {
            int x = 1 / 0;
            System.out.println("x = " + x);
        } catch (Throwable var2) {
            throw var2;
        }*/
    }
    public class TreeNode {

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
}
