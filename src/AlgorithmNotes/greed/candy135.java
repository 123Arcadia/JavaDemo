package AlgorithmNotes.greed;

import java.util.Arrays;

public class candy135 {
    public int candy(int[] ratings) {
        // 排序
        int[] l = new int[ratings.length];
        int[] r = new int[ratings.length];
        Arrays.fill(l, 1);
        Arrays.fill(r, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) { // 只对第一次大于的值+1，相等不管
                l[i] = l[i-1] + 1;
            }
        }
        int count = l[ratings.length-1];
        for (int i = ratings.length-2 ;i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                r[i] = r[i+1] + 1;
            }
            count += Math.max(l[i], r[i]);
        }
        return count;
        // 102 -> 0 1 2

        // 0 1 1 -> 1 2 1  = 4
        // left = [1,2,1]
        // right = [ 1, 1,1]

        // 1 0 2 -> 2 1 2
        // l = [1,1,2]
        // r = [1,2,1]
    }

    private int getIdx(int[] ratings, int val) {
        int[] arr = new int[ratings.length];
        System.arraycopy(ratings, 0, arr, 0, ratings.length);
        Arrays.sort(arr);
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] == val) {
                return i;
            }
        }
        return -1;
    }

    private int getMaxVal(int[] ratings) {
        int max= 0;
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] > max) {
                max = ratings[i];
            }
        }
        return max;
    }
}
