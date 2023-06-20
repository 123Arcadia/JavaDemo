package Lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class lambda_Test {
    @Test
    public void lambda() {
        List<Integer> list = new ArrayList<>() {{
            add(1);
            add(7);
            add(2);
            add(5);
        }};

        Collections.sort(list, (Integer o1, Integer o2) -> -(o1 - o2));
        System.out.println("list = " + list); // list = [7, 5, 2, 1]

        //分解开
        Comparator<Integer> comperator = (Integer o1, Integer o2) -> o2 - o1;

        Collections.sort(list, comperator);
        System.out.println("list = " + list); // list = [7, 5, 2, 1]
    }

    @Test
    public void ThreadTest() {
        Runnable runnable = () -> System.out.println("-----");
        runnable.run(); // -----

        // 这样写错的
//        new Runnable(() -> System.out.println("->->->")).run();

        new Thread(() -> System.out.println("Thread...")).start(); // Thread...

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runn....");
            }
        }).start(); // runn....

        Runnable r = () -> System.out.println("r ====");
        new Thread(r).start(); // r ====
    }

    /**
     * forEach访问 数组 和 Collection
     */
    @Test
    public void ForEach() {
        List<String> strings = new ArrayList<>() {{
            add("I");
            add(" am");
            add(" is");
            add(" zcw!!");
        }};
        /**
         * Lambda forEach
         *      forEach:实际上调用的是 Consumer
         */
        strings.forEach((s) -> System.out.println(s));
        //I
        // am
        // is
        // zcw!!

        /**
         * 方法引用
         */
        strings.forEach(System.out::println);
        //I
        // am
        // is
        // zcw!!

        //map
        Map<Integer, String> map = new HashMap<>() {{
            put(1, "111");
            put(2, "222");
            put(3, "333");
        }};
        map.forEach((k, v) -> System.out.println(v));
        //111
        //222
        //333
    }

    /**
     * 函数式编程
     */
    @Test
    public void Test() {
        Func func = (s) -> {
            System.out.println("s show()..." + s);
        };
        func.show("zcw"); // s show()...zcw
    }

    interface Func {
        public void show(String s);
    }

    @Test
    public void forEach2() {
        List<Integer> list = new ArrayList<>() {{
            add(1);
            add(7);
            add(2);
            add(5);
        }};
        list.forEach((s) -> System.out.println(s));
        //1
        //7
        //2
        //5

        list.stream().forEach(System.out::println);
        //1
        //7
        //2
        //5
        /**
         * 为什么方法引用可以作为Comparaktor的参数???
         * compareTo是成员方法，传参是先传this
         */
        Collections.sort(list, Integer::compareTo);
        System.out.println("list = " + list); // list = [1, 2, 5, 7]


        /**
         * 为什么compare也可以？？？
         *      compare是静态方法,调用是不会传this,相当于(o1, o2) -> o2 - o1)
         */
        Collections.sort(list, Integer::compare);
        //就等于
//        Collections.sort(list, (o1, o2) -> o2 - o1); // 这里换成逆序
        System.out.println("[Integer::compare]list = " + list); // list = [1, 2, 5, 7]

        /**
         * 使用stream().sorted(Comparator)排序
         */
        List<Integer> list_sorted_compare = list.stream().sorted(Integer::compare).collect(Collectors.toList());
        System.out.println("list_sorted_compare = " + list_sorted_compare); // [1, 2, 5, 7]

        List<Integer> list_sorted_compareTo = list.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println("list_sorted_compareTo = " + list_sorted_compareTo); // [1, 2, 5, 7]

    }

}
