
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiaoxu
 * @date 2022-09-29
 * spring_boot:com.xiaoxu.lock.UnsafeUse
 */
public class UnsafeUse {
    private int a;
    private int b;
    private static final int c;
    static {
        c = 300;
    }

    public int getA() {
        return a;
    }

//    public void setA(int a) {
//        this.a = a;
//    }

    public int getB() {
        return b;
    }

//    public void setB(int b) {
//        this.b = b;
//    }

    public static Unsafe getUnsafe(){
        Unsafe unsafe = null;
        try{
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            // �����ȡstatic���͵ĳ�Ա������field.get(null)��ȡstatic������ʵ����Ա����Ҫ����ʵ������
            unsafe = (Unsafe) theUnsafeField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return unsafe;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * compareAndSwapInt�ĸ����ǰ�ȫ�ɿ��ģ�����ԭ���ԣ�
     */
    public static void useMe(){
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
        //sun.misc.Unsafe@6acbcfc0
        UnsafeUse use = new UnsafeUse();
        System.out.println(use);
        //UnsafeUse[a=0,b=0]
        // unsafe.objectFieldOffset���ʵ����Ա������unsafe.staticFieldOffset���static����

        try {
            System.out.println("c:"+UnsafeUse.c);
            //c:300
            // ע�⣺compareAndSwapInt�޸ľ�̬��Ա������ֵʱ,������.class����
            unsafe.compareAndSwapInt(UnsafeUse.class,unsafe.staticFieldOffset(UnsafeUse.class.getDeclaredField("c")),
                    300,444);
            System.out.println("�޸ĺ��c:"+UnsafeUse.c);
            //�޸ĺ��c:444
            long valueOffset = unsafe.objectFieldOffset(UnsafeUse.class.getDeclaredField("b"));
            System.out.println("b���ڴ�ƫ�Ƶ�ַ��"+valueOffset);
            //b���ڴ�ƫ�Ƶ�ַ��16
            System.out.println("b�ĳ�ʼֵ:"+use.getB());
            //b�ĳ�ʼֵ:0
            // compareAndSwapInt:��1��������ʵ�����󣬵�2��������ʵ�������ʵ����Ա����b���ڴ�ƫ��ֵ��
            // ��3��������ʵ�������b���ڵ�ֵ����4��������ʵ�������bϣ��update��ֵ��
            // �����3������==ʵ�������b���ڵ�ֵ���Ÿ���b��ֵΪ��4������
            unsafe.compareAndSwapInt(use,valueOffset,0,177);
            unsafe.compareAndSwapInt(use,valueOffset,177,172);
            // compare�Ƚ�ʧ�ܣ���ǰ��b��172������171������ʧ�ܣ�b��Ϊ172
            //  CAS���µķ�ʽ����������set�������� CAS�ǵײ�������ݵķ�ʽ
            unsafe.compareAndSwapInt(use,valueOffset,171,11);
            System.out.println("�޸ĺ��b:"+use.getB());
            //�޸ĺ��b:172
        } catch (NoSuchFieldException e) {
            System.out.println("��ȡbʧ��");
        }
    }

    public static void main(String[] args) {
        useMe();
    }
}
