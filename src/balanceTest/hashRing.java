package balanceTest;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class hashRing {

    /**
     * 哈希取模缓存命中率计算 <p>
     * <a href="https://blog.csdn.net/gonghaiyu/article/details/108375298">参考链接</a>
     */
    @Test
    public void test_hash_v1() {
        int[] array = IntStream.range(0, 12).toArray();
        int mod0 = 3;
        int mod1 = 4;
        Map<Integer, List<Integer>> map0 = new HashMap<>();
        Map<Integer, List<Integer>> map1 = new HashMap<>();
        for (int item : array) {
            int id0 = item % mod0;
            int id1 = item % mod1;
            System.out.println("mod=" + mod0 + ", item=" + item + ", " + item % mod0);
            System.out.println("mod=" + mod1 + ", item=" + item + ", " + item % mod1);
            if (map0.containsKey(id0)) {
                map0.get(id0).add(item);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(item);
                map0.put(id0, list);
            }

            if (map1.containsKey(id1)) {
                map1.get(id1).add(item);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(item);
                map1.put(id1, list);
            }
        }

        System.out.println(map0);
        //{0=[0, 3, 6, 9], 1=[1, 4, 7, 10], 2=[2, 5, 8, 11]}
        System.out.println("--------------------------");
        System.out.println(map1);
        //{0=[0, 4, 8], 1=[1, 5, 9], 2=[2, 6, 10], 3=[3, 7, 11]}
        //只有0, 1, 2在原本的id的集合中, 缓存失效率: 75%

    }

    /**
     * 加上ring, 缓存失效率: 0 ~ 33%
     */

    public double hash2idx(int item) {
        return 1.0 * item % 3.0; // 暂时以这样为准
    }

    @Test
    public void test_hashRing() {
        int[] array = IntStream.range(0, 12).toArray();
        int mod0 = 3;
//        int mod1 = 4;
        Map<Integer, List<Integer>> ring = new HashMap<>(mod0); // 3 个节点
        for (int i = 0; i < 3; i++) {
            List<Integer> list = new ArrayList<>();
            ring.put(i, list);
        }

        for (int item : array) {
            // item在ring中1,2之间，选择2
            int idx = (int) Math.ceil(hash2idx(item));
//            System.out.println(idx);
            ring.get(idx).add(item);
        }


        System.out.println(ring);
        //{0=[0, 3, 6, 9], 1=[1, 4, 7, 10], 2=[2, 5, 8, 11]}
        List<Integer> extend_list = new ArrayList<>();
        ring.values().forEach(List::clear);
        ring.put(3, extend_list); // 扩展3节点
        System.out.println(ring);
        for (int item : array) {
            // item在ring中1,2之间，选择2
            int idx = (int) Math.ceil(hash2idx(item));
//            System.out.println(idx);
            ring.get(idx).add(item);
        }
        System.out.println(ring);

    }

    @Test
    public void weight_Random() {
        Map<String, AtomicInteger> map = new TreeMap<>();
        map.put("20881", new AtomicInteger(1));
        map.put("20882", new AtomicInteger(2));
        map.put("20883", new AtomicInteger(3));

        int maxWeight = map.values().stream().mapToInt(AtomicInteger::get).max().getAsInt();
        int currSequence = 0;
        int weightSum = map.values().stream().mapToInt(AtomicInteger::get).sum();
//        System.out.println("weightSum = " + weightSum); // 6
//        System.out.println("maxWeight = " + maxWeight); // 3
        int mod= currSequence % weightSum;
        //1axWeight*map.size()个数,一定是大于mod的值，所以可以保证mod
        for (int i = 0; i < maxWeight; i++) {
            for (Map.Entry<String, AtomicInteger> entry : map.entrySet()) {
                String port = entry.getKey();
                AtomicInteger w = entry.getValue();
                if (mod == 0 && w.get() > 0) {
                    System.out.println("chose:" + port);
                    return ;
                }
                if (w.get() > 0) {
                    w.decrementAndGet();
                    mod --;
                }
            }
        }

    }

    @Test
    public void test_w_R(){
        for (int i = 0; i < 8; i++) {
            weight_Random();

        }
    }

}
