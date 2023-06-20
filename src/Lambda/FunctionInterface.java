package Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
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
    public void test2(){
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
