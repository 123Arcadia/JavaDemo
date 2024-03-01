package Lambda.StreamTest;

import Lambda.cyborg_Stream.Employee;
import Lambda.cyborg_Stream.EmployeeData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    /**
     * 超创建
     *
     */
    @Test
    public void test1() {
        // 使用 Stream.of() 方法直接将一组元素转换为 Stream 对象
        Stream<String> stream = Stream.of("aaa", "bbb", "ccc");

        //如果我们不确定要添加多少个元素到 Stream 中，可以使用 Stream.builder() 创建一个 Stream.Builder 对象，并使用其 add() 方法来逐个添加元素，最后调用 build() 方法生成 Stream 对象
        Stream<Object> buidStream = Stream.builder().add("aaa").add("bbb").add("ccc").build();

        Stream<Integer> stream1 = Stream.generate(() -> 0); // 创建一个无限流，每个元素都是 0
        Stream<Integer> stream2 = Stream.iterate(0, n -> n + 1); // 创建一个无限流，从 0 开始递增



    }
    /**
     * stream():
     * limit()
     * filter(Predicate)
     * distinct()
     * skip(long n)  与limit互补; 跳过前n个元素 (需要注意的是，在使用截断操作时需要注意流的有界性。如果流是无界的（例如 Stream.generate()），那么使用 limit() 方法可能导致程序陷入无限循环，而使用 skip() 方法则没有意义)
     */
    @Test
    public void stream_List() {
        List<String> list = new ArrayList<>(Arrays.asList("zzz", "ccc", "www"));
        System.out.println("list = " + list.stream().limit(2).collect(Collectors.toList()));
        //list.stream().limit(2) = [zzz, ccc]
        System.out.println("list = " + list);

        /**
         * 遍历
         */
        list.stream().forEach(System.out::println);
        //zzz
        //ccc
        //www

        List<String> list_filter = list.stream().filter(s -> !"ccc".equals(s)).collect(Collectors.toList());
        System.out.println("list_filter = " + list_filter);
        //list_filter = [zzz, www]


        List<String> list_skip = list.stream().skip(2).collect(Collectors.toList());
        System.out.println("list_skip = " + list_skip); // list_skip = [www]
    }

    @Test
    public void test19() {
        List<String> list = new ArrayList<>(Arrays.asList("zzz", "ccc", "www"));
        List<String> list_map = list.stream().map(String::toUpperCase).filter(s -> !"CCC".equals(s)).collect(Collectors.toList());
        System.out.println("list_map = " + list_map);
        //list_map = [ZZZ, WWW]
    }


    /**
     * sorted()	产生一个新流，其中按自然顺序排序
     * sorted(Comparator com)	产生一个新流，其中按比较器顺序排序
     * sorted(Comparator.reverseOrder())
     */
    @Test
    public void test20() {
        List<Integer> list = new ArrayList<>(Arrays.asList(13, 54, 97, 52, 43, 64, 27));
        List<Integer> list_sorted = list.stream().sorted().collect(Collectors.toList());
        System.out.println("list_sorted = " + list_sorted);
        //list_sorted = [13, 27, 43, 52, 54, 64, 97]

        List<Integer> list_sorted_Asc = list.stream().sorted(Integer::compare).collect(Collectors.toList());
        System.out.println("list_sorted_Asc = " + list_sorted_Asc);
        //[13, 27, 43, 52, 54, 64, 97]

        //降序
        List<Integer> list_sorted_Desc = list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("list_sorted_Desc = " + list_sorted_Desc);
        //list_sorted_Desc = [97, 64, 54, 52, 43, 27, 13]
    }

    /**
     * allMatch(Predicate p)	检查是否匹配 所有 元素
     * anyMatch(Predicate p)	检查是否 至少 匹配一个元素
     * noneMatch(Predicate p)	检查是否 没有匹配 所有元素
     * findFirst()	            返回第一个元素
     * findAny()	            返回当前流中的 任意元素
     * count()	                返回流中元素总数
     * max(Comparator c)	    返回流中最大值
     * min(Comparator c)	    返回流中最小值
     * forEach(Consumer c)	    内部迭代(使用Collection 接口需要用户去做迭代，称为外部迭代。相反，Stream API 使用内部迭代——它帮你把迭代做了)
     */



    /**
     * 归约（Reduce）:
     *
     * reduce(T iden, BinaryOperator b)	可以将流中元素反复结合起来，得到一个值。返回T
     * reduce(BinaryOperator b)	可以将流中元素反复结合起来，得到一个值。返回Optional
     * reduce(U identity,BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
     * <p>
     * Employee来自cyborg_Stream\Employee.java
     */
    @Test
    public void test22() {
        List<Integer> list = new ArrayList<>(Arrays.asList(13, 54, 97, 52, 43, 64, 27));
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println("reduce = " + reduce); // reduce = 350

        List<Employee> employees = EmployeeData.getEmployees();
        // reduce(T identity, BinaryOperator)——可以将流中元素反复结合起来，得到一个值。返回 T
        // 练习1：计算1-10的自然数的和
        System.out.println(list.stream().reduce(0, Integer::sum));
        //reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
        // 练习2：计算公司所有员工工资总和
        System.out.println(employees.stream().map(Employee::getSalary).reduce((o1, o2) -> o1 + o2));
        // 别的写法，计算年龄总和
        System.out.println(employees.stream().map(Employee::getAge).reduce(Integer::sum));


        ArrayList<Integer> newList = new ArrayList<>();
        long s = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        /**
         * 注意：newList线程不安全
         */
        ArrayList<Integer> accResult_s = Stream.of(1, 2, 3, 4)
                .reduce(newList,
                        (acc, item) -> {
                            acc.add(item);
                            System.out.println("item: " + item);
                            System.out.println("acc+ : " + acc);
                            System.out.println("BiFunction");
                            return acc;
                        }, (acc, item) -> null);
        long nanoTime1 = System.nanoTime();
        long e = System.currentTimeMillis();
        System.out.println("times: " + (e - s)); // 1 ms
        System.out.println("times: " + (nanoTime1 - nanoTime)); // times: 1284900
//        System.out.println("end -start = " + (end - start));

        System.out.println("accResult_s: " + accResult_s); // [1, 2, 3, 4]
    }

    /**
     * parallel 下的 reduce
     */
    @Test
    public void test_parallel_reduce() {
        Long start = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        System.out.println(
                Stream.of(1, 2, 3, 4).parallel().reduce(5, new BiFunction<Integer, Integer, Integer>() {
                            @Override
                            public Integer apply(Integer integer, Integer integer2) {
                                return integer + integer2;
                            }
                        }
                        , new BinaryOperator<Integer>() {
                            @Override
                            public Integer apply(Integer integer, Integer integer2) {
                                return integer + integer2;
                            }
                        }));
        // 结果：30
        long end = System.currentTimeMillis();
        long nanoTime1 = System.nanoTime();
        System.out.println("times: " + (end - start)); // times: 2259700
        System.out.println("times: " + (nanoTime1 - nanoTime)); // 3ms
        //结果：30
        //先5 + 1,5 +  2,5 +  3,5 + 4； 在前两个之和13 + 后两个之和17 = 30
    }



    @Test
    public void Stream_Cases() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        //返回符合条件的stream
        Stream<String> stringStream = strings.stream().filter(s -> "abc".equals(s));
        //计算流符合条件的流的数量
        long count = stringStream.count();
        System.out.println("count = " + count); // 2

        //forEach遍历->打印元素
//        strings.forEach(System.out::println);
        strings.stream().forEach(System.out::println);

        System.out.println("========");

        //limit 获取到1个元素的stream
        Stream<String> limit = strings.stream().limit(2);
        //toArray 比如我们想看这个limitStream里面是什么，比如转换成String[],比如循环
//        String[] array = limit.toArray(String[]::new);
//        for (String s : array) {
//            System.out.println(s);
//        }
//        //abc
//        //def
//        System.out.println("array = " + Arrays.toString(array)); // array = [Ljava.lang.String;@2f7c7260
        String[] array1 = limit.toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });
        System.out.println("array = " + Arrays.toString(array1)); // array = [abc, def]


        System.out.println("========");

        //map 对每个元素进行操作返回新流
        Stream<String> map = strings.stream().map(s -> s + "22");
        System.out.println("map.collect(Collectors.toList()) = " + map.collect(Collectors.toList()));
        // map.collect(Collectors.toList()) = [abc22, def22, gkh22, abc22]

        System.out.println("========");

        //sorted 排序并打印
        strings.stream().sorted().forEach(System.out::println);
        //abc
        //abc
        //def
        //gkh

        System.out.println("========");

        //Collectors collect 把abc放入容器中
        List<String> collect = strings.stream().filter(string -> "abc".equals(string)).collect(Collectors.toList());
        Long num = strings.stream().filter(string -> "abc".equals(string)).count();
        System.out.println("collect = " + collect); // collect = [abc, abc]
        System.out.println("num = " + num); // num = 2

        //把list转为string，各元素用，号隔开
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("mergedString = " + mergedString); // mergedString = abc,def,gkh,abc

        System.out.println("========");

        //对数组的统计，比如用
        List<Integer> number = Arrays.asList(1, 2, 5, 4);

        /**
         * 统计
         * summaryStatistics: 从 Stream 中获取一些常用的统计信息，如元素个数、最小值、最大值、总和和平均值
         */
        IntSummaryStatistics statistics = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数(max) : " + statistics.getMax());
        System.out.println("列表中最小的数(min) : " + statistics.getMin());
        System.out.println("平均数(avg) : " + statistics.getAverage());
        System.out.println("所有数之和(sum) : " + statistics.getSum());
        //鍒楄〃涓渶澶х殑鏁�(max) : 5
        //鍒楄〃涓渶灏忕殑鏁�(min) : 1
        //骞冲潎鏁�(avg) : 3.0
        //鎵�鏈夋暟涔嬪拰(sum) : 12


        //concat 合并流
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        //其中List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        System.out.println("Stream.concat(strings2.stream(), strings.stream()).count() = "
                + Stream.concat(strings2.stream(), strings.stream()).count());
        //Stream.concat(strings2.stream(), strings.stream()).count() = 6

        System.out.println(Stream.concat(strings2.stream(), strings.stream()).collect(Collectors.toList()));
        //[xyz, jqx, abc, def, gkh, abc]


        /**
         * 注意 一个Stream只能 操作一次，不能断开，否则会报错。
         */
        Stream stream = strings.stream();
        //第一次使用
        System.out.println("stream.limit(2).collect(Collectors.toList()) = "
                + stream.limit(2).collect(Collectors.toList())); // [abc, def]

        //第二次使用
//        stream.forEach(System.out::println);
        //报错 java.lang.IllegalStateException: stream has already been operated upon or closed

    }

    /**
     * flatMap(Function f):
     *      接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void testFlapMap() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");

        List<Object> res = list.stream().flatMap(s -> Arrays.stream(s.split(""))).collect(Collectors.toList());
        System.out.println("res = " + res);
        // res = [a, a, b, b, c, c, d, d]


        List<String> usernames = Arrays.asList("alice", "bob", "charlie");
        List<String> emails = Arrays.asList("alice@example.com", "bob@example.com", "charlie@example.com");

        List<String> all = Stream.of(usernames, emails)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println(all);
        // [alice, bob, charlie, alice@example.com, bob@example.com, charlie@example.com]
        all.add("1");
        System.out.println("all = " + all);

        /**
         * 处理多维数组
         */

        int[][] matrix = {{1, 2}, {3, 4}, {76, 6}};
        int[] res_array = Arrays.stream(matrix).flatMapToInt(s -> Arrays.stream(s)).toArray();
        System.out.println("res_array = " + Arrays.toString(res_array)); //res_array = [1, 2, 3, 4, , 6]
        List<List<Integer>> list1 = new ArrayList<>();

        list1.add(new ArrayList<>(Arrays.asList(1,2)));
        list1.add(new ArrayList<>(Arrays.asList(3,4)));
        List<Integer> collect = list1.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("collect = " + collect);
        //collect = [1, 2, 3, 4]

    }

    /**
     * 并行流
     *
     * 避免共享可变状态：在并行流中，多个线程会同时操作数据。如果共享可变状态（如全局变量）可能导致数据竞争和不确定的结果。因此，避免在并行流中使用共享可变状态，或者采取适当的同步措施来确保线程安全。
     *
     * 使用合适的操作：
     *      一些操作在并行流中的性能表现更好，而另一些操作则可能导致性能下降。一般来说，在并行流中使用基于聚合的操作（如 reduce、collect）和无状态转换操作（如 map、filter）的性能较好，
     *      而有状态转换操作（如 sorted）可能会导致性能下降。
     */
    @Test
    public void testParallelStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.parallelStream()
                .map(n -> n) // 在多个线程上并行处理计算
                .forEach(System.out::println);
        //3
        //5
        //4
        //1
        //2

        /**
         * FileStreamExample
         */
        String fileName = "src/Lambda/StreamTest/Streamfile.txt";
        // 读取文件内容并创建 Stream
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            // 打印文件的每一行内容
//            stream.forEach(System.out::println);

            // 统计文件的行数
            long count = stream.count();
            System.out.println("lineCount:" + count);

            // 筛选包含关键词的行并打印输出
//            stream.filter(line -> line.contains("keyword"))
//                    .forEach(System.out::println);

            // 将文件内容转换为大写并打印输出
//            stream.map(String::toUpperCase)
//                    .forEach(System.out::println);

            // 将文件内容收集到 List 中
//            List<String> lines = stream.collect(Collectors.toList());
//            System.out.println(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Stu{
        private String name;
        private Integer age;
    }

    /**
     * Stream: 由List<Object>转为Map<K, V>, K为对象的每个属性，V为对象
     */
    @Test
    public void testStream2() {
        List<Stu> stuList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            stuList.add(new Stu(i+"", i));
        }
        Map<String, List<Stu>> groupByAppIdStus = stuList.stream().collect(Collectors.groupingBy(Stu::getName));
        System.out.println("groupByAppIdStus = " + groupByAppIdStus);
    }

}
