package AlgorithmNotes.MonotonousStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class nextGreaterElements503 {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res= new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n*2-1; i++) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i % n]) {
                res[stk.pop()] = nums[i % n];
            }
            stk.push(i % n);
        }
        return res;
    }
}
