package List;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ListTest {
    @Test
    public void test01() {
        Set list =  new LinkedHashSet(); // 尽量用ArraysList
        list.add(45);
        list.add(2);
        list.add(new Person("zcw", 19));

        //list.add(1, "111");

        List list1 = Arrays.asList(7,8,9,"10");
        list.add(list1);
        System.out.println("list = " + list + " --length = " + list.toArray().length);
        //list = [45, 2, List.Person@256216b3, [7, 8, 9, 10]] --length = 4

        // 这里是删除的对象，不是索引
        list.remove(2);


        System.out.println("list = " + list + " --length = " + list.toArray().length);
        //list = [45, List.Person@256216b3, [7, 8, 9, 10]] --length = 3

//        list.subList(2,list1.toArray().length);
//        System.out.println("list = " + list.subList(2, 4));
    }
}
class Person {
    private int age;
    private String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
