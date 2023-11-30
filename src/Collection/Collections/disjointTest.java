package Collection.Collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <code>Arrays.mismatch(a, b)</code>: the index of the first mismatch between the two arrays, otherwise -1.
 */
public class disjointTest{

    // 判断两个集合是否是不相交的集合
    @Test
    public void arrayIntAnyMatch() {
        // 定义两个集合
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 3, 4, 5, 6};
        // [mismatch]: the index of the first mismatch between the two arrays, otherwise -1.
        int mismatch = Arrays.mismatch(a, b);
        System.out.println("mismatch = " + mismatch);
    }

    /**
     * <code>Collections.disjoint(set1, set2)</code>: true if the two specified collections have no elements in common.</code>
     */
    @Test
    public void test_disjoint() {
        Set<String> set1 = new HashSet<>();
        set1.add("111");
        set1.add("222");
        set1.add("333");

        Set<String> set2 = new HashSet<>();
        set2.add("444");
        set2.add("555");
        set2.add("666");

        Set<String> set3 = new HashSet<>();
        set3.add("444");
        set3.add("777");
        set3.add("888");
        boolean a = Collections.disjoint(set1, set2);
        System.out.println("set1 与 set2 无相同元素时，Collections.disjoint结果：" + a); // true

        boolean b = Collections.disjoint(set2, set3);
        System.out.println("set2 与 set3 有相同元素时，Collections.disjoint结果：" + b); // false
    }
}
