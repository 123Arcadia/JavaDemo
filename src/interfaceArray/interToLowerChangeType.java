package interfaceArray;


class Animal extends Object {

    public void move() {
        System.out.println("动物在移动");
    }
}


class Bird extends Animal {
    @Override
    public void move() {
        System.out.println("鸟儿在飞翔");
    }

    public void fly() {
        System.out.println("Bird fly");
    }

}
class Cat extends Animal {

    //重写父类中继承的方法
//    public void move() {
//        System.out.println("猫在走猫步");
//    }


    @Override
    public void move() {
        System.out.println("猫在走猫步");
    }

    public void catchMouse() {
        System.out.println("猫抓老鼠");
    }
}

/**
 * 向下转型[downcasting]:
 */
public class interToLowerChangeType {

    public static void main(String[] args) {
        Animal a2 = new Cat();//向上转型
        a2.move();
        Cat c2 = (Cat) a2; // 向下专型: 本来就是Cat类型s
        c2.catchMouse();

        // 为什么这里运行报错
        try {
            Animal a3 = new Bird();
            Cat c3 = (Cat) a3;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 因为JVM堆内存中真实存在的对象是Bird类型，Bird对象无法转换为Cat对象，Bird和Cat之间不存在继承关系，这就是著名的异常：Java.lang.ClassCastException(类型转换异常)

        //子类指向父类, 仍然报错
        Cat c4 = null;
        try {
            Animal a4 = new Animal();
            c4 = (Cat) a4;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  因为a4是Animal这个父类对象的数据类型，而c4指向的是子类对象Cat，子类对象不能指向父类对象，该异常遇上个问题相同：Java.lang.ClassCastException(类型转换异常)

        // 向下转型解决： instanceof
        //解决方式：
        Animal a3 = new Bird();
        if(a3 instanceof Cat) {
            System.out.println("->");
            Cat c3 = (Cat) a3;
            c3.catchMouse();
        } else if(a3 instanceof Bird) {
            System.out.println("<-");
            Bird b2 = (Bird)a3;
            b2.fly();
            //<-
            //Bird fly
        } else {
            System.out.println("no this class!");
        }

    }

}
