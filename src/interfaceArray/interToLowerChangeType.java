package interfaceArray;


class Animal extends Object {

    public void move() {
        System.out.println("�������ƶ�");
    }
}


class Bird extends Animal {
    @Override
    public void move() {
        System.out.println("����ڷ���");
    }

    public void fly() {
        System.out.println("Bird fly");
    }

}
class Cat extends Animal {

    //��д�����м̳еķ���
//    public void move() {
//        System.out.println("è����è��");
//    }


    @Override
    public void move() {
        System.out.println("è����è��");
    }

    public void catchMouse() {
        System.out.println("èץ����");
    }
}

/**
 * ����ת��[downcasting]:
 */
public class interToLowerChangeType {

    public static void main(String[] args) {
        Animal a2 = new Cat();//����ת��
        a2.move();
        Cat c2 = (Cat) a2; // ����ר��: ��������Cat����s
        c2.catchMouse();

        // Ϊʲô�������б���
        try {
            Animal a3 = new Bird();
            Cat c3 = (Cat) a3;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ��ΪJVM���ڴ�����ʵ���ڵĶ�����Bird���ͣ�Bird�����޷�ת��ΪCat����Bird��Cat֮�䲻���ڼ̳й�ϵ��������������쳣��Java.lang.ClassCastException(����ת���쳣)

        //����ָ����, ��Ȼ����
        Cat c4 = null;
        try {
            Animal a4 = new Animal();
            c4 = (Cat) a4;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  ��Ϊa4��Animal������������������ͣ���c4ָ������������Cat�����������ָ������󣬸��쳣���ϸ�������ͬ��Java.lang.ClassCastException(����ת���쳣)

        // ����ת�ͽ���� instanceof
        //�����ʽ��
        Animal a3 = new Bird();
        if(a3 instanceof Cat) {
            System.out.println("->");
            Cat c3 = (Cat) a3;
            c3.catchMouse();
        } else if(a3 instanceof Bird) {
            System.out.println("<-");
            Bird b2 = (Bird)a3;
            b2.fly();
            //<-
            //Bird fly
        } else {
            System.out.println("no this class!");
        }

    }

}
