package finalize__;

public class finalize {
    public static void main(String[] args) {
        Car car = new Car("汽车");
        car = null;
        System.gc();
        System.out.println("程序退出");
    }
}

class Car {
    private String name;

    public Car(String name) {
        this.name = name;
    }
    //主动回收垃圾
    @Override
    protected void finalize() throws Throwable {
        System.out.println("我们销毁了汽车：" + name);
    }
}
