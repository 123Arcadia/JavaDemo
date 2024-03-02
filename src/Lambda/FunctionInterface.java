package Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionInterface {
    @Test
    public void test01() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1 -> run()......");
            }
        };
        r1.run();

        Runnable r2 = () -> System.out.println("r2 -> run()......");
        r2.run();
    }

    @Test
    public void Consumer_test(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("善与恶的区别是什么？");

        System.out.println("+++++++++++++++++++");

        Consumer<String> c1 = (String s) -> {
            System.out.println(s);
        };
        c1.accept("先天人性无善恶,后天人性有善恶。");
        //善与恶的区别是什么？
        //+++++++++++++++++++
        //先天人性无善恶,后天人性有善恶。

        System.out.println("-----------测试Consumer的addThen-------------");
        //专门消费Student对象的Consumer
        Consumer<Integer> consumerInt = i -> {i++;
            System.out.println("i = " + i);};

        consumerInt.andThen(i -> {i+=2;
                    System.out.println("i(1) = " + i);})
                    .andThen(i -> {i+=2;
                        System.out.println("i(2) = " + i);})
                    .accept(10);
        // consumerInt执行accept方法后交andThen中的lambda继续执行
        // ！！！ 注意：这里accept方法没有传送计算结果的作用，所以两个andThen方法的i都是10 ！！！
        //i = 11
        //i(1) = 12
        //i(2) = 12

         class Student {
            public void hello(){
                System.out.println("我是学生！");
            }
        }

        /**
         * compose将指定函数式的结果作为当前函数式的实参
         */
        Function<Integer, String> INTEGER_STRING_FUNCTION = Object::toString;
        String str = INTEGER_STRING_FUNCTION
                .compose((String s) -> s.length())   //将此函数式的返回值作为当前实现的实参
                .apply("lbwnb");   //传入上面函数式需要的参数
        System.out.println("str = " + str); // 5


    }

    /**
     * java函数式接口
     */
    @Test
    public void Predict () {
        /**
         * 第一种：
         */
//        Predicate<Integer> predicate = new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {
//                return integer % 2 == 0;
//            }
//        };
        /**
         * 第二种：
         */
        Predicate<Integer> predicate1 = (s) -> s % 2 == 0;

        List<Integer> list = new ArrayList<>() {{
            add(2);
            add(4);
            add(5);
            add(8);
        }};
        for (int i = 0; i < list.size(); i++) {
            boolean test = predicate1.test(list.get(i));
            System.out.println("odd list.get(i) : " + (test ? list.get(i) : false));
        }
        //odd list.get(i) : 2
        //odd list.get(i) : 4
        //odd list.get(i) : false
        //odd list.get(i) : 8
    }


}
