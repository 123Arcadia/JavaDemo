package ClothFactory;

interface ClothFactory{
    void produceCloth();
}

//代理类
class PersonTest implements ClothFactory{
    private ClothFactory factory;

    public PersonTest(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("造纸厂开始做一些准备工作");

        factory.produceCloth();

        System.out.println("造纸厂做一些后续收尾工作");
    }
}

//被代理类
class NeckTest implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("造纸厂计划生产一批卫生纸");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类的对象
        ClothFactory word = new NeckTest();

        //创建代理类的对象
        ClothFactory proxy = new PersonTest(word);
        proxy.produceCloth();
    }
}
