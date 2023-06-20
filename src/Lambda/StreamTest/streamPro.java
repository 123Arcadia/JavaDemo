package Lambda.StreamTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class streamPro {
    /**
     * 去重 + 集合 转 数组
     */
    @Test
    public void test() {
        int[] nums1 = {1, 2, 2, 1, 4, 9, 5};
        int[] nums2 = {2, 2, 5, 9};
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        List list1 = new ArrayList<>();
        List list2 = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            list1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            list2.add(nums2[i]);
        }
        if (list1.containsAll(list2)) {
            Integer[] integers = (Integer[]) list2.toArray(new Integer[0]);
            res = Arrays.stream(integers).distinct().mapToInt(Integer::valueOf).toArray();
        } else {
            Integer[] integers = (Integer[]) list1.toArray(new Integer[0]);
            res = Arrays.stream(integers).distinct().mapToInt(Integer::valueOf).toArray();
        }
        System.out.println("Arrays.toString(res) = " + Arrays.toString(res));
        //Arrays.toString(res) = [2, 5, 9]
    }
}
