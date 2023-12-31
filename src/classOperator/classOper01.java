package classOperator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class classOper01 {

    @Test
    public void classTest() {
        ArrayList<Integer> list = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.add(4);
        list.add(5);
        System.out.println("list = " + list);
        System.out.println(list.size());

        System.out.println(ArrayList.class.getTypeName());
        //java.util.ArrayList
        System.out.println(ArrayList.class.getSimpleName());
        //ArrayList
        try {
            Field elementData = ArrayList.class.getDeclaredField("elementData");
            System.out.println(elementData);
            // transient java.lang.Object[] java.util.ArrayList.elementData
            elementData.setAccessible(true);
            int length = ((Object[]) elementData.get(list)).length;
            System.out.println("list容量:" + length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        //list = [0, 1, 2, 4]
        //4
        //java.util.ArrayList
        //ArrayList
        //transient java.lang.Object[] java.util.ArrayList.elementData
        //list瀹归噺:4
        //------------
        //list瀹归噺:19
        //15
    }

    @Test
    public  void getClassName() throws ClassNotFoundException {
        System.out.println(Student.class.getSimpleName());
        //Student
        System.out.println(Student.class.getName());
        //classOperator.Student
        System.out.println(Student.class.getClassLoader().getName());
        // app
        System.out.println(Student.class.getCanonicalName());
        //classOperator.Student
        System.out.println(Student.builder().age("16").name("zcw01").build());
        //Student(name=zcw01, age=16)
        Class<?> aClass = Class.forName(Student.class.getName());
        for (Field f : aClass.getDeclaredFields()) {

            if (f.getGenericType().toString().contains("String")) {
                System.out.println(f.toString()+", " + f.toString().length());
            }
        }
        //java.lang.String classOperator.Student.name, 43
        //java.lang.String classOperator.Student.age, 42

        System.out.println(Student.class.getPackage().getName());
        // 包名: classOperator
        System.out.println(Student.class.getPackage());
        // 包名: package classOperator

    }

    public int getMsgLength(String className){
        int length = 0;
        try {
            Class<?> c = Class.forName(className);

            for (Field f:c.getSuperclass().getDeclaredFields()) {
                length = length+getFieldLength(f);
            }
            for (Field f : c.getDeclaredFields()) {
                length = length+getFieldLength(f);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 获取基础类型数据字符长度
     * @param f
     * @return
     * @throws Exception
     */
    private int getFieldLength(Field f) throws Exception {
        f.setAccessible(true);
        if (f.getGenericType().toString().contains("int")) {
            return 4;
        } else if (f.getGenericType().toString().contains("float")) {
            return 4;
        } else if (f.getGenericType().toString().contains("long")) {
            return 8;
        } else if (f.getGenericType().toString().contains("short")) {
            return 2;
        } else if (f.getGenericType().toString().contains("byte")) {
            return 1;
        } else if (f.getGenericType().toString().contains("double")) {
            return 8;
        } else if (f.getGenericType().toString().contains("String")) {
            return f.toString().length();
        } else {
            return 0;
        }
    }

    
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Student {
    String name;
    String age;

}
