package toUpperLowerChangeType;

class Animal {
    public String name;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Animal eat() {
        System.out.println(name + " ���ڳԶ������óԣ�");
        return null;

    }

}

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);

    }

    public void wangWang() {
        System.out.println(name + " ���������У���������������");
    }

    public Dog eat() {
        System.out.println(name + " ���ڿ�������Ĺ�ͷ��");
        return null;
    }
}

class Bird extends Animal {
    public Bird(String name, int age) {
        super(name, age);
    }

    public void fly() {
        System.out.println(name + " �������ɰ���");
    }

}
public class changeToType {
    public static void main(String[] args) {
        Animal animal2 = new Bird("С��", 10);
//    animal2.fly(); �����޷�����������еķ���
        //����ת��  ���� ����ȫ
        if(animal2 instanceof Bird)
        {
            Bird bird = (Bird) animal2;
            bird.fly();
        }

        Animal animal1 = new Dog("С��", 10);
        if(animal1 instanceof Bird)
        {
            Bird bird2 = (Bird) animal1;
            bird2.fly();
        } else

        {
            System.out.println("���ܷɣ�");
        }
    }
}
