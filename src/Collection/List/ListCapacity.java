package Collection.List;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ListCapacity {

    /**
     * 非法反射警告: Unable to make field transient java.lang.Object[] java.util.ArrayList.elementData accessible:
     * 解决: 在Vm 参数添加：
     * --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED
     */
    @Test
    public void get_ListCapacity() {
        //创建一个存储字符串的集合，
        ArrayList<String> list = new ArrayList<>(10);

        int rongliang = 0;
        // 存储16个字符串元素，
        list.add("张飞");
        list.add("刘备");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        try {
            rongliang = getArrayListLength(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("capa = " + rongliang + ", size = " + list.size());
        // capa = 10, size = 10
        // 扩容其原容量的一半(即1.5倍)
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        list.add("关羽");
        try {
            rongliang = getArrayListLength(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("capa = " + rongliang + ", size = " + list.size());
        // capa = 22, size = 16

        System.out.println(3<<1); // 011 - > 110(6)

        //t=0,x=0,mask=3, t=1,x=0
        //t=1,x=5,mask=3, t=1,x=5
        //t=1,x=6,mask=3, t=1,x=6
        //t=1,x=4,mask=3, t=1,x=4
        //t=0,x=0,mask=5, t=1,x=0
        //t=1,x=5,mask=5, t=2,x=5
        //t=2,x=6,mask=5, t=2,x=6
        System.out.println("-----------");
        System.out.println(5&3);
        System.out.println(6&3);
        System.out.println(4&3);
        System.out.println(0&5);
        System.out.println(5&5);
        System.out.println(6&5);
        //1
        //2
        //0
        //0
        //5
        //4

    }

    public static int getArrayListLength(ArrayList<String> list) throws Exception {
        //获取class对象
        Class<?> arraylist = Class.forName("java.util.ArrayList");
        //获取属性elementData数组
        Field elementData = arraylist.getDeclaredField("elementData");
        //设置访问状态为true
        elementData.setAccessible(true);
        //获取指定对象此Filed的值
        Object[] objects = (Object[]) elementData.get(list);
        return objects.length;
    }
}
