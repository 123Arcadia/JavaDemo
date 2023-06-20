package Testpro;

public class StaticCodeMovie {
    public static void main(String[] args) {
//        Actor actor1 = new Actor();
//        Actor actor2 = new Actor();
        System.out.println(Actor.age);;
    }
}

class Actor {
    static int age = 19;
    static {
        System.out.println("正在调用代码块...");
    }
    public Actor() {
        System.out.println("正在调用构造器...");
    }
}
