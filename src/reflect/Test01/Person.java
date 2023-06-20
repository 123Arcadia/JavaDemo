package reflect.Test01;

public class Person {
    private String name;
    public int age  = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show(){
        System.out.println("[show()]你好，我是?");
    }

    private String showNation(String nation){
        System.out.println("[showNation]喷子实在太多了！！！" + nation);
        return nation;
    }
}
