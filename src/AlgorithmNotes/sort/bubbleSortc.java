package AlgorithmNotes.sort;

import java.util.Arrays;
import java.util.Random;

public class bubbleSortc {

    public static void main(String[] args) {
        Random random = new Random(5);
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(a));
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
        //[7, 2, 4, 4, 6, 5, 4, 1, 2, 1]
        //[1, 1, 2, 2, 4, 4, 4, 5, 6, 7]s
    }

    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                }
            }
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}
