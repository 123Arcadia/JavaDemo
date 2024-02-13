package ApacheGuava.guava01;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import org.junit.Test;

/**
 * {@link Multiset} �ӿڼ̳��� {@link Collection} �ӿ�
 * 1��Multiset �൱�� {@link Set}���������� Multiset �������ͬ��Ԫ�أ������ڲ�ʹ��һ�� HashMap ��ά��
 * 2��ͬ�� Multiset Ҳ���Լ���ʵ���ࣺ{@link HashMultiset}��{@link LinkedHashMultiset}��{@link TreeMultiset} ��
 * 3��HashMultiset ��TreeMultiset ������ģ�LinkedHashMultiset ������ģ�������ȫͬ�� HashSet��TreeSet��LinkedHashSet
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 8:54
 */
public class MultisetTest {
    /**
     * HashMultiset ��TreeMultiset ������ģ�LinkedHashMultiset �������
     */
    @Test
    public void hashMultiset() {
        Multiset<Object> hashMultiset = HashMultiset.create();
        hashMultiset.add("a");
        hashMultiset.add(200);
        hashMultiset.add("a");
        hashMultiset.add("��");
        //[a x 2, 200, ��]
        System.out.println(hashMultiset);
        //a a 200 ��
        hashMultiset.stream().forEach(item -> System.out.print(item+" "));

        hashMultiset.remove("a");
        //remove����������1
        System.out.println("\nhashMultiset = " + hashMultiset);
        //hashMultiset = [a, 200, ��]
    }

    /**
     * HashMultiset ��TreeMultiset ������ģ�LinkedHashMultiset �������
     */
    @Test
    public void linkedHashMultiset() {
        LinkedHashMultiset<Object> linkedHashMultiset = LinkedHashMultiset.create();
        linkedHashMultiset.add("�й�");
        linkedHashMultiset.add("����");
        linkedHashMultiset.add("��");
        linkedHashMultiset.add("����");
        linkedHashMultiset.add("����");

        //[�й�, ���� x 2, ��, ����]
        System.out.println(linkedHashMultiset);
        //�й� ���� ���� �� ����
        linkedHashMultiset.stream().forEach(item -> System.out.print(item+" "));

    }

    /**
     * HashMultiset ��TreeMultiset ������ģ�LinkedHashMultiset �������
     */
    @Test
    public void treeMultiset() {
        TreeMultiset<Comparable> treeMultiset = TreeMultiset.create();
        treeMultiset.add("�й�");
        treeMultiset.add("��");
        treeMultiset.add("��");
        treeMultiset.add("��");
        treeMultiset.add("��");

        //[�й�, ��, ��, �� x 2]
        System.out.println(treeMultiset);
        //�й� �� �� �� ��
        treeMultiset.stream().forEach(item -> System.out.print(item+" "));
    }


}
