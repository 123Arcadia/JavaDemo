package Collection.MapTest;

import org.junit.Test;

import java.util.*;

public class MapSort {

    @Test
    public void test01() {
        Map map = new HashMap();

        map.put("zcw", 2);
        map.put("zcw01", 3);
        //map.put(4, "zcw01"); //无效
        map.put("zcw03", 4);
        map.put("zcw03", 66);
        map.put("zcw04", 5);
        map.put("zcw05", 6);
        map.put("zcw06", 7);
        if (map.containsKey("zcw05")) {
            map.put(null, 66);
        }
        System.out.println("map111 = " + map);
        System.out.println(" 03 = " + map.get(3));
        Object i = map.remove("zcw03");
        System.out.println("i = " + i);
        System.out.println("map = " + map);

        //key
        Set set = map.keySet();
        System.out.println("set = " + set);

        //values
        Collection col = map.values();
        System.out.println("col = " + col);

        map.remove(4);
//        for (int i = 0; i < map.size(); i++) {
//            map.remove(i);
//        }
        System.out.println("map01 = " + map);

        /**
         * entry:表示映射关系
         */
        //key - values
//        Set keySet = map.keySet();
//        Iterator iterator = keySet.iterator();
//        while (iterator.hasNext()) {
//            Object key = iterator.next();
//            Object value = map.get(key);
//            System.out.println("key + \"->\" + value = " + key + "->" + value);
//        }

/*
        Set entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
*/

//        Set<Map.Entry<String, Integer>> entry = map.entrySet();
//        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Integer> e = iterator.next();
//            String key = e.getKey();
//            Integer value = e.getValue();
//            System.out.println(key + "-> " + value);
//        }

//        map.clear();
//        System.out.println("map = " + map);
//        System.out.println("map = " + map.size());

//        Hashtable hashtable = new Hashtable();
//        hashtable.put(null, null);
//        System.out.println("hashtable = " + hashtable);
    }

    @Test
    public void test_Entry_sort() {
        Map<Integer , Integer> map = new TreeMap<>(new Comparator<Integer>() {
            /**
             * 倒序排列
             * @param o1 the first object to be compared.
             * @param o2 the second object to be compared.
             * @return
             */
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });

        map.put(2, 2);
        map.put( 3, 3);
        //map.puw01"); //无效
        map.put( 4,4);
        map.put( 66,1);
        map.put( 5,5);
        map.put( 6,6);
        map.put( 7,7);

        Set entrySet = map.entrySet();
        List<Integer> list = new ArrayList<>(entrySet);
        System.out.println("list = " + list);
        System.out.println(list.get(0));
        //list = [66=1, 7=7, 6=6, 5=5, 4=4, 3=3, 2=2]
        //66=1
    }

    /**
     * TreeMap: 以value值排序
     */
    @Test
    public void sortMapReversebyValue() {
        SortedMap<String,String> sortedMap = new TreeMap<String,String>();
        sortedMap.put("1", "a");
        sortedMap.put("5", "b");
        sortedMap.put("2", "c");
        sortedMap.put("4", "d");
        sortedMap.put("3", "e");
        System.out.println("(sort before)sortedMap = " + sortedMap); // sortedMap还是不变

        //sortedMap:1->a
        //sortedMap:2->c
        //sortedMap:3->e
        //sortedMap:4->d
        //sortedMap:5->b

        Set<Map.Entry<String, String>> entries = sortedMap.entrySet();
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 升序
                // o1在前，o2在后
                // -1 -> <
//                return o1.getValue().compareTo(o2.getValue());
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println("(sort after)sortedMap = " + sortedMap); // sortedMap还是不变
        //sortedMap = {1=a, 2=c, 3=e, 4=d, 5=b}
        System.out.println("(sort after)list = " + list); // 但是list排序变了
        //list = [1=a, 5=b, 2=c, 4=d, 3=e]
    }
    @Test
    public void treeMapReversebyValue() {
        Map<String,String> treeMpa = new TreeMap<String,String>();
        treeMpa.put("1", "a");
        treeMpa.put("5", "b");
        treeMpa.put("2", "c");
        treeMpa.put("4", "d");
        treeMpa.put("3", "e");
        System.out.println("(sort before)treeMpa = " + treeMpa); // sortedMap还是不变
//        (sort before)treeMpa = {1=a, 2=c, 3=e, 4=d, 5=b}

        Set<Map.Entry<String, String>> entries = treeMpa.entrySet();
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                // 升序
                // o1在前，o2在后
                // -1 -> <
//                return o1.getValue().compareTo(o2.getValue());
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println("(sort after)treeMpa = " + treeMpa); // sortedMap还是不变
        //(sort after)treeMpa = {1=a, 2=c, 3=e, 4=d, 5=b}
        System.out.println("(sort after)list = " + list); // 但是list排序变了
        //(sort after)list = [1=a, 5=b, 2=c, 4=d, 3=e]
    }
}
