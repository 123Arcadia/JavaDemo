package AlgorithmNotes.hot100;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class maxSlidingWindow239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
//        int n = nums.length;
//        int[] res = new int[n-k+1];
//        Deque<Integer> q = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            // 入
//            while (!q.isEmpty() && nums[q.getLast()] <= nums[i]) {
//                q.removeLast(); // 比他小到=的都移除
//            }
//
//            q.addLast(i);
//            // 出: 是最大值
//            if (i - q.getFirst() >= k) {
//                q.removeFirst();
//            }
//            if (i >= k - 1) {
//                res[i-k+1] =nums[q.getFirst()];
//            }
//        }
//
//        return res;

        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> queue= new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (i - queue.peek() >= k) {
                queue.removeFirst();
            }
            if (i >= k - 1) {
                ans[i -k + 1] = nums[queue.getFirst()];
            }
        }
        return ans;

    }


    @Test
    public void test() {
        Deque<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.getFirst() + "," + q.getLast()); //1,3
        System.out.println(q.peek());
        //1
//        System.out.println(q.poll());
        //1
//        System.out.println(q.pop());
        //1
        System.out.println(q);
        //[2, 3]

        System.out.println(q.peekLast());
        //3

        String str = "";
        System.out.println(str.length());
    }
}
