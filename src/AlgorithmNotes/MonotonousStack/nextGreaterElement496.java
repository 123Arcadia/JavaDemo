package AlgorithmNotes.MonotonousStack;

import org.junit.Test;

import java.util.*;

public class nextGreaterElement496 {

    /**
     * 496. 下一个更大元素 I
     *
     * 递减栈就 是求右边第一个比自己小的元素了
     */

    //其中nums1 是 nums2 的子集
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 遍历nums1寻找nums2的下一个更大的元素
        Deque<Integer> stk =new ArrayDeque<>();
        Map<Integer, Integer> map=  new HashMap<>();
        int[] res = new int[nums1.length];
        for (int i = nums2.length-1; i >= 0; i--) {
            int num = nums2[i];
            while (!stk.isEmpty() && num >= stk.peek()) {
                stk.pop();
            }
            map.put(num, stk.isEmpty() ? -1 : stk.peek());
            stk.push(num);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }


    @Test
    public void test() {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};

        String t = "";
        for (int num : nums1) {
            System.out.println("last: " + t + ", num = " + num);
            t = t + "_"+num;
        }

    }
}
