package ApacheGuava.guava01;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * {@link Multimap} һ�� key ��Ӧ��� value.
 * 1��JDK �ṩ���� Map ��һ������һ��ֵ��һ��һ�ģ���ʵ�ʿ����У���������һ�� KEY ��� VALUE �����������һ�������µ��鱾����ͨ��ʹ�� Map<k,List<v>>
 * 2��Multimap �ṩһ�� key �Զ��ֵ.
 * 3��Multimap ��ʵ�����У�ArrayListMultimap��HashMultimap��LinkedHashMultimap��TreeMultimap��ImmutableMultimap......
 * 4���������� jdk �Դ��� map ����һ����
 */
public class MultimapTest {

    /**
     * ArrayListMultimap ʹ�� ArrayList ���洢��������ֵ������ֵ����Ϊ�գ�ֵ����˳��ġ�
     * 1��guava ���еļ��϶��� create ��̬�������ڴ���ʵ��
     * 2��boolean put(@Nullable K key, @Nullable V value)����Ӽ�ֵ�ԣ�key �����ظ�
     * 3��Collection<V> get(@Nullable K key)����ȡָ�� key ��ֵ����Ϊһ�Զ࣬���Է��ص��Ǽ��ϣ�key ������ʱ�����ؿռ��ϡ�
     * 4��boolean remove(Object key, Object value)��
     * ���������ӳ�����Ƴ�һ������ֵ�ĵ�����ֵ�ԣ�������ڣ����������ӳ���еĶ����ֵ�Է��ϴ���������ɾ����һ��
     * 5��Collection<V> removeAll(Object key): ������ɾ����ֵ������Ϊ�գ���ɾ����� key ����������ֵ��
     */
    @Test
    public void ArrayListMultimap1() {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("name", "³Ѹ");
        multimap.put("name", "������");
        multimap.put("name", "³Ѹ");
        multimap.put("age", "45");
        //{name=[³Ѹ, ������, ³Ѹ], age=[45]}
        System.out.println(multimap);

        Collection<String> name = multimap.get("name");
        Collection<String> age = multimap.get("age");
        //[³Ѹ, ������, ³Ѹ]
        System.out.println(name);
        //[45]
        System.out.println(age);

        multimap.remove("name", "³Ѹ");
        multimap.removeAll("age");
        //{name=[������, ³Ѹ]
        System.out.println(multimap);
    }

    /**
     * HashMultimap
     */
    @Test
    public void hashMultimap1() {
        HashMultimap<String, Object> hashMultimap = HashMultimap.create();
        hashMultimap.put("id", 2003);
        hashMultimap.put("name", "����");
        hashMultimap.put("name", "����");
        //{name=[����, ����], id=[2003]}
        System.out.println(hashMultimap);

        Set<Map.Entry<String, Object>> entries = hashMultimap.entries();
        for (Map.Entry<String, Object> entry : entries) {
//            name = ����
//            name = ����
//            id = 2003
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}