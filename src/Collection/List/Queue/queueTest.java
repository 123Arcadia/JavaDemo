package Collection.List.Queue;

import org.junit.Test;

import java.util.*;

public class queueTest {
    @Test
    public void set() {
        /**
         * 大到小
         */
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[0] > y[0] ? x[0] - y[0] : x[1] - y[1]);
        queue.add(new int[]{4, 4});
        queue.add(new int[]{2, 4});
        queue.add(new int[]{1, 4});
        for (int[] ints : queue) {
            for (int anInt : ints) {
                System.out.print(anInt + "-");
            }
            System.out.println();
        }
        //4-4-
        //2-4-
        //1-4-
        System.out.println("--------");
        /**
         * 优先第一个，大到小
         * 在第二个，大到小
         */
        PriorityQueue<int[]> queue1 = new PriorityQueue<>((x, y) -> x[0] < y[0] ? y[0] - x[0] : x[1] - y[1]);
        queue1.add(new int[]{1, 4});
        queue1.add(new int[]{3, 7});
        queue1.add(new int[]{3, 9});
        queue1.add(new int[]{9, 1});
        for (int[] ints : queue1) {
            for (int anInt : ints) {
                System.out.print(anInt + "-");
            }
            System.out.println();
        }
        //9-1-
        //1-4-
        //3-9-
        //3-7-
        System.out.println("--------");
        /**
         * 按第一个：小到大
         */
        PriorityQueue<int[]> queue2 = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        queue2.add(new int[]{3, 9});
        queue2.add(new int[]{1, 4});
        queue2.add(new int[]{3, 7});
        queue2.add(new int[]{9, 1});
        for (int[] ints : queue2) {
            for (int anInt : ints) {
                System.out.print(anInt + "-");
            }
            System.out.println();
        }
        //1-4-
        //3-9-
        //3-7-
        //9-1-
        System.out.println("--------");
        /**
         * 按第一个：小到大
         */
        PriorityQueue<int[]> queue3 = new PriorityQueue<>((x, y) -> x[0] > y[0] ? x[0] - y[0] : y[1] - x[1]);
        queue3.add(new int[]{3, 9});
        queue3.add(new int[]{1, 4});
        queue3.add(new int[]{3, 7});
        queue3.add(new int[]{9, 7});
        queue3.add(new int[]{9, 1});
        for (int[] ints : queue3) {
            for (int anInt : ints) {
                System.out.print(anInt + "-");
            }
            System.out.println();
        }
        //1-4-
        //3-9-
        //3-7-
        //9-1-
    }

    @Test
    public void Stack() {
        Stack<Integer> stk = new Stack<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(1,5,4,6));
        stk.addAll(list);
        System.out.println(stk.peek());
        System.out.println(stk.firstElement());
        stk.removeElement(0);
    }
}
