package DeepCopy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

public class DeepShallowCopy {
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        Teacher tea1 = new Teacher("teacher1", "age1Tech");
        Person person1 = new Person("person1", "age1", tea1);
        Person person2 = new Person("person2", "age2", tea1);

        System.out.println("person1 = " + person1);
        System.out.println("person2 = " + person2);

        person2 = (Person) person1.clone();
        //;这里是浅拷贝
        System.out.println(person2 == person1); //false
        System.out.println(person2.getTeacher() == person1.getTeacher()); //false -> 是深拷贝
        System.out.println(person2.getName() == person1.getName()); //true -> name还是前拷贝

        //



        /**
         * clone一次调用，是其本省属性的拷贝，其引用类型（Teacher）不会拷贝
         * 除非自己实现clone方法
         */
    }

    /**
     * 使用ccommons-lang3
     */
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Person implements Cloneable {
    private String name;
    private String age;
    private Teacher teacher;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    /**
     * 如果想要实现深拷贝，可以通过覆盖Object中的clone方法的方式。
     * 要在clone对象时进行深拷贝，就要implements Clonable接口，覆盖并实现clone方法
     * ，除了调用父类中的clone方法得到新的对象， 还要将该类中的引用变量也clone出来。
     * 如果只是用Object中默认的clone方法，是浅拷贝的。
     * deepCopy
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person clone = (Person)super.clone();
//        if (clone != null) {
////            Teacher techClone = (Teacher) super.clone();
//            Object clone1 = clone.getTeacher().clone();
//        }
        clone.teacher = (Teacher)teacher.clone();
        return clone;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Teacher implements Cloneable {
    private String name;
    private String age;

    /**
     * 默认对象的内容是浅拷贝
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
