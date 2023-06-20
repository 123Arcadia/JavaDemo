package Testpro.Comparetor;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MyComparetor {
    @Test
    public void test_comparetor() {
        /**
         * 降序
         */
        String[] arr = new String[]{"AA", "GG", "EE", "FF", "DD", "CC", "BB"};
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    return -o1.compareTo(o2);
                }
                throw new RuntimeException("数据类型error!");
            }
        });
        System.out.println("arr = " + Arrays.toString(arr));
        // arr = [GG, FF, EE, DD, CC, BB, AA]

    }

    @Test
    public void test_compareTo() {
        String a = "A";
        String b = "B";
        int c=  a.compareTo(b);
        System.out.println("c = " + c); // c = -1
    }


}
