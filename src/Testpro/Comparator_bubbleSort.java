package Testpro;

import java.util.Arrays;
import java.util.Comparator;

public class Comparator_bubbleSort {
    public static void main(String[] args) {
        int a[] = {2,4,5,8,9,1,7,4,6};
        System.out.println(Arrays.toString(a));

//        System.out.println("++++++排序后+++++++");
//        Arrays.sort(a);
//        System.out.println(Arrays.toString(a));
        bubble01(a, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int i1 = (Integer) o1;
                int i2 = (Integer) o2;

                return i1 - i2;
            }
        });
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void bubble01(int[] a, Comparator c) {

        for (int i = 0; i < a.length - 1; ++i)
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (c.compare(a[i], a[j + 1]) > 0) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
    }
}
