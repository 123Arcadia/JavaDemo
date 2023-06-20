package ClothFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ��̬�������
 */
interface Moon{
    String getBelief();

    void Object(String Moon);
}

//��������
class Venus implements Moon{


    @Override
    public String getBelief() {
        return "The only planet in the solar system without a magnetic field.";
    }

    @Override
    public void Object(String MinMoon) {
        System.out.println("��Χ�кܶ�" + MinMoon);
    }
}

/**
 * Ҫ��ʵ�ֶ�̬������Ҫ��������⣿
 * ����һ����θ��ݼ��ص��ڴ��еı������࣬��̬�Ĵ���һ�������༰�����
 * ���������ͨ��������Ķ�����÷���aʱ����ζ�̬��ȥ���ñ��������е�ͬ������a��
 */
class BookTest{

    //���ô˷���������һ��������Ķ��󡣽������һ
    public static Object getProxyInstance(Object obj){//obj:��������Ķ���
        DeskTest hander = new DeskTest();
        hander.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),hander);
    }
}

class DeskTest implements InvocationHandler{

    private Object obj;//��Ҫʹ�ñ�������Ķ�����и�ֵ

    public void bind(Object obj){
        this.obj = obj;
    }

    //������ͨ��������Ķ��󣬵��÷���aʱ���ͻ��Զ��ĵ������µķ�����invoke()
    //����������Ҫִ�еķ���a�Ĺ��ܾ�������invoke()��
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //method:��Ϊ�����������õķ������˷���Ҳ����Ϊ�˱����������Ҫ���õķ���
        //obj:��������Ķ���
        Object returnValue = method.invoke(obj,args);

        //���������ķ���ֵ����Ϊ��ǰ���е�invoke()�ķ���ֵ��
        return returnValue;
    }
}

public class ProductTest {
    public static void main(String[] args) {
        Venus superMan = new Venus();
        //NumTest:������Ķ���
        Moon NumTest = (Moon) BookTest.getProxyInstance(superMan);
        //��ͨ�������������÷���ʱ�����Զ��ĵ��ñ���������ͬ���ķ���
        String belief = NumTest.getBelief();
        System.out.println(belief);
        NumTest.Object("�Ĵ����ɽ");

        System.out.println("+++++++++++++++++++");

        NeckTest fox = new NeckTest();
        ClothFactory ween = (ClothFactory) BookTest.getProxyInstance(fox);

        ween.produceCloth();
    }
}

