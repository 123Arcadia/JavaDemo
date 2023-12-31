package Collection.List.Deque;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class dequeTest {
    @Test
    public void DequeueTest() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(4);
        deque.add(2);
        System.out.println("deque = " + deque); // deque = [1, 4, 2]
        System.out.println("deque.size() = " + deque.size()); // deque = 3
        Integer pop = deque.pop();
        System.out.println("pop = " + pop); // 1
        /**
         * 此时 11 会加在前面，因为 push 适应的是 栈
         */
        deque.push(11); // 等于addFirst()
        System.out.println("deque.getFirst() = " + deque.getFirst()); // 11
        System.out.println("push-->deque = " + deque);
        System.out.printf("%-5d\n", deque.getLast()); // 2
        boolean offerFirst = deque.offerFirst(3);
        System.out.println("offerFirst = " + offerFirst); // true
        System.out.println("deque = " + deque); // deque = [3, 11, 4, 2]
        System.out.println("deque.peek() = " + deque.peek()); // 3 等于peekFirst()
    }
}
