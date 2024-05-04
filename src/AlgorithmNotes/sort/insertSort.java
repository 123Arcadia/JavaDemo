package AlgorithmNotes.sort;

import java.util.Arrays;
import java.util.Random;

public class insertSort {
    public static int[] insertSort(int[] arr) {
        //第一张牌有序，所以从第二张牌开始往前比较
        for (int i = 1; i < arr.length; i++) {
            //把这张牌拿在手里
            int temp = arr[i];
            //    j=i-1是手牌的前一张，只要还没到最左边的牌，就一直比较下去
            int j;
            for (j = i - 1; j >= 0; j--) {
                //如果前面的牌比手牌大，那就把前面的牌往后移
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j]; // 覆盖掉arr[i]
                } else
                    break;
            }
            // 此时nums[j]是左边向前走第一个比他小的数
            // ++j表示后一个
            arr[++j] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        Random random = new Random(5);
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(a));

        System.out.println(Arrays.toString(insertSort(a)));
        //[7, 2, 4, 4, 6, 5, 4, 1, 2, 1]
        //[1, 1, 2, 2, 4, 4, 4, 5, 6, 7]
        //
    }


}
