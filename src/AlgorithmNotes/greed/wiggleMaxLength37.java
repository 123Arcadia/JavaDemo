package AlgorithmNotes.greed;

import org.junit.Test;

public class wiggleMaxLength37 {

    /**
     * 376. 摆动序列s
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     */
    //只需要统计该序列中「峰」与「谷」的数量即可

    @Test
    public void test() {
        int x = 1, y = 7;
        System.out.println(Integer.compare(x, y)); // -1
    }

    public int wiggleMaxLength(int[] nums) {
//        int n = nums.length;
//        if (n < 2) {
//            return n;
//        } // 如果n = 2
//        int prediff = nums[1] - nums[0];
//        int res = (prediff != 0) ? 2 : 1;
//        ///实际就是把所有的过度元素看成它左右的相同值，然后遍历时跳过(因为diff = 0跳过)
//        for (int i = 2; i < n; i++) {
//            int diff = nums[i] - nums[i-1];
//            if (diff > 0 && prediff <= 0 || diff < 0 && prediff >= 0) {
//                res++;
//                prediff = diff;
//            }
//
//        }
//        return res;
        int n = nums.length;
        if (n == 1) return 1;
        int pre = 0, res = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            else if (nums[i] > nums[i-1]) {
                if (pre > 0) {
                    continue;
                }
                pre = 1;
                res ++;
            } else {
                if (pre == 0) {
                    continue;
                }
                pre = 0;
                res ++;
            }
        }
        return res+1;
    }

}
