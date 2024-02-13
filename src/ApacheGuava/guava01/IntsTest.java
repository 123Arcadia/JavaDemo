package ApacheGuava.guava01;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * guava �ṩ�� Bytes/Shorts/Ints/Longs/Floats/Doubles/Chars/Booleans ��Щ�����������͵���չ֧��
 */
public class IntsTest {
    /**
     * List<Integer> asList(int... backingArray��������ָ������֧�ֵĹ̶���С���б������޸ģ����� {@link Arrays#asList(Object[])}
     * int max(int... array): ��ȡ�����е����ֵ������Ϊ�ջ���Ϊnull���������쳣
     * int min(int... array)����ȡ�����е����ֵ������Ϊ�ջ���Ϊnull���������쳣
     * void sortDescending(int[] array)���������{@code array}��Ԫ������array Ϊ null ʱ�׳��쳣.
     * void reverse(int[] array)����ת�����е�Ԫ�أ�array Ϊ null ʱ�׳��쳣.
     */
    @Test
    public void ints1() {

        //�� int ����תΪ Integer �� list��[1, 2, 4, 5, 6]
        List<Integer> integerList = Ints.asList(1, 2, 4, 5, 6);
        System.out.println(integerList);

        int[] ints = new int[]{3, 2, 4, 55, 34, 33, 55, 67};
        //ȡ int �����е����ֵ����Сֵ��67��2
        int max = Ints.max(ints);
        int min = Ints.min(ints);
        System.out.printf("%s��%s%n", max, min);

        //�� ints �е�Ԫ�ؽ������У�[67, 55, 55, 34, 33, 4, 3, 2]
        Ints.sortDescending(ints);
        System.out.println(Ints.asList(ints));

        //�������е�Ԫ����β�ߵ���[67, 55, 33, 34, 55, 4, 2, 3]
        ints = new int[]{3, 2, 4, 55, 66, 34, 33, 55, 67};
        Ints.reverse(ints);
        System.out.println(Ints.asList(ints));
    }

    /**
     * String join(String separator, int... array)
     * 1������һ���ַ��������ַ��������� separator �ָ��� array Ԫ�ص�ֵ
     * 2��separator ֻ������Ԫ�ص��м�������ӣ���������ͷ���β
     * 3��array Ϊ null ʱ���쳣��Ϊ��ʱ������ ""
     */
    @Test
    public void ints2() {
        int[] ints2 = new int[]{3, 2, 4, 55, 34, 33, 55, 67};
        String join = Ints.join("','", ints2);
        join = "('" + join + "')";
        //join=('3','2','4','55','34','33','55','67')
        System.out.println("join=" + join);
    }

    /**
     * int[] toArray(Collection<? extends Number> collection)
     * 1������һ�����飬���а��� collection �е�ÿ��ֵ������ {@link Number#intValue} �ķ�ʽת��Ϊ int ֵ��
     * 2������ Collection.toArray() һ�����̰߳�ȫ��
     * boolean contains(int[] array, int target)
     * 1����� target ��ΪԪ�س����� array �У��򷵻� true��array ����Ϊ�գ����ǲ���Ϊ null
     */
    @Test
    public void ints3() {
        ArrayList<Integer> arrayList = Lists.newArrayList(100, 200, 400);
        int[] array = Ints.toArray(arrayList);
        //true
        System.out.println(Ints.contains(array, 200));
        //[100, 200, 400]
        System.out.println(Ints.asList(array));
    }

    /**
     * int[] concat(int[]... arrays)����˳�򽫶������ϲ���һ���µ�����
     */
    @Test
    public void ints4() {
        int[] array1 = Ints.toArray(Ints.asList(1, 2, 4, 56, 345, 23));
        int[] array2 = Ints.toArray(Ints.asList(21, 32, 14, 56, 645, 53));

        int[] concat = Ints.concat(array1, array2);
        //[1, 2, 4, 56, 345, 23, 21, 32, 14, 56, 645, 53]
        System.out.println(Ints.asList(concat));
    }

    /**
     * int compare(int a, int b)���Ա� a��b�Ĵ�С��a>b����1��a=b����0��a<b����-1
     * int indexOf(int[] array, int target) ����ѯָ��Ԫ�ص�����λ�ã�array ����Ϊ null.
     * int lastIndexOf(int[] array, int target)��
     */
    @Test
    public void ints5() {
        List<Integer> integerList = Arrays.asList(124, 23, 25, 22, 11, 23, 46, 88);
        int[] ints = Ints.toArray(integerList);
        int indexOf1 = Ints.indexOf(ints, 23);
        int indexOf2 = Ints.indexOf(ints, 123);
        //1
        System.out.println(indexOf1);
        //-1
        System.out.println(indexOf2);
        //5
        int lastIndexOf = Ints.lastIndexOf(ints, 23);
        System.out.println(lastIndexOf);

        //-1
        System.out.println(Ints.compare(3, 5));
        //0
        System.out.println(Ints.compare(5, 5));
        //1
        System.out.println(Ints.compare(15, 5));
    }
}