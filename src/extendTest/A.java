package extendTest;

import java.util.Objects;

public class A {
    public static void main(String[] args) {
        A y = new B();
        B z = new B();

        System.out.println(Objects.equals(y.fish(z), z.fish(z))); // (3, 3) true
        System.out.println("y.fish(z) = " + y.fish(z)); // 3

        /**
         * 先调用 B.fish(), B没有, 就进去A调用
         */
        System.out.println("z.fish(y) = " + z.fish(y)); // 1
        System.out.println("y.fish(y) = " + y.fish(y)); // 1
        System.out.println("z.fish(z) = " + z.fish(z)); // 3
    }

    int fish(A other) {
        return 1;
    }
    int fish(B other) {
        return 2;
    }
}

class B extends A {
    @Override
    int fish(B other) {
        return 3;
    }
}
