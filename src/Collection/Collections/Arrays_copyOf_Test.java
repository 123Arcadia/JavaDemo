package Collection.Collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Arrays.copyOf 本质就是 System.arraycopy[cv
 * dzcxzcx
 */
public class Arrays_copyOf_Test {
    @Test
    public void test01() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        /**n
         * 使用asList就不能改这个LIst了
         */
        List dest = Arrays.asList(new Object[list.size()]);
        Collections.copy(dest, list);
        System.out.println("dest = " + dest);
        //dest = [1, 2, 3, 4, 5]

        int[] a = {1, 2, 3, 4 ,5};
        /**
         * Arrays.copyOf本质就是System.arraycopy
         */
        int[] b = Arrays.copyOf(a, 4);
        System.out.println("b = " + Arrays.toString(b));
        //b = [1, 2, 3, 4]
        int[] c = new int[a.length];
        System.arraycopy(a, 0, c, 0, c.length);
        System.out.println("c = " + c); // c = [I@23223dd8
        for (int i : c) {
            System.out.print(i + " ");
        } // 1 2 3 4 5
        System.out.println();
    }
}
