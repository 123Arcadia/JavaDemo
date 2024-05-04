package AlgorithmNotes.MonotonousStack;

import java.util.Stack;

public class sdd {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // 找到了左右两边最小的柱子
        Stack<Integer> stk = new Stack<>();
        stk.push(0);
        int[] newHeight = new int[n +2];
        for (int i = 0; i < n; i++) {
            newHeight[i+1] = heights[i];
        }
        heights = newHeight;
        // 新数组height头尾部是0
        int res=0;
        for (int i = 1; i < heights.length; i++) {
            System.out.println("["+i+"]"+stk);
            while (!stk.isEmpty() && heights[stk.peek()] > heights[i]) {
                // 出现新的hright[i]柱子噶度变低
                int mid = stk.pop(); // 这里mid为什么要出栈, 因为height[mid]是最高的(算上他左右); 当遍历到i=mid + 1, 我们计算面积找的是最低的柱子
                int l = stk.peek();
                int r = i; // 其左右两边
                int h = heights[mid];
                int w = (r - l - 1);
                res = Math.max(res, h * w);
            }
            System.out.println("["+i+"]"+stk);
            stk.push(i); // i对应的height[i]
        }
        return res;
    }

}
