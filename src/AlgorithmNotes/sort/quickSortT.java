package AlgorithmNotes.sort;

import java.util.Arrays;
import java.util.Random;

public class quickSortT {

    public static void main(String[] args) {
        Random random = new Random(5);
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(a));
//        quickSort(a, 0, a.length - 1);
        quicktwo(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        //[7, 2, 4, 4, 6, 5, 4, 1, 2, 1]
        //[1, 1, 2, 2, 4, 4, 4, 5, 6, 7]s
    }

    /**
     * 在使用分治法的过程中以基准元素为中心把其他元素移到它的两边
     * 快速排序的平均时间复杂度为O(nlogn)，但最坏情况下也可以达到O(n2)
     *
     * @param nums
     * @param l
     * @param r
     */
    private static void quickSort(int[] nums, int l, int r) {
        int n = nums.length;
        if (l >= r) return;
        int i = l - 1, j = r + 1;
        int val = nums[(l + r) >> 1];
        while (i < j) {
            do i++; while (nums[i] < val);
            do j--; while (nums[j] > val);
            if (i < j) {
                swap(nums, i, j);
            }
        }
        quickSort(nums, l, j);
        quickSort(nums, j + 1, r);
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }


    public static void quicktwo(int[] nums, int l, int r) {
        if (l < r) {
            int pivotpos = parition(nums, l, r);

            quicktwo(nums, l, pivotpos - 1);
            quicktwo(nums, pivotpos + 1, r);
        }
    }

    private static int parition(int[] nums, int l, int r) {
        int pivot = nums[l];
        while (l < r) {
            while (l < r && nums[r] >= pivot) r--;
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }


}
