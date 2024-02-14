package Collection.Collections;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class ArraysElementTest {

    /**
     * 获取数组长度
     * 新方法: (来自java.lang.reflect) Array.getLength(array)
     */
    @Test
    public void testLength() {
        int[] array = new int[]{1, 2, 3, 4, 5};
        // 1.
        System.out.println("array.length = " + array.length);
        //array.length = 5
        // 2.
        System.out.println("Array.getLength(strings) = " + Array.getLength(array));
        //Array.getLength(strings) = 3
    }

    /**
     * Arrays.deepToString: 此方法用于将多维数组转换为字符串。
     */
    @Test
    public void testDeepToString() {
        int[][] array = new int[][]{{2, 8, 4, 1}, {9, 2, 0, 3}};
        //deepToString方法可以对多维数组进行打印
        System.out.println(Arrays.deepToString(array));
        //[[2, 8, 4, 1], [9, 2, 0, 3]]
        System.out.println(Arrays.toString(array));
        //[[I@256216b3, [I@2a18f23c]

        String[][][] str = new String[][][]{{{"1","2"},{"3","4"}}};
        System.out.println(Arrays.deepToString(str));
        System.out.println(Arrays.toString(str));
        //[[[1, 2], [3, 4]]]
        //[[[Ljava.lang.String;@16c0663d]
        System.out.println("str.length = " + str.length );
        //str.length = 1
        System.out.println("str[0].length = " + str[0].length );
        //str[0].length = 2
        System.out.println("str[0][0].length = " + str[0][0].length );
        //str[0][0].length = 2
    }

    /**
     * Arrays也为一维数组和多维数组提供了相等判断的方法
     */
    @Test
    public void test_equalsArray() {
        int[][] a = new int[][]{{2, 8, 4, 1}, {9, 2, 0, 3}};
        int[][] b = new int[][]{{2, 8, 4, 1}, {9, 2, 0, 3}};
        System.out.println(Arrays.equals(a, b));   //equals仅适用于一维数组
        System.out.println(Arrays.deepEquals(a, b));   //对于多维数组，需要使用deepEquals来进行深层次判断

        //不是说基本类型的数组不能转换为引用类型的数组吗？为什么这里的deepEquals接受的是Object[]也可以传入参数呢？
        // 这是因为现在是二维数组，二维数组每个元素都是一个数组，而数组本身的话就是一个引用类型了，
        // 所以说可以转换为Object类型，但是如果是一维数组的话，就报错!!!
    }


    /**
     * 判断两个集合是否是不相交的集合
     * Arrays.mismatch: return -1表示两个集合元素相等；否则返回第一个不相等的元素索引
     */
    @Test
    public void arrayIntAnyMatch() {
        // 定义两个集合
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 3, 4, 5, 6};
        // [mismatch]: the index of the first mismatch between the two arrays, otherwise -1.
        int mismatch = Arrays.mismatch(a, b);
        System.out.println("mismatch = " + mismatch); // 0
        System.out.println(91/3); // 30

        String[] c1 = {"1", "2", "3", "4", "5"};
        String[] c2 = {"11", "2", "3", "4", "5"};
        int string = Arrays.mismatch(c1, c2);
        System.out.println("string = " + string); // string = 0

        int[] c = {1, 2, 3, 4, 5};
        int[] d = {1, 2, 3, 4, 5};
        // [mismatch]: the index of the first mismatch between the two arrays, otherwise -1.
        int mismatchEqual = Arrays.mismatch(c,d);
        System.out.println("mismatchEqual = " + mismatchEqual); // -1

    }


    /**
     * Collections.disjoint(set1, set2):
     *      true if the two specified collections have no elements in common.</code>
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
