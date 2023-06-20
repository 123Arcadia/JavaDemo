package Time.DatetimeTest;

public class Girlfriend {
    final static int age = 19;
    public static void main(String[] args) {

        GirlFriend1 instance = GirlFriend1.getInstance();
        System.out.println(instance);
        System.out.println(GirlFriend1.n1);  //此处构造器值调用了一次！！！

        System.out.println(age);
    }
}

class GirlFriend1 {
    private String name;
    public static int n1 = 100;
    private static GirlFriend1 gf = new GirlFriend1("小红红");
    private GirlFriend1(String name) {
        System.out.println("构造器调用......");
        this.name = name;
    }


    public static GirlFriend1 getInstance() {
        return gf;
    }

    @Override
    public String toString() {
        return "GirlFriend1{" +
                "name='" + name + '\'' +
                '}';
    }
}
abstract class T {
    static int age;
    public abstract int getInt () ;
}



