package DeepCopy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

public class DeepShallowCopy {
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        Teacher tea1 = new Teacher("teacher1", "age1Tech");
        Person person1 = new Person("person1", "age1");
        Person person2 = new Person("person2", "age2");

        System.out.println("person1 = " + person1);
        System.out.println("person2 = " + person2);

        person2 = (Person) person1.clone();
        //;这里是浅拷贝
        System.out.println(person2 == person1); //false
        System.out.println(person2.getTeacher() == person1.getTeacher()); //true

        System.out.println("[clone之后]person2 = " + person2);
        person2.setAge("age2 clone");
        person2.setTeacher(tea1);
        person2.getTeacher().setAge("age3Tech");
        person1.setTeacher(tea1);
        System.out.println("person1 = " + person1);
        System.out.println("person2 = " + person2);
        System.out.println(person2 == person1); //false
        System.out.println(person2.getTeacher() == person1.getTeacher()); //true

        /**
         * clone一次调用，是其本省属性的拷贝，其引用类型（Teacher）不会拷贝
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
     * deepCopy
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person clone = (Person)super.clone();
        if (clone != null) {
            Teacher techClone = (Teacher) super.clone();
        }
        return clone;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Teacher implements Cloneable {
    private String name;
    private String age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
