package AlgorithmNotes;


/**
 * 数组中逆序对
 */

public class reverSortPair {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    int mod = 1000000007;
    public int InversePairs (int[] nums) {
        // write code here
        int n = nums.length;
        int[] res=  new int[n];
        return mergeSort(0, n- 1, nums, res);
    }

    /**
     * 归并： nums升序排序
     * @param l
     * @param r
     * @param nums
     * @param temp 辅助数组
     * @return
     */
    public int mergeSort(int l, int r, int[] nums, int[] temp) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) >> 1;
        int res = mergeSort(0, mid, nums, temp) + mergeSort(mid+1, r, nums, temp);
        res %= mod;
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            temp[k] = nums[k];
        }
        for (int k = l;  k <= r ; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j++];
            } else if (j == r + 1 || temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            } else {
                nums[k]= temp[j++];
                res += mid - i + 1;
            }
        }

        return res % mod;
    }
}
