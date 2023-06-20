package EnumTest;

public class EnumPro {
    public static  void main(String[] args) {
        System.out.println(Person.Person1);
        System.out.println(Person.Person2);
        System.out.println(Person.Person3);
        Person person = Person.Person1;
        System.out.println(person.name());
        System.out.println(person.values());

        Person[] values = Person.values();
        for (Person i:values) {
            System.out.print(i + " ");
        }

        System.out.println();
        //单个调用
        Person person01 = Person.Person1;
        System.out.println("person01.toString() = " + person01.toString());
//        {Person : name = jack age = 19}
//        {Person : name = dany age = 20}
//        {Person : name = Candy age = 17}
//        Person1
//                [LEnumTest.Person;@7b23ec81
//        {Person : name = jack age = 19} {Person : name = dany age = 20} {Person : name = Candy age = 17}
//        person01.toString() = {Person : name = jack age = 19}

    }
}

enum Person {
    Person1("jack", 19),
    Person2("dany", 20),
    Person3("Candy", 17);

    private int age;
    private String name;

    private Person( String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{Person : name = " + name + " age = " + age + "}";
    }


}
