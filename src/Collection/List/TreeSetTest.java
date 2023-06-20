package List;

import org.junit.Test;

import java.util.Objects;
import java.util.TreeSet;

public class TreeSetTest {
    @Test
    public void test01() {
        TreeSet list = new TreeSet();
//        list.add(123);
//        list.add(555);
//        list.add(999);

        list.add(new Person01("zcw", 19));
        list.add(new Person01("zcw01", 22));
        list.add(new Person01("zcw02", 20));
        list.add(new Person01("zcw03", 19));
        list.add(new Person01("zcw04", 17));
        list.add(new Person01("zcw005", 17));
        list.add(new Person01("zcw005", 10));
        System.out.println("list = " + list);
    }
}

class Person01 implements Comparable {
    private String name;
    private int age;

    public Person01(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person01{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person01 person01 = (Person01) o;

        if (age != person01.age) return false;
        return Objects.equals(name, person01.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
/*

    //按姓名从小到大
    @Override
    public int compareTo(Object o) {
        if (o instanceof Person01) {
            Person01 person01 = (Person01) o;
            return this.name.compareTo(person01.name);
        } else {
            throw new RuntimeException("输入类型不可");
        }
    }
*/

/*
    //按姓名从大到小
    @Override
    public int compareTo(Object o) {
        if (o instanceof Person01) {
            Person01 person01 = (Person01) o;
            return -this.name.compareTo(person01.name);
        } else {
            throw new RuntimeException("输入类型不可");
        }
    }
    */

    //先按年龄从小到大，在按name从小到大
    @Override
    public int compareTo(Object o) {
        if (o instanceof Person01) {
            Person01 person01 = (Person01) o;
            if (this.age < person01.age) return 1;
            else if (this.age > person01.age) {
                return -1;
            }
            else {
                return this.name.compareTo(person01.name);
            }
        }
        throw new RuntimeException("Error!");
    }
}
