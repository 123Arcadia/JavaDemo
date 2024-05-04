package AlgorithmNotes.sort;

import java.util.Arrays;
import java.util.Random;

public class mergeSort {

    public static void main(String[] args) {
        Random random = new Random(5);
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(a));

        System.out.println(Arrays.toString(MergeSort(a)));
        //[7, 2, 4, 4, 6, 5, 4, 1, 2, 1]
        //[1, 1, 2, 2, 4, 4, 4, 5, 6, 7]
        //
    }

    public static int[] MergeSort(int[] nums) {
        int mid = nums.length / 2;
        if (mid < 1) return nums;

        int[] left = MergeSort(Arrays.copyOfRange(nums, 0, mid));
        int[] right = MergeSort(Arrays.copyOfRange(nums, mid, nums.length));
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] box = new int[left.length + right.length];
        // left/right数组的头指针
        int leftPointer = 0, rightPointer = 0;
        // box的指针
        int boxPointer = 0;

        while (boxPointer < box.length) {
            // left数组已经排序完成
            if (leftPointer >= left.length) {
                while (rightPointer < right.length) {
                    box[boxPointer++] = right[rightPointer++];
                }
                break;
            }
            // right数组 排序完成
            if (rightPointer >= right.length) {
                while (leftPointer < left.length) {
                    box[boxPointer++] = left[leftPointer++];
                }
                break;
            }
            if (left[leftPointer] <= right[rightPointer]) {
                box[boxPointer++] = left[leftPointer++];
            } else {
                box[boxPointer++] = right[rightPointer++];
            }
        }
        return box;
    }


    private void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}
