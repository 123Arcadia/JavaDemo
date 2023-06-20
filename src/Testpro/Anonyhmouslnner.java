package Testpro;

public class Anonyhmouslnner {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.hi();
    }
}

class Outer {
    private  int n1 = 100;

    public void hi() {
        int n2 = 30;
        new Person1() {

            public void say() {
                System.out.println("say()....");
            }
        }.say();
    }

}

class Person1 {
    public void hi() {
        System.out.println("Person_say()....");
    }

    public void ok (String str) {
        System.out.println("PPerson_ok()..." + str);
    }


    public void say() {
        System.out.println("");
    }
}