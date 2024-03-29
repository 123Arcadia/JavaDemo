package ApacheGuava.guava01;

import ApacheGuava.guava01.pojo.Person;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import org.junit.Test;

import java.util.Date;

/**
 * 有时 key 并不是单一的类型，而是多种类型，Guava 为此提供了 ClassToInstanceMap，
 * key 可以是多种类型，value 是此类型的实例。
 */
public class ClassToInstanceMapTest {

    @Test
    public void ClassToInstanceMap1() {
        ClassToInstanceMap<Object> classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.put(Person.class, new Person(100, "张三", new Date(), false, 13000F));
        classToInstanceMap.put(Integer.class, 200);
        classToInstanceMap.put(String.class, "success");

        Object person = classToInstanceMap.get(Person.class);
        //Person{id=100, name='张三', birthday=2020-07-25T16:32:19.014, marry=false, price=13000.0}
        System.out.println(person);

        Object code = classToInstanceMap.get(Integer.class);
        //200
        System.out.println(code);

        Object msg = classToInstanceMap.get(String.class);
        System.out.println(msg);
        //success

        //类型相同时，即 Key 相同时，覆盖旧值
        classToInstanceMap.put(Person.class, new Person(200, "李四", new Date(), false, 13000F));
        System.out.println(classToInstanceMap.get(Person.class));
    }

}