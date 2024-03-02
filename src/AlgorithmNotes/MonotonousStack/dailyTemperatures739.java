package AlgorithmNotes.MonotonousStack;

import java.util.Stack;

public class dailyTemperatures739 {
    /**
     * 单调栈: 739. 每日温度
     *
     * 要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置，此时我们就要想到可以用单调栈了。时间复杂度为O(n)
     */

    public int[] dailyTemperatures(int[] temperatures) {
        //记录当前最大温度所在索引
        int n = temperatures.length;
        Stack<Integer> stk = new Stack<Integer>(); // 记录索引
        stk.push(0);
        int[] res = new int[n];
        for (int i = 1; i < n; i++) {
            while (!stk.isEmpty() && temperatures[i] > temperatures[stk.peek()]) {
                int idx = stk.pop();
                res[idx] = i - idx;
            }
            stk.push(i);
        }
        return res;
    }
}
