package Lambda.cyborg_Stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class streamTest {

    /**
     * 创建Stream方式一：通过集合
     */
    @Test
    public void test14() {
        List<Employee> employees = EmployeeData.getEmployees();
        //default Stream<E> stream()  返回一个顺序流
        Stream<Employee> stream = employees.stream();
        //default Stream<E> parallelStream  返回一个并行流
        Stream<Employee> employeeStream = employees.parallelStream();
    }

    /**
     * 创建Stream方式二：通过数组
     */
    @Test
    public void test15() {
        int[] arr = new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8};
        //调用Arrays的static <T> Stream<T> stream(T[] array)   返回一个流
        IntStream stream = Arrays.stream(arr);
        List<Integer> arrToList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println("arrToList = " + arrToList + "\narrToList.class: " + arrToList.getClass());
        //arrToList = [1, 3, 5, 7, 9, 2, 4, 6, 8]
        //arrToList.class: class java.util.ArrayList

        Employee kyle = new Employee(9527, "Kyle");
        Employee lucy = new Employee(9421, "Lucy");
        Employee[] employees = {kyle, lucy};
        Stream<Employee> stream1 = Arrays.stream(employees);
    }

    /**
     * 创建Stream方式三：通过Stream的of()
     */
    @Test
    public void test16() {
        Stream<Integer> stream = Stream.of(2, 4, 6, 8, 1, 3, 5, 7);
        List<Integer> collect = stream.collect(Collectors.toList());
        System.out.println("collect = " + collect);
        int sum = collect.stream().mapToInt(Integer::intValue).sum();
        //map就没有sum()
//        collect.stream().map(Integer::valueOf).
        System.out.println("sum = " + sum);
    }

    /**
     * 创建Stream方式四：创建无限流
     * 如果不用limit限制输出，则会一直输出下去，forEach就相当于是终止操作
     */
    @Test
    public void test17() {
        // 迭代
        // 遍历前10个数
        // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        Stream.iterate(0, t -> t + 1).limit(10).forEach(System.out::println);

        // 生成
        // 10个随机数
        // public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 字符串拼接
     */
    @Test
    public void String_stream() {
        String[] strings = {"a", "b", "c", "d", "e"};


        String reduce = Arrays.stream(strings).reduce("", (current, number) -> current + "|" + number);
        System.out.println("reduce = " + reduce);
        // |a|b|c|d|e
        String reduce2 = Arrays.stream(strings).reduce("", (current, number) -> {
            if (!"".equals(current)) {
                return current + "|" + number;
            } else {
                return number;
            }
        });
        System.out.println("reduce2 = " + reduce2);
        // a|b|c|d|e
    }

    @Test
    public void reduceTest() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 8, 3, 4));
        Integer reduce = list.stream().reduce(1, (a, b) -> {
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("a + b = " + (a + b));
            return a + b;
        });
        System.out.println("reduce = " + reduce);
        //a = 1
        //b = 1
        //a + b = 2
        //a = 2
        //b = 8
        //a + b = 10
        //a = 10
        //b = 3
        //a + b = 13
        //a = 13
        //b = 4
        //a + b = 17
        //reduce = 17

        // 1 8 3 4
        Integer reduce1 = list.stream().parallel().reduce(1, Integer::sum, (integer, integer2) -> {
            int i = integer + integer2;
            return i;
        });
        System.out.println("reduce1 = " + reduce1); // reduce1 = 20
    }

    @Test
    public void parallel() {
        System.out.println(
                Stream.of(1, 2, 3, 4).parallel().reduce(5, new BiFunction<Integer, Integer, Integer>() {
                            @Override
                            public Integer apply(Integer integer, Integer integer2) {
                                System.out.println("---integer = " + integer);
                                System.out.println("---integer2 = " + integer2);
                                System.out.println("---integer + integer2 = " + integer + integer2);
                                return integer + integer2;
                            }
                        }
                        , new BinaryOperator<Integer>() {
                            @Override
                            public Integer apply(Integer integer, Integer integer2) {
                                System.out.println("===integer = " + integer);
                                System.out.println("===integer2 = " + integer2);
                                System.out.println("===integer + integer2 = " + integer + integer2);
                                return integer + integer2;
                            }
                        }));
        //---integer = 5
        //---integer = 5
        //---integer2 = 1
        //---integer2 = 2
        //---integer = 5
        //---integer2 = 4
        //---integer = 5
        //---integer2 = 3
        //---integer + integer2 = 54
        //---integer + integer2 = 53
        //---integer + integer2 = 51
        //---integer + integer2 = 52
        //===integer = 6
        //===integer2 = 7
        //===integer + integer2 = 67
        //===integer = 8
        //===integer2 = 9
        //===integer + integer2 = 89
        //===integer = 13
        //===integer2 = 17
        //===integer + integer2 = 1317
        //30

    }

    @Test
    public void int_Long() {
        int[] arr = new int[]{8, 1, 34, 554, 55, 10};
        System.out.println("sum() = " + Arrays.stream(arr).sum()); // 662
        /**
         * int[] 和超出int需要转为Long
         */
        System.out.println("sum[Long] = " + Arrays.stream(arr).asLongStream().sum());

    }
    @Test
    public void test() {
        int[] arr = new int[]{8, 1, 34, 554, 55, 10};
        List<Integer> collect = Arrays.stream(arr).boxed().distinct().collect(Collectors.toList());
        System.out.println("collect = " + collect);
        int[] as = collect.stream().mapToInt(x -> x).toArray();
        for (int a : as) {
            System.out.println(a);
        }
        /**
         * 智能用于引用类型，不能是基本类型
         */
//        int[] array = collect.toArray();

    }




    @Test
    public void contains() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(4,9,5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(9,4,9,8,4));
        if (list1.containsAll(list2)) {
            System.out.println(" = ");
        } else {
            System.out.println("---");
        }
    }

}
