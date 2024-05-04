package AlgorithmNotes.MonotonousStack;

import java.util.Stack;

public class trap42 {

    /**
     * 42: 接雨水
     */
    public int trap(int[] height) {
        /**
         * 接雨水
         */
//            // 对每个柱找到不小于它的第二个柱
//            int n = height.length;
//            if (n < 2) {
//                return 0;
//            }
//            int[] maxL = new int[n];
//            int[] maxR = new int[n];
//            maxL[0] = height[0];
//            maxR[n - 1] = height[n - 1];
//            for (int i = 1; i < n; i++) {
//                // 知道对于height[i]的左边最大的柱子
//                maxL[i] = Math.max(maxL[i - 1], height[i]);
//            }
//            //同理
//            for (int i = n-2; i >= 0; i--) {
//                maxR[i] = Math.max(maxR[i + 1], height[i]);
//            }
//            int sum = 0;
//            // Bianli [1,n-1]的柱子
//            for (int i = 1; i < n - 1; i++) {
//                int count = Math.min(maxL[i], maxR[i]) - height[i];
//                sum += count;
//            }
//            return sum;


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
