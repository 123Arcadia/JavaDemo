package Collection.MapTest;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapTest02 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        Map<Integer, List<Integer>> map = new HashMap<>();
        System.out.println("list1 = " + list1);
        map.put(null, list1);
        System.out.println("map = " + map);
        //list1 = [1, 2]
        //map = {null=[1, 2]}

        char[] chars = new char[]{'a', 'b'};
        char[] chars1 = new char[2];
        chars1[1] = 'm';
        System.out.println("chars = " + chars); // chars = [C@7ba4f24f
        System.out.println(chars[0]); // a
        System.out.println(chars[1]); // b
        System.out.printf("%d\n", chars.length); // 2
        String s = "123";
        System.out.println("Integer.valueOf(s, 1) = " + Integer.valueOf(s, 1));


    }

    /**
     * 获取map容量
     */
    @Test
    public void getCapacity() {
        try {
            //指定初始容量15来创建一个HashMap
            HashMap m = new HashMap(15);
            //获取HashMap整个类
            Class<?> mapType = m.getClass();
            //获取指定属性，也可以调用getDeclaredFields()方法获取属性数组
            Field threshold = mapType.getDeclaredField("threshold");
            //将目标属性设置为可以访问
            threshold.setAccessible(true);
            //获取指定方法，因为HashMap没有容量这个属性，但是capacity方法会返回容量值
            Method capacity = mapType.getDeclaredMethod("capacity");
            //设置目标方法为可访问
            capacity.setAccessible(true);
            //打印刚初始化的HashMap的容量、阈值和元素数量
            System.out.println("容量：" + capacity.invoke(m) + " 阈值：" + threshold.get(m) + " 元素数量：" + m.size());
            for (int i = 0; i < 17; i++) {
                m.put(i, i);
                //动态监测HashMap的容量、阈值和元素数量
                System.out.println("容量：" + capacity.invoke(m) + " 阈值：" + threshold.get(m) + " 元素数量：" + m.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取map容量
     */
    @Test
    public void hashTest(){
        HashMap<Object,Object> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, "HelloWorld");
            getAttribute(map,i);
        }
    }

    public void getAttribute(Object o,Object k){
        String nameVlues="";
        Class<? extends Object> clazz = o.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            try {
                Field field = fields[i];
                field.setAccessible(true);
                String name = field.getName();

                Object value = field.get(o);
                if("size".equals(name) || "threshold".equals(name))
                    nameVlues += field.getName()+":"+value+",";
                else if("table".equals(name)){
                    Map.Entry[] table = (Map.Entry[]) value;
                    Object bucketIndex = getFuction(o,
                            "indexFor",
                            getFuction(o, "hash", k),table.length);
                    Map.Entry entry=
                            table[Integer.valueOf(bucketIndex.toString())];
                    nameVlues += "table["+bucketIndex+"]:"+entry+",";

                    Class<? extends Map.Entry> claz = entry.getClass();
                    Field next = claz.getDeclaredField("next");
                    next.setAccessible(true);
                    if (next.get(entry) != null)
                        nameVlues += "entry.next:"+next.get(entry)+",";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //获取最后一个逗号的位置
        int lastIndex = nameVlues.lastIndexOf(",");
        //不要最后一个逗号","
        String  result= nameVlues.substring(0,lastIndex);
        System.out.println(result);

    }


    public Object getFuction(Object o,String methodName,Object... k){
        Class<? extends Object> clazz = o.getClass();
        Object invoke = null;

        try {
            Method method = null;
            if("indexFor".equals(methodName)){
                method = clazz.getDeclaredMethod(methodName,int.class,int.class);
            }else if("hash".equals(methodName)){
                method = clazz.getDeclaredMethod(methodName,Object.class);
            }
            method.setAccessible(true);  //解除访问限制
            invoke = method.invoke(o, k);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoke;
    }
}
