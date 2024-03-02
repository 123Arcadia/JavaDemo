package AlgorithmNotes.MonotonousStack;

import java.util.Stack;

public class trap42 {

    /**
     * 42: 接雨水
     */
    public int trap(int[] height) {
//        int n = height.length;
//        if (n < 2) return 0;
//        int[] maxLeft = new int[n];
//        int[] maxRight = new int[n];
//        maxLeft[0] = height[0];
//        maxRight[n-1] = height[n-1];
//        for (int i = 1; i < n ; i++) {
//            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
//        }
//        for(int i = n - 2; i >= 0; i--) {
//            maxRight[i] = Math.max(maxRight[i+1], height[i]);
//        }
//        int sum = 0;
//        for (int i = 1; i < n-1; i++) {
//            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
//            sum += Math.max(count, 0);
//        }
//        return sum;


        int n = height.length;
        if (n <= 2) return 0;
        Stack<Integer> stk = new Stack<>(); // 存储索引
        stk.push(0);
        int sum = 0;
        for (int i = 1; i < n; i++) {
            // 出现凸槽，接下俩计算stk中存储的高度造成的水量
            while (!stk.isEmpty() && height[i] > height[stk.peek()]) {
                int mid = stk.pop();
                if (!stk.isEmpty()) {
                    int h = Math.min(height[i], height[stk.peek()]) - height[mid]; // 高度差
                    int w = i - stk.peek() - 1; // 只要中间的宽度
                    sum += h * w;
                }
            }
            stk.push(i);
        }
        return sum;
    }
}
