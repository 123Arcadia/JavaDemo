package AlgorithmNotes.greed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class largestSumAfterKNegations1005 {
    /**
     * 1005. K 次取反后最大化的数组和
     */


    public int largestSumAfterKNegations(int[] nums, int k) {
        // 全是正数(是否有0)
//        int n = nums.length, idx = 0;
//        // 负数的优先队列
//        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
//        boolean zero = false;
//        for (int i = 0; i < n; i++) {
//            if (nums[i] < 0) {
//                queue.add(i);
//            }
//            if (nums[i] == 0) {
//                zero = true;
//            }
//            if (Math.abs(nums[i]) < Math.abs(nums[idx])) {
//                idx = i;
//            }
//        }
//        if (k <= queue.size()) {
//            // 把负数从小到大取反，会最终和最大
//            while (k -- > 0) {
//                int t = queue.poll();
//                nums[t] = -nums[t];
//                // nums[queue.peek()] = -nums[queue.poll()];
//            }
//        } else {
//            // 先把负数取反
//            while (!queue.isEmpty() && k -- > 0) {
//                nums[queue.peek()] = -nums[queue.poll()];
//            }
//            // 剩余的k
//            if (!zero && k % 2 != 0) {
//                // 没有0且剩余k是奇数
//                nums[idx] = -nums[idx];
//            } // 其他情况不变
//        }
//        return getSum(nums);

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans= Arrays.stream(nums).sum();
        for (int i = -100; i < 0; i++) {
            if (map.containsKey(i)) {
                int ops= Math.min(k, map.get(i)); // k 和该负数的次数去最小
                ans += (-i) * 2 * ops;
                map.put(-i, map.getOrDefault(-i, 0) + ops);
                map.put(i, map.getOrDefault(i, 0) -ops);
                k -= ops;
                if (k <= 0) {
                    break;
                }
            }
        }
        // 对于其他情况不用管
        if (k > 0 && k % 2 != 0 && !map.containsKey(0)) {
            for (int i = 1; i <= 100; i++) {
                if (map.containsKey(i)) {
                    ans -= i * 2;
                    break;
                }
            }
        }
        return ans;
    }

    private int getSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
