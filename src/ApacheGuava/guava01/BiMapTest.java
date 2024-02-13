package ApacheGuava.guava01;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * BiMap (˫��ӳ��)����һ�ֱ�����ֵ�ͼ���Ψһ�Ե�ӳ�䣬��Լ��ʹ BiMap ֧�� "������ͼ"��
 * ���õ�ʵ���� {@link HashBiMap}
 * HashBiMap ��������ϣ��֧�֣�����ֵ����Ϊ null������ע�⣺key ����Ψһ��value Ҳ����Ψһ���൱�� key �� value ������ Set �洢һ����
 *
 * @author wangMaoXiong
 * @version 1.0
 * @date 2020/7/23 15:12
 */
public class BiMapTest {
    /**
     * 1��V put(@Nullable K key, @Nullable V value)
     * key �ظ�ʱ���Ḳ�Ǿ�ֵ���������ֵ�Ѱ󶨵��� biMap �е������������׳� IllegalArgumentException��
     * Ҫ������쳣�����Ϊ���� {@link#forcePut}��
     *
     * 2��BiMap<V, K> inverse()
     * ���ش� BiMap �ķ�����ͼ��key �� value ����
     */
    @Test
    public void HashBiMap1() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("����", "2268461752@qq.com");
        biMap.put("����", "7858461752@qq.com");
        //{����=2268461752@qq.com, ����=7858461752@qq.com}
        System.out.println(biMap);

        String s = biMap.get("����");
        System.out.println("����->" + s);
        //����->7858461752@qq.com

        // K - V: ӳ�䵹ת
        BiMap<String, String> inverseBiMap = biMap.inverse();
        //{2268461752@qq.com=����, 7858461752@qq.com=����}
        System.out.println(inverseBiMap);
        //{����=2268461752@qq.com, ����=7858461752@qq.com}
        System.out.println(biMap);
    }

    /**
     * 3. forcePut(@Nullable K key, @Nullable V value)
     * put����һ����ʽ���ڼ��� put ����֮ǰ�����ᾲĬ��ɾ��ֵΪ value ���κ�������Ŀ
     * 4. void putAll(Map<? extends K, ? extends V> map)
     *      (1) putAll�д��� ͬKey��ͬvalue ʱ���׳��쳣java.lang.IllegalArgumentException: value already present:
     */
    @Test
    public void HashBiMap2() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("����", "2268461752@qq.com");
        biMap.put("����", "7858461752@qq.com");
        biMap.forcePut("����", "2268461752@qq.com");
        //{����=7858461752@qq.com, ����=2268461752@qq.com}
        System.out.println(biMap);

        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("����", "12437484674567@163.com");
        hashMap.put("���", "986768799989@163.com");

        biMap.putAll(hashMap);
        //{����=7858461752@qq.com, ����=2268461752@qq.com, ����=12437484674567@163.com, ���=986768799989@163.com}
        System.out.println("biMap:putAll:" + biMap);

        Set<String> values = biMap.values();
        //[7858461752@qq.com, 2268461752@qq.com, 12437484674567@163.com, 986768799989@163.com]
        System.out.println("values = " + values);
    }
}