package toUpperLowerChangeType;

class Animal {
    public String name;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Animal eat() {
        System.out.println(name + " 正在吃东西，好吃！");
        return null;

    }

}

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);

    }

    public void wangWang() {
        System.out.println(name + " 正在汪汪叫，可能有外人来了");
    }

    public Dog eat() {
        System.out.println(name + " 正在啃香喷喷的骨头！");
        return null;
    }
}

class Bird extends Animal {
    public Bird(String name, int age) {
        super(name, age);
    }

    public void fly() {
        System.out.println(name + " 正在自由翱翔");
    }

}
public class changeToType {
    public static void main(String[] args) {
        Animal animal2 = new Bird("小鸟", 10);
//    animal2.fly(); 父类无法调用子类独有的方法
        //向下转型  但是 不安全
        if(animal2 instanceof Bird)
        {
            Bird bird = (Bird) animal2;
            bird.fly();
        }

        Animal animal1 = new Dog("小狗", 10);
        if(animal1 instanceof Bird)
        {
            Bird bird2 = (Bird) animal1;
            bird2.fly();
        } else

        {
            System.out.println("不能飞！");
        }
    }
}
