package AlgorithmNotes.sort;

import org.junit.Test;

import java.util.Arrays;

public class binSearch {


    @Test
    public void leftAndRight_test() {
        int[] nums = new int[]{1, 3, 5, 6};
        int val = 3;
        boolean lower = true;
        int res = leftAndRight(nums, val, lower);
        System.out.println("res = " + res);
//        res = 1
        /**
         * 针对函数重复元素 -------> 索引不确定
         */
        int[] nums1 = new int[]{1, 3, 3, 3, 5, 6};
        int res1 = leftAndRight(nums1, val, true);
        System.out.println("res1 = " + res1);
//        res1 = 2
    }

    /**
     * 针对：不重复元素的数组
     * [左闭右闭] 等于val的值的索引
     */
    private int leftAndRight(int[] nums, int val, boolean lower) {
        int n = nums.length;
        ;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] < val) {
                l = mid + 1;
            } else if (nums[mid] > val) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return r;
    }

    /**
     * [左闭右开)
     */
    private int leftAndExcRight(int[] nums, int val, boolean lower) {
        int n = nums.length;
        int l = 0, r = n;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] < val) {
                l = mid + 1;
            } else if (nums[mid] > val) {
                r = mid;
            } else {
                return mid;
            }
        }
        return r;
    }

    @Test
    public void leftAndExcRight_test() {
        int[] nums = new int[]{1, 3, 46, 12, 5, 6, 9, 2};
        Arrays.sort(nums);
        int val = 3;
        boolean lower = true;
        int res = leftAndRight(nums, val, lower);
        System.out.println("res = " + res);

    }

    @Test
    public void mid_test() {
        /**
         * 对偶数个数
         */
        // 0 1 2 3 4 5
        int l = 0, r = 5;
        int mid1 = (l + r) >> 1;
        int mid2 = l + ((r - l) >> 1);
        System.out.println(mid1 + ", " + mid2); // 2, 2

        /**
         * 对奇数个数
         */
        // 0 1 2 3 4
        int ll = 0, rr = 4;
        int mid11 = (ll + rr) >> 1;
        int mid22 = ll + ((rr - ll) >> 1);
        System.out.println(mid11 + ", " + mid11); // 2, 2
    }

    /**
     * 首个大于目标值的位置
     * [左闭右开)
     */
    private int leftAndExcRight_geVal(int[] nums, int val, boolean lower) {
        int n = nums.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > val) { // 找到一个大于target的值，暂存并在左半区间继续查找
                r = mid;
            } else { // 其他情况继续
                l = mid + 1;
            }
        }
        return r == n ? -1 : r;
    }

    @Test
    public void leftAndExcRight_geVal_test() {
        int[] nums = new int[]{1, 3, 46, 12, 5, 6, 9, 2};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int val = 3;
        boolean lower = true;
        int res = leftAndExcRight_geVal(nums, val, lower);
        System.out.println("res = " + res);
    }


    /**
     * 首个大于等于目标值的位置
     */
    public int leftAndRight_geeqVal(int[] nums, int val) {
        int n = nums.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= val) {
                r = mid; // 遇见重复元素会一直 r-- 第一个元素所在
            } else {
                l = mid + 1;
            }
        }
        return r == n ? -1 : r;
    }

    @Test
    public void leftAndRight_geeqVal_test() {
        int[] nums = new int[]{1, 2, 3, 3, 3, 5, 6, 9, 12, 46};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int val = 3;
        boolean lower = true;
        int res = leftAndRight_geeqVal(nums, val);
        System.out.println("res = " + res);
    }

    /**
     * 最后小于目标值的位置
     * [左闭右开)
     */
    public int leftAndRight_lastleVal(int[] nums, int val) {
        int n = nums.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] < val) {
                l = mid + 1;// 找到一个小于target的值，暂存并在右半区间继续查找更大的小于target的值
            } else {
                r = mid;
            }
        }
        return l - 1;
    }

    /**
     * 最后小于等于目标值的位置
     * [左闭右开)
     */
    public int leftAndRight_lastleeqVal(int[] nums, int val) {
        int n = nums.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] <= val) {
                l = mid + 1;// 找到一个小于target的值，暂存并在右半区间继续查找更大的小于target的值
            } else {
                r = mid;
            }
        }
        return l - 1;
    }




}
