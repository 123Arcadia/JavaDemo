package AlgorithmNotes.hot100;

import java.util.Stack;

class MinStack {

    /**
     * 在常数时间内检索到最小元素的栈。
     */


    Stack<Integer> stk;
    Stack<Integer> minStk;
    // 维护一个存储最小值的栈，每次push都会比较，把小的值push到辅助栈中，pop是一同弹出

    public MinStack() {
        this.stk = new Stack<>();
        this.minStk = new Stack<>();
        minStk.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stk.push(val);
        minStk.push(Math.min(val, minStk.peek()));
    }

    public void pop() {
        stk.pop();
        minStk.pop();
    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return minStk.peek();
    }
}
