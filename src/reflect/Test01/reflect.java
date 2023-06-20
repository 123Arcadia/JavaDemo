package reflect.Test01;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class reflect {
    //����֮ǰ������Person�Ĳ���
    @Test
    public void test(){

        //1.������Ķ���
        Person p1 = new Person("jay",21);

        //2.���ö���,�������ڲ������Ժͷ���
        p1.age = 15;
        System.out.println(p1.toString());

        p1.show();

        //��Person����ⲿ��������ͨ��Person��Ķ���������ڲ�˽�еĽṹ��
        //���磺name��showNation�Լ�˽�еĹ�������

    }

    @Test
    public void test02() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class clazz = Person.class;
//        Class<Person> clazz = Person.class;
        Constructor cons = clazz.getConstructor(String.class, int.class);

        Object obj = cons.newInstance("Tom", 12);

        Person p = (Person)obj;
        System.out.println(p.toString());

        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());
//         ʧ�ܣ����ܵ���private
//        Field name = clazz.getDeclaredField("name");
//        name.set(p, "NLP!!!");
//        System.out.println("name������ = " + name);

        //���÷���
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);
    }

    @Test
    public void Test03() throws ClassNotFoundException {
        Class clazz = Person.class;
        Person p1 = new Person("jack", 19);
        System.out.println("p1.getClass() = " + p1.getClass());
        System.out.println(Person.class);

        Class c1 = clazz.forName("www.gh110.com");
        System.out.println(c1);

        ClassLoader classLoader = reflect.class.getClassLoader();
        Class c4 = classLoader.loadClass("www.gh110.com");
        System.out.println(c4);

        System.out.println(c1 == c4);

    }

    //����private
    @Test
    public void test03_1() throws Exception {
        //1.ͨ�����䣬����Person��Ķ���
        Class<Person> cl = Person.class;
        Constructor<Person> cons = cl.getDeclaredConstructor(String.class);
        cons.setAccessible(true);
        Person p1 = cons.newInstance("BLP");
        System.out.println("p1 = " + p1);

        //1.ͨ�����䣬����Person��Ķ���
        Field name = cl.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1, "NLP!!!");
        System.out.println("�����p1 = " + p1);

        Method showNation = cl.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        showNation.invoke(p1, "I am NLP");

    }

    @Test
    public void test4() {
        Class s1 = Object.class;
        Class s2 = Comparable.class;
        Class s3 = String[].class;
        Class s4 = int[][].class;
        Class s5 = ElementType.class;
        Class s6 = Override.class;
        Class s7 = int.class;
        Class s8 = void.class;
        Class s9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[4096];
        Class s10 = a.getClass();
        Class s11 = b.getClass();
        // ֻҪ�����Ԫ��������ά��һ��������ͬһ��Class
        System.out.println(s10 == s11); //true
    }
    @Test
    public void test1() {
        String a = "zcw";
        String b = "nlp";
        System.out.println(a + b);
        System.out.println(a.charAt(1));
        System.out.println(Integer.toBinaryString(5 & 0xff)); //101

        System.out.println((-7) % 3); // -1
        System.out.println(7 % (-3)); // 1
        System.out.println((-7) % (-3)); // -1
        System.out.println(5 % (-3)); // 2
        System.out.println((-5) % 3); // -2
    }


    @Test
    public void test111() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Person> classPerson = Person.class;
        ////1.ͨ�����䣬����Person��Ķ���
        Constructor<Person> constructor = classPerson.getDeclaredConstructor(String.class);
        Person instance = constructor.newInstance("����name");
        System.out.println("instance = " + instance);

        //2.ͨ�����䣬���ö���ָ�������Ժͷ���
        Field age = classPerson.getDeclaredField("age");
        age.set(instance, 11111);
        System.out.println("age = " + age);

        //���÷���
        Method showNation = classPerson.getDeclaredMethod("showNation", String.class);
        showNation.invoke(instance);
    }
}
