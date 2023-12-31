package AAATemp;

public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
//        ClassA a1 = new ClassA();
//        ClassA a2 = (ClassA)a1.clone();
        CloneTest c = new CloneTest();
        CloneTest a1 = (CloneTest) c.clone();
//        System.out.println(a1 == a2); // false
//        assertNotEquals(a1, a2);
        System.out.println(a1);

    }
}

class ClassA implements Cloneable {
    String name;


}
