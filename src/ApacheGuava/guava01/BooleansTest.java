package ApacheGuava.guava01;

import com.google.common.primitives.Booleans;
import org.junit.Test;

import java.util.List;
import java.util.StringJoiner;

public class BooleansTest {



    /**
     * List<Boolean> asList(boolean... backingArray)��������תΪ�б�
     * boolean[] toArray(Collection<Boolean> collection)��������תΪ����
     */
    @Test
    public void test1() {
        List<Boolean> booleanList = Booleans.asList(false, true, true, false);
        //[false, true, true, false]
        System.out.println(booleanList);
        //[false, true, true, false]

        boolean[] booleans = Booleans.toArray(booleanList);
        //false,true
        System.out.println(booleans[0] + "," + booleans[2]);
        //false,true
    }

    /**
     * int compare(boolean a, boolean b)���Ƚϲ���ֵ���ײ�ʹ�� (a == b) ? 0 : (a ? 1 : -1)
     *
     * 0: ���
     */
    @Test
    public void test2() {
        System.out.println(Booleans.compare(true, true));
        //0
        System.out.println(Booleans.compare(true, false));
        //1
        System.out.println(Booleans.compare(false, false));
        //0
        System.out.println(Booleans.compare(false, true));
        //-1
    }

    /**
     * boolean[] concat(boolean[]... arrays) :�����������ϳ�һ��
     * boolean contains(boolean[] array, boolean target) :����������Ƿ����Ŀ��Ԫ��
     */
    @Test
    public void test3() {
        boolean[] booleans1 = new boolean[]{false, false, true, false};
        boolean[] booleans2 = new boolean[]{false, true, true, true};

        boolean[] concat = Booleans.concat(booleans1, booleans2);
        //[false, false, true, false, false, true, true, true]
        System.out.println(Booleans.asList(concat));

        //true
        System.out.println(Booleans.contains(booleans1, true));
    }

    /**
     * int countTrue(boolean... values)��ͳ�������� true �ĸ���
     */
    @Test
    public void test4() {
        boolean[] booleans2 = new boolean[]{false, true, true, true};
        System.out.println("true ����Ϊ��" + Booleans.countTrue(booleans2));
        //3
        System.out.println("false ����Ϊ��" + (booleans2.length - Booleans.countTrue(booleans2)));
        //1
    }

    /**
     * int indexOf(boolean[] array, boolean target)�������е�һ�γ��� target ������
     * int lastIndexOf(boolean[] array, boolean target): ���������һ�γ��� target ������
     */
    @Test
    public void test5() {
        boolean[] booleans2 = new boolean[]{false, true, true, true};
        //1
        System.out.println(Booleans.indexOf(booleans2, true));
        //3
        System.out.println(Booleans.lastIndexOf(booleans2, true));
    }

    /**
     * String join(String separator, boolean... array): ʹ���ַ����������е�Ԫ����������
     * void reverse(boolean[] array) ���������е�Ԫ��λ�ý��з�ת
     * void reverse(boolean[] array, int fromIndex, int toIndex)���������е�ָ����Χ�ڵ�Ԫ��λ�ý��з�ת
     */
    @Test
    public void test6() {
        boolean[] booleans2 = new boolean[]{false, true, true, true};
        // ������StringJoiner����String.join
        String join = "(" + Booleans.join(",", booleans2) + ")";
        System.out.println(join);
        //(false,true,true,true)

        Booleans.reverse(booleans2,0,3);
        System.out.println(Booleans.asList(booleans2));
        //[true, true, false, true]

        StringJoiner boolJoiner = new StringJoiner(",","(",")");
        for (boolean b : booleans2) {
            boolJoiner.add(String.valueOf(b));
        }
        System.out.println("boolJoiner = " + boolJoiner);
        //boolJoiner = (true,true,false,true)
    }
}
