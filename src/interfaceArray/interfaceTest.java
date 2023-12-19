package interfaceArray;

public class interfaceTest implements Animal1{
    @Override
    public void action(String inrefName) {
        System.out.println("action Override: " + inrefName);
    }

    public static void main(String[] args) {
        interfaceTest test = new interfaceTest();
        test.action("test");
        System.out.println(Animal1.age);
        System.out.println(Animal1.name);
        Animal1 animal = (Animal1) new interfaceTest(); // 向上转型
        System.out.println(animal.cacluAge());
        //action Override: test
        //5
        //animal
        //Anima: 5
        //5
    }
}


interface Animal1 {
    String name = "animal";
    int age = 5;

    public void action(String inrefName);

    default int cacluAge() {
        System.out.println("Anima: " + age);
        return age;
    }

}