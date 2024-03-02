package AlgorithmNotes;

import org.junit.Test;

import java.util.Stack;
class MyQueue {

    public Stack<Integer> A;
    public Stack<Integer> B;
    public MyQueue() {
        A =  new Stack<>();
        B =  new Stack<>();
    }

    public void push(int x) {
        A.push(x);
    }

    public int pop() {
        int p = peek();
        B.pop();
        return p;
    }

    public int peek() {
        if (!B.isEmpty()) {
            return B.peek();
        }
        if (A.isEmpty()) return -1;
        while (!A.isEmpty()) {
            B.push(A.pop());
        }
        return B.peek();
    }

    public boolean empty() {
        return A.isEmpty() && B.isEmpty();
    }
}
public class QueueFromStack {
    @Test
    public void test() {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.peek());
        int pop = queue.pop();
        System.out.println("pop = " + pop);
        System.out.println(queue.A +", " + queue.B);
        System.out.println(queue.peek());
        //1
        //pop = 1
        //2
    }
}
