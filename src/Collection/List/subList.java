package Collection.List;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class subList {

    /**
     * ArrayList: SubList的get()方法是通过下标来获取原数组的数据，而不是返回一个新的对象，当代码中有对分割后的列表访问时，便是对原ArrayList的引用，导致该对象不会被GC回收，数据量大时，有导致OOM的风险。因此，我们需要找到新的方案去解决代码中的风险点。
     */
    @Test
    public void test(){
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,6,4,3,2,64,3,21,2));

        /**
         * 1. 通过skip()方法获取某个元素节点之后的数据
         * 不会影响原本List数据
         */
        //获取第2个节点后的数据（包含第2个元素）
        List<Integer> skipList = list1.stream().skip(1).collect(Collectors.toList());
        System.out.println("skipList = " + skipList);
        System.out.println("list1 = " + list1);
        //skipList = [6, 4, 3, 2, 64, 3, 21, 2]
        //list1 = [1, 6, 4, 3, 2, 64, 3, 21, 2]
        skipList.set(0, 999);
        System.out.println("skipList = " + skipList);
        System.out.println("list1 = " + list1);
        //skipList = [999, 4, 3, 2, 64, 3, 21, 2]
        //list1 = [1, 6, 4, 3, 2, 64, 3, 21, 2]

        /**
         * 2. 通过limit()方法获取某个元素节点之前的数据
         */
        //获取第2个节点前的数据
        List<Integer> limitList = list1.stream().limit(1).collect(Collectors.toList());
        System.out.println("limitList = " + limitList);

        /**
         * 其他解决方案
         * guava 的 Lists.partition()
         * apache 的 ListUtils.partition()
         * 通过List的构造方法
         */





    }
}
