package Testpro;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;

public class test {
    @Test
    public void test() {
        int x = 50;
        int cap = 20, num = 0;
        while (x > 0) {
            num++;
            x -= cap;
        }
        System.out.println("num = " + num);

        Map<String, Map<Integer, Integer>> map =  new HashMap<>();
        Map<Integer, Integer> map1=  new HashMap<>();
        Map<Integer, Integer> map2=  new HashMap<>();
        map1.put(1, 3);
        map1.put(3, 4);
        map.put("1", map1);
        map2.put(2, 9);
        map2.put(4, 9);
        map.put("1", map2);

        System.out.println("map1 = " + map1);
        System.out.println("map2 = " + map2);
        System.out.println("map = " + map);
    }

    @Test
    public void test1() {
        List<Integer> list = List.of(1,5,7,10);
        ArrayList<Integer> list1 = new ArrayList<>();
        List<Integer> xList = List.of(2,4,3,6,7,5,6,9);
        for (int i = 0; i < list.size() - 1; i++) {
            for (Integer x : xList) {

                if ((i & 1) != 1 && x >= list.get(i) && x <= list.get(i+1)) {
                    list1.add(x);
                }
            }
        }
        System.out.println("list1 = " + list1);
        list1.addAll(Arrays.asList(88888, 9999));
        System.out.println("list1 = " + list1);
        System.out.println("(1 & 1) = " + (1 & 1));

        Map<String, Integer> map =  new HashMap<>();
        map.put("11", 1);
        map.put("22", 2);

        map.getOrDefault("22", 33);
        System.out.println("map = " + map);
    }

    @Test
    public void forBreak() {
        boolean f = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) {
                    System.out.println("j = " + j);
                    System.out.println("i = " + i);
                    f = true;
                    System.out.println("f = " + f);
                    return ;
                }
            }
        }
        System.out.println("f = " + f);
    }

    @Test
    public void map() {
        Map<String, List<Map<String, Integer>>> map = new HashMap<>();
        List<Map<String, Integer>> list1 = new ArrayList<>();
        List<Map<String, Integer>> list2 = new ArrayList<>();
        Map<String, Integer> map_1 = new HashMap<>();
        Map<String, Integer> map_2 = new HashMap<>();
        map_1.put("zcw1", 1);
        map_1.put("zcw2", 2);
        map_1.put("zcw3", 3);

        map_2.put("zcw_1", 1);
        map_2.put("zcw_2", 2);
        map_2.put("zcw_3", 3);
        list1.add(map_1);
        list2.add(map_2);
        System.out.println("list1 = " + list1);
        map.put("ZCW1", list1);
        map.put("ZCW1", list2);
        System.out.println("map = " + map);

    }

    @Test
    public void map190() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 1);
        map.put("3", 1);
        map.put("2", map.getOrDefault("2", 0) + 1);
        System.out.println("map = " + map);
        System.out.println("(int)0.5 = " + (int)0.5);
    }

    @Test
    public void tt () {
        Map<String, List<String>> list=  new HashMap<>();
    }


    @Test
    public void cntrTest() {
        List<Cntr> conList = new ArrayList<Cntr>();
        conList.add(new Cntr("1", "allo_1"));

        conList.add(new Cntr("1", "allo_2"));
        conList.add(new Cntr("1", "allo_2"));
        conList.add(new Cntr("1", "allo_2"));
        conList.add(new Cntr("1", "allo_5"));
        conList.add(new Cntr("1", "allo_6"));
        conList.add(new Cntr("1", "allo_6"));
        conList.add(new Cntr("1", "allo_6"));
        conList.add(new Cntr("1", "allo_9"));
        System.out.println("conList = " + conList);

//        Map<String, List<String>> map=  new HashMap<>();
//        Map<String, Integer> allo_Num = new HashMap<>();
//        for (Cntr cntr : conList) {
//            String name = cntr.getName();
//            String allo = cntr.allo;
////            if (allo_Num.containsKey(allo)) {
////                allo_Num.put(allo, allo_Num.get(allo) + 1);
////            } else {
////                allo_Num.put(allo, 1);
////            }
//            allo_Num.put(allo, 0);
//        }
//        System.out.println("allo_Num = " + allo_Num);
//
//        for (Cntr cntr : conList) {
//            String name = cntr.getName();
//            String allo = cntr.allo;
//            if (map.containsKey(name)) {
//                List<String> list = map.get(name);
//                if (list.contains(allo)) {
////                    allo_Num.get(allo)
//                }
//            }
//
//        }

//        Map<String, List<Cntr>> map = new HashMap<>();
        Map<String, Map<String, Integer>> map = new HashMap<>();

        Map<String, Integer> alloNum = new HashMap<>();
        for (Cntr cntr : conList) {
//            String name = cntr.getName();
            String allo = cntr.allo;
            if (alloNum.containsKey(allo)) {
                alloNum.put(allo, alloNum.get(allo) + 1);
            } else {
                alloNum.put(allo, 1);
            }


        }
        map.put("1", alloNum);
        System.out.println("map = " + map);

    }
}
@Data
@AllArgsConstructor
class Cntr {
    String name;
    String allo;
}