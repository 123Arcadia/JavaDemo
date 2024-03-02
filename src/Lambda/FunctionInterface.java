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
        con.accept("������������ʲô��");

        System.out.println("+++++++++++++++++++");

        Consumer<String> c1 = (String s) -> {
            System.out.println(s);
        };
        c1.accept("�����������ƶ�,�����������ƶ�");
        //������������ʲô��
        //+++++++++++++++++++
        //�����������ƶ�,�����������ƶ�

        System.out.println("-----------����Consumer��addThen-------------");
        //ר������Student�����Consumer
        Consumer<Integer> consumerInt = i -> {i++;
            System.out.println("i = " + i);};

        consumerInt.andThen(i -> {i+=2;
                    System.out.println("i(1) = " + i);})
                    .andThen(i -> {i+=2;
                        System.out.println("i(2) = " + i);})
                    .accept(10);
        // consumerIntִ��accept������andThen�е�lambda����ִ��
        // ������ ע�⣺����accept����û�д��ͼ����������ã���������andThen������i����10 ������
        //i = 11
        //i(1) = 12
        //i(2) = 12

         class Student {
            public void hello(){
                System.out.println("����ѧ����");
            }
        }

        /**
         * compose��ָ������ʽ�Ľ����Ϊ��ǰ����ʽ��ʵ��
         */
        Function<Integer, String> INTEGER_STRING_FUNCTION = Object::toString;
        String str = INTEGER_STRING_FUNCTION
                .compose((String s) -> s.length())   //���˺���ʽ�ķ���ֵ��Ϊ��ǰʵ�ֵ�ʵ��
                .apply("lbwnb");   //�������溯��ʽ��Ҫ�Ĳ���
        System.out.println("str = " + str); // 5


    }

    /**
     * java����ʽ�ӿ�
     */
    @Test
    public void Predict () {
        /**
         * ��һ�֣�
         */
//        Predicate<Integer> predicate = new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {
//                return integer % 2 == 0;
//            }
//        };
        /**
         * �ڶ��֣�
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
