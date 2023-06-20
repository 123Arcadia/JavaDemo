package Collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }


    }

    @Test
    public void test_set() {
        Set set = new HashSet();
        set.add(123);
        set.add(456);
        set.add("fgd");
        set.add("book");
        set.add(new Person("Tom",12));
        set.add(new Person("Tom",12));
        set.add(129);

        for (Object obj : set) {
            System.out.print(obj + " ");
        }
        // 由于我们没有重写自定义Person类的equals()和hashCode()方法，所以Tom还是会输出两次
        // Person{name='Tom', age=12} 129 fgd Person{name='Tom', age=12} book 456 123
    }
}
