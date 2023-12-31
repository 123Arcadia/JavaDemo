package Collection.List;

import org.junit.Test;

import java.util.*;

public class ListArrayasList {
    @Test
    public void ArrayasList() {
        List list = new ArrayList<>(Arrays.asList(11, 1, 6, 4, 7, 2, "zzz"));
        System.out.println("list = " + list); // list = [11, 1, 6, 4, 7, 2]

        List<Integer> list1 = Arrays.asList(11, 1, 6, 4, 7, 2);
        System.out.println("list1 = " + list1); // list1 = [11, 1, 6, 4, 7, 2]
        /**
         * 报错, 这里对比D:\javaProject\javaTesting\src\List\ListTest.java
         */
//        list1.add(99);
//        System.out.println("list1[add后] = " + list1);

        List<Integer> list2 = List.of(11, 1, 6, 4, 7, 2);
        System.out.println("list2 = " + list2); // list2 = [11, 1, 6, 4, 7, 2]
        /**
         * 报错:List.of不可修改
         */
//        list2.add(99);
//        System.out.println("list2[add后] = " + list2);
    }

    @Test
    public void break_UPDATE() {
        UPDATE:
        for (int i = 0; i < 10; i++) {
            System.out.print("i->" + i + " ");
            if (i == 10) System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print("j->" + j + " ");
                if (j == 10) System.out.println();
                if (j == 5) {
                    break UPDATE;
                }
            }
        }
    }
    //i->0 j->0 j->1 j->2 j->3 j->4 j->5

    /**
     * 迭代器可以操作List的元素
     */
    @Test
    public void iterator_remove() {
        List list = new ArrayList<>(Arrays.asList(1, 3, 3, 4, 2, 10));
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = (Integer) iterator.next();
            System.out.print(next + " ");
            if (next == 3) iterator.remove();
        }
        System.out.println("list = " + list);
        // 1 3 3 4 2 10
        // list = [1, 4, 2, 10]

    }

    /**
     * 普通for删除测试
     */
    @Test
    public void for_remove() {
        Integer num = 4;
        List list = new ArrayList<>(Arrays.asList(1, 4, 2, 10));
        int size = list.size(); // 4
        for (int i = 0; i < size; i++) {
            if (list.get(i) == num) {
                System.out.println("list[get(i)] = " + list.get(i));
                list.remove(i);
                System.out.println("i = " + i);
                size--;
            }
        }
        /**
         * 为什么要加size--:
         *      因为size初始计算为4，然而当remove发生后list大小变为3(此时在访问索引为3,即未改变前的最后一个元素就会索引超限，异常!!)
         */

        System.out.println("list = " + list);
    }

    @Test
    public void ForEach() {
        List list = new ArrayList<>(Arrays.asList(1, 4, 2, 10));
        list.forEach(o -> System.out.print(o + " "));
        // 1 4 2 10
        System.out.println();
        list.forEach(System.out::println);
        //1
        //4
        //2
        //10

        Map<Integer, String> map = new HashMap<>();
        map.put(101, "Java");
        map.put(102, "Angular");
        map.put(103, "Spring");

        map.forEach((k, v) -> System.out.println(k + "-" + v));
        // 或者
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
        //101-Java
        //102-Angular
        //103-Spring

    }

    @Test
    public void DequeueTest() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(4);
        deque.add(2);
        System.out.println("deque = " + deque); // deque = [1, 4, 2]
        System.out.println("deque.size() = " + deque.size()); // deque = 3
        Integer pop = deque.pop();
        System.out.println("pop = " + pop); // 1
        /**
         * 此时 11 会加在前面，因为 push 适应的是 栈
         */
        deque.push(11);
        System.out.println("deque.getFirst() = " + deque.getFirst()); // 11
        System.out.printf("%-5d\n", deque.getLast()); // 2
        boolean offerFirst = deque.offerFirst(2);
        System.out.println("offerFirst = " + offerFirst); // true
        System.out.println("deque = " + deque); // deque = [2, 11, 4, 2]
    }


    /**
     * List.Of 和 arraysAsList 不可 add
     * ListOf 不可以set 修改
     */
    @Test
    public void test_ListOf_ArraysAsList() {
        List<String> ListOf = List.of("zc1", "zc2");
        List<String> arraysAsList = Arrays.asList("zc1", "zc2");
        // List.Of 和 arraysAsList 不可 add
        // ListOf 不可以set 修改
//        ListOf.add("zc3");    // 异常
//        arraysAsList.add("zc3");  // 异常
//        ListOf.set(1, "zc3");  // 异常
        arraysAsList.set(1, "zc3");
        System.out.println("ListOf:" + ListOf + "\n" + "arraysAsList:" + arraysAsList);
        List<String> list = new ArrayList<>(List.of("zcw ", " zcw "));
        for (String s : list) {
            s = s.trim();
        }

        System.out.println("list = " + list);
    }

    /**
     * ArrayList:subList (修改subList得到的List, 原本List也会改变)
     * fromIndex - 截取元素的起始位置，包含该索引位置元素
     * toIndex - 截取元素的结束位置，不包含该索引位置元素
     */
    @Test
    public void test_subList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 33, 44, 3, 2, 3, 9, 22)); // 9个
        System.out.println(list.subList(0, list.size() / 2));
        System.out.println(list.size() + ", " + list.get(list.size() - 1));
        System.out.println(list.subList(list.size() / 2, list.size() - 1));
        System.out.println(list.subList(list.size() / 2, list.size()));
        //[1, 5, 33, 44]
        //9, 22
        //[3, 2, 3, 9]
        //[3, 2, 3, 9, 22]

//        List<Integer> subList = (ArrayList<Integer>) list.subList(0, 2);
//        System.out.println("subList = " + subList);
//        subList.set(0, 999);
//        System.out.println("subList = " + subList);
//        System.out.println("list = " + list);
        //subList = [1, 5]
        //subList = [999, 5]
        //list = [999, 5, 33, 44, 3, 2, 3, 9, 22]
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 111);
        map.remove(2);

        List<Integer> subList1 = new ArrayList<>();
        subList1 = list.subList(0, 3);
        subList1.set(0, 9999);
        System.out.println("subList1 = " + subList1);
        System.out.println("list = " + list);

    }

    /**
     * String[] 化为 List<String>
     */
    @Test
    public void StringsToList() {
        String[] str = {"111", "222", "333"};
        System.out.println(str.length);
        List<String> res1 = new ArrayList<>(Arrays.asList(str));
        List<String> res2 = new ArrayList<>(str.length);
        for (int i = 0; i < str.length; i++) {
            res2.add(str[i]);
        }
        List<String> res3 = new ArrayList<>(str.length);
        Collections.addAll(res3, str);
        System.out.println("res1 = " + res1);
        System.out.println("res2 = " + res2);
        System.out.println("res3 = " + res3);

    }


}
