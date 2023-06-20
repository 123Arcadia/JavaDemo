package Collection;

class Father {
    public int f = 1;

    public void sum(){
        System.out.println("(f + 1000) = " + (f + 1000));
    }
}


class Son extends Father {
    public int son = 10;
    public String s = "son";
    @Override
    public void sum() {
        System.out.println("son+100 = " + (son + 100));
    }

}

public class Demo2 {
    public static void main(String[] args) {
        Father father = new Son();
        /**
         * 先调用子类
         */
        father.sum();
        //(f + 100) = 110
        System.out.println("father = " + father.f);
        //father = 1
        Son son = (Son) father;
        System.out.println("son.f = " + son.f);
        //son.f = 1
        son.sum();
        //son+100 = 110

    }
}
