package AlgorithmNotes.sort;

import java.util.Arrays;
import java.util.Random;

public class SelectionSort {
    public static void main(String[] args) {
        Random random = new Random(5);
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(a));
        selectionSort(a);
        System.out.println(Arrays.toString(a));
        //[7, 2, 4, 4, 6, 5, 4, 1, 2, 1]
        //[1, 1, 2, 2, 4, 4, 4, 5, 6, 7]
    }


    /**
     * 每次把他后面的最小元素交换到前面
     * 稳定性：不稳定
     * 时间复杂度：最佳：O(n2) ，最差：O(n2)， 平均：O(n2)
     * 空间复杂度：O(1)
     * 排序方式：In-place
     *
     * @param nums
     */
    public static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(nums, i, min);
            }
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}