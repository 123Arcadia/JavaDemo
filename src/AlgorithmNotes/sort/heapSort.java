package AlgorithmNotes.sort;

public class heapSort {


    public int findKthLargest(int[] nums, int k) {
        // 使用PriorityQueue

        // 使用堆排序
//        int headpSize = nums.length;
//        buuildHeap(nums, headpSize);
//
//        for(int i = nums.length-1;  i > nums.length - k; i--) {
//            swap(nums, i, 0); // 最大值和末尾调换
//            headpSize--;
//            maxHeapify(nums, 0, headpSize);
//        }
//        return nums[0];


        // 使用快排
        int l = 0, r = nums.length - 1;
        return quickSort(nums, l, r, nums.length - k);
    }

    private int quickSort(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (l < r) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                swap(nums, i, j);
            }
        }
        if (k <= j) {
            return quickSort(nums, l, j, k);
        }
        return quickSort(nums, j + 1, r, k);
    }

    private void buuildHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    private void maxHeapify(int[] nums, int i, int heapSize) {
        int l = 2 * i, r = i * 2 + 1;
        int longest = i;
        if (l < heapSize && nums[l] > nums[i]) {
            longest = l;
        }
        if (r < heapSize && nums[r] > nums[i]) {
            longest = r;
        }
        if (longest != i) {
            // 交换
            swap(nums, i, longest);
            maxHeapify(nums, longest, heapSize);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}
