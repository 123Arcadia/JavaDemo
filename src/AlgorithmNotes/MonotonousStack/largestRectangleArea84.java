package AlgorithmNotes.MonotonousStack;

import org.junit.Test;

import java.util.Stack;

public class largestRectangleArea84 {

    /**
     * 84: largestRectangleArea柱状图中最大的矩形
     */

    public int largestRectangleArea(int[] heights) {
//        // 暴力
//        // 灭给hright[i] 找到左右两边小于height[i]的值
//        int n = heights.length;
//        int[] left = new int[n]; // 存储的是height[i]左边比它大的索引, 到时候计算面积最低高度就是height[i]
//        int[] right = new int[n];
//        left[0] = -1;
//        right[n-1] = n;
//        for (int i = 1; i < n; i++) {
//            int t = i - 1;
//            // 不是if
//            while (t >= 0 && heights[i] <= heights[t]) { // 这里要等于，需要算面积
//                t = left[t];
//            }
//            left[i] = t; // 这里i已经是height[i] > height[t]
//        }
//        for (int i = n - 2; i >= 0; i--) {
//            int t = i + 1;
//            while (t < n && heights[i] <= heights[t]) {
//                t = right[t]; // i - j +j 1
//            }
//            right[i] = t;
//        }
//        int sum = 0;
//        for (int i = 0; i < n; i++) { // 这里不是从[1, n-2], 因为要算面积[0, n-1]
//            // 这里right[i],left[i] 相等于是左开右开区间，我们只需要中间（不包括边界）的长度
//            int t = heights[i] * (right[i] - left[i] - 1);
//            sum = Math.max(t, sum);
//        }
//        return sum;

        // 单调栈
        Stack<Integer> stk = new Stack<>();
        // 两边加上0，是为了找到小于hieght[i]的值从而停止出栈
        int n = heights.length;
        int[] newHeights = new int[n + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int i = 0; i < n; i++) {
            newHeights[i + 1] = heights[i];
        }

        heights = newHeights;
        stk.push(0);
        int res = 0;
        for (int i = 1; i < heights.length; i++) {
            while (!stk.isEmpty() && heights[i] < heights[stk.peek()]) {
                //
                int mid = stk.pop();
                int l = stk.peek();
                int r = i;
                int h = heights[mid];
                int w = r - l - 1;
                res = Math.max(res, h * w);
            }
            stk.push(i);
        }
        return res;
    }

    @Test
    public void test() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int res = largestRectangleArea(heights);
        System.out.println("res = " + res);

    }

}
