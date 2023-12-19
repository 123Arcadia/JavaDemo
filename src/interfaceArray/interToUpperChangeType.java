package interfaceArray;



class  Son1 implements Father {

    @Override
    public void action2() {

    }

    @Override
    public void action() {
        System.out.println("Action: Son1");
    }
}

class Son2 extends Grandpa {

}

class Grandpa {
    String name = "grandpa";

    public String getName() {
        return name;
    }
}

interface Father{
    public void action();
    public void action2();
}

/**
 * 向上转型[upcasting]:引用是接口
 */
public class interToUpperChangeType {

    public static void main(String[] args) {
        Grandpa grandpa = new Son2();
        System.out.println(grandpa.getName()); // son2
        // 如果子类没有继承该方法，你们退而调用父类的方法
    }

}