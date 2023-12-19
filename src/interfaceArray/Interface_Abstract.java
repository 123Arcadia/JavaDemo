package interfaceArray;

public class Interface_Abstract extends Person1 implements Person {

    /**
     * 来自Person
     */
    @Override
    public void say() {
        System.out.println("say...111");
    }

    /**
     * 来自Person1
     */
    @Override
    public void call() {
        System.out.println("call...111");
    }

    public static void main(String[] args) {
        Person person = new Person() {
            @Override
            public void say() {
                System.out.println("interface say...");
            }
        };
        person.defa(); //  default...
        Person.call(); // interface call
        person.say(); // interface say...
        System.out.println("Person.N = " + Person.N); // 7



    }
}

/**
 * JDK 1.8允许给接口添加 非抽象 的方法实现，但必须使用default关键字修饰；定义了default的方法可以不被实现子类所实现，
 * 但只能被实现子类的对象调用；如果子类实现了多个接口，并且这些接口包含一样的默认方法，则子类必须重写默认方法；
 */

interface Person {
    /**
     * static可以通过接口类名直接访问
     */
    public static  final int N = 7;
    static void call() {
        System.out.println("interface call");
    }

    /**
     * default: 允许接口聂有完成时间的方法体
     */
    default void defa() {
        System.out.println(" default... ");
    }

    public void say();
}

/**
 * 抽象类中的抽象方法的修饰符只能为 public 或者 protected，默认为 public；
 *
 * 抽象类可以包含属性、方法、构造方法，[但是构造方法不能用于实例化]，主要用途是被子类调用。
 */
abstract class Person1{
    public abstract void say();
    public void call() {};
}
