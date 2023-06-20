package ClothFactory;

interface ClothFactory{
    void produceCloth();
}

//������
class PersonTest implements ClothFactory{
    private ClothFactory factory;

    public PersonTest(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("��ֽ����ʼ��һЩ׼������");

        factory.produceCloth();

        System.out.println("��ֽ����һЩ������β����");
    }
}

//��������
class NeckTest implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("��ֽ���ƻ�����һ������ֽ");
    }
}

public class StaticProxyTest {
    public static void main(String[] args) {
        //������������Ķ���
        ClothFactory word = new NeckTest();

        //����������Ķ���
        ClothFactory proxy = new PersonTest(word);
        proxy.produceCloth();
    }
}
