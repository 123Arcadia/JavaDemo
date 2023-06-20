package Account;

public class Testcat_Ani {
    public static void main(String[] args) {
        Animals animals = new Animals();
        //Cat cat = new Cat();
        //animals.cat();  //不能调用子类的方法,调用成员由编译类型( = 的左边)决定
        Cat cat = (Cat) animals;
        cat.cal();
        //animals.say();
    }

}
