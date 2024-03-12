package AlgorithmNotes.hot100;

public class findKthLargest215 {
    public int findKthLargest(int[] nums, int k) {
//        Arrays.sort(nums);
//        return nums[k-1];

        //1. 基于快拍排
//        int n = nums.length;
//        return quickSort(nums, 0, n - 1, n - k);

        //2. 基于堆排
        // 建堆后nums就是从大到小排序
        int heapSize = nums.length;;
        buildHeadp(nums, heapSize);
        //做 k−1 次删除操作后堆顶元素就是我们要找的答案
        for (int i = nums.length-1; i >= nums.length - (k - 1); i--) {
            // 删除操作： 这里删除是交换0, n-1的数值，然后堆化[0...n-2]的范围即可
            swap(nums,0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private void buildHeadp(int[] nums, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {
            // 自底向上
            //从第一个非叶子节点为根节点的子树开始，将其调整为大根堆
            maxHeapify(nums, i, heapSize);
        }
    }

    private void maxHeapify(int[] nums, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2;
        int largest = i; // 当前根节点
        if (l < largest && nums[l] > nums[largest]) {
            // 当前左叶子值最大
            largest = l;
        }
        if (r < heapSize && nums[r] > nums[largest]) {
            // 当前右叶子值对打
            largest = r;
        }
        if (largest != i) {
            swap(nums, i, largest); // 让最大值成为当前跟节点
            maxHeapify(nums, largest, heapSize); // 在检查一次
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // k 是所求的idx
    private int quickSort(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do {
                i++;
            } while (nums[i] < x);
            do {
                j--;
            }while (nums[j] > x);
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        // 如果k == j ,下一轮会返回nums[k]
        if (k <= j) return quickSort(nums, l, j, k);
        else return quickSort(nums, j +1, r, k);
    }
}
