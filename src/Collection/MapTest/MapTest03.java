package Collection.MapTest;

import org.junit.Test;

import java.util.*;

public class MapTest03 {
    @Test
    public void test_Map() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "zcw1");
        map.put(2, "zcw2");
        map.put(3, "zcw3");
        map.put(4, "zcw4");

        Set<Integer> set = map.keySet();
        for (Object o : set) {
            System.out.print(o + " ");
        }

        ArrayList<String> list = new ArrayList<>(map.values());
        System.out.println("list = " + list);

        // 1 2 3 4 
        System.out.println("==========");

        Collection<String> values = map.values();
        for (Object o : values) {
            System.out.print(o + " ");
        }
        // zcw1 zcw2 zcw3 zcw4
        System.out.println("===========");

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        Set entrySet_2 = map.entrySet();

        for (Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o;
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key : " + key + "->" + "value : " + value);
        }
        //key : 1->value : zcw1
        //key : 2->value : zcw2
        //key : 3->value : zcw3
        //key : 4->value : zcw4
    }

    /**
     * 除数：
     *      取余(%)操作中如果 除数 是 2 的幂次则等价于与其除数减一的与(&)操作
     *      （也就是说 hash%length==hash&(length-1)的前提是 length 是 2 的 n 次方；）。
     *      ” 并且 采用二进制位操作 &，相对于%能够提高运算效率，这就解释了 HashMap 的长度为什么是 2 的幂次方。
     * ------
     * 著作权归所有
     * 原文链接：https://javaguide.cn/java/collection/java-collection-questions-02.html
     */
    @Test
    public void test_HasMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "zz");
        map.put(2, "cz");
        map.put(3, "cz");
        int x = 10 % 4; // 4是2**2
        int y = 10 & (4-1);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        //x = 2
        //y = 2
        System.out.println("map = " + map);
        map.remove(4);
        System.out.println("map = " + map);
        //map = {1=zz, 2=cz, 3=cz}
        //map = {1=zz, 2=cz, 3=cz}
    }

    /**
     * ConcurrentHashMap
     */
    @Test
    public void sum() {

        List<Integer> list = new ArrayList<>(Arrays.asList());

    }


    @Test
    public void  test() {

        double dou = Double.MAX_VALUE;
        double v1 = dou / 0.0;
        double v2 = 0.0 / 0.0;
        System.out.println(dou / 0.0);
        System.out.println(-v1);
        //Infinity
        //-Infinity

        System.out.println(v1 > 100);
        System.out.println(v1 == Double.MAX_VALUE); // false
        System.out.println(-v1 > -100);
        //true
        //false
        System.out.println("v2 = " + v2); // v2 = NaN

        ArrayList<Double> res = null;
        System.out.println("res = " + res);
    }



    private int getNum(int n) {
        int v = 0;
        while (n != 0) {
            if ((n & 1) !=0) {
                v++;
            }
            n >>= 1;
        }
        return v;
    }

    @Test
    public void test1() {
//        int num = getNum(4294967293);
        System.out.println(Math.pow(2,31));
//        System.out.println("num = " + num);
//        int n = 4294967293;
        int n = 111;
        String s = String.valueOf(n);
        int v = 0 ;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                v++;
            }
        }
        return ;
    }


}
