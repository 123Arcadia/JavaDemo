
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
            // 反射获取static类型的成员变量（field.get(null)获取static变量，实例成员变量要传入实例对象）
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
     * compareAndSwapInt的更新是安全可靠的（具有原子性）
     */
    public static void useMe(){
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
        //sun.misc.Unsafe@6acbcfc0
        UnsafeUse use = new UnsafeUse();
        System.out.println(use);
        //UnsafeUse[a=0,b=0]
        // unsafe.objectFieldOffset针对实例成员变量，unsafe.staticFieldOffset针对static变量

        try {
            System.out.println("c:"+UnsafeUse.c);
            //c:300
            // 注意：compareAndSwapInt修改静态成员变量的值时,传入类.class即可
            unsafe.compareAndSwapInt(UnsafeUse.class,unsafe.staticFieldOffset(UnsafeUse.class.getDeclaredField("c")),
                    300,444);
            System.out.println("修改后的c:"+UnsafeUse.c);
            //修改后的c:444
            long valueOffset = unsafe.objectFieldOffset(UnsafeUse.class.getDeclaredField("b"));
            System.out.println("b的内存偏移地址："+valueOffset);
            //b的内存偏移地址：16
            System.out.println("b的初始值:"+use.getB());
            //b的初始值:0
            // compareAndSwapInt:第1个参数是实例对象，第2个参数是实例对象的实例成员变量b的内存偏移值，
            // 第3个参数是实例对象的b现在的值，第4个参数是实例对象的b希望update的值，
            // 如果第3个参数==实例对象的b现在的值，才更新b的值为第4个参数
            unsafe.compareAndSwapInt(use,valueOffset,0,177);
            unsafe.compareAndSwapInt(use,valueOffset,177,172);
            // compare比较失败，当前的b是172，不是171，更新失败，b仍为172
            //  CAS更新的方式，不依赖于set方法！！ CAS是底层操作数据的方式
            unsafe.compareAndSwapInt(use,valueOffset,171,11);
            System.out.println("修改后的b:"+use.getB());
            //修改后的b:172
        } catch (NoSuchFieldException e) {
            System.out.println("获取b失败");
        }
    }

    public static void main(String[] args) {
        useMe();
    }
}
