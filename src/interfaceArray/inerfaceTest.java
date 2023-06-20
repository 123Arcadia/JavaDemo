package interfaceArray;

public class inerfaceTest {
    public static void main(String[] args) {
        System.out.println("x = "+B.x);
        A a = new A();

        System.out.println(a.hashCode());
        System.out.println(a.toString());

    }

}


class A {
    public static int x = 1;
}

class B extends A {
    public static int x = 2;
}

class C extends B {
    public static int x = 3;
}
