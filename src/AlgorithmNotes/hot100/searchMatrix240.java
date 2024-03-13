package AlgorithmNotes.hot100;

public class searchMatrix240 {

    public boolean searchMatrix(int[][] matrix, int target) {
//        for (int[] row : matrix) {
//            int idx = findTarget(row, target);
//            if (idx >= 0) return true;
//        }
//        return false;

        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n-1;
        // 从右上角箱左下角遍历
        while (x < m &&y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }

    private int findTarget(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n-1;
        while (l <= r) {
            int mid = (l +r) >> 1;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
