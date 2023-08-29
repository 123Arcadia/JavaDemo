package ClassLoaderTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

public class loadertest {

    /**
     * 了解类的加载器
     */
    @Test
    public void test1() {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = loadertest.class.getClassLoader();
        System.out.println(classLoader);

        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);

        //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);
    }


    /**
     * 读取 properties
     */
    @Test
    public void test2() throws Exception {
        Properties pros = new Properties();
        //此时的文件默认在当前的module下。
        //读取配置文件的方式一：
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        pros.load(fis);
        //读取配置文件的方式二：使用ClassLoader
        //配置文件默认识别为：当前module的src下
        ClassLoader classLoader = loadertest.class.getClassLoader();
        /**
         * 要创建properties文件
         */
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(is);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ",password = " + password);
    }

    @Test
    public void css() {
        List<Integer> list = new ArrayList<Integer>() {{
            add(2);
            add(4);
            add(4);
            add(8);
        }};
        System.out.println("list = " + list);
        System.out.println(list.indexOf(4)); // 1
        System.out.println(list.lastIndexOf(4)); // 2

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        System.out.println("list1 = " + list1);

        System.out.println(Math.ceil((double) 72 / 22));

        int o = 0;
        for (int i = 1; i <= 91; i++) {
            if (i % 2 != 1) {
                o ++;
            }
        }
        System.out.println("o = " + o);
    }

    @Test
    public void testStu() {
        stu zcw1 = new stu("zcw1",16 );
        stu zcw2 = new stu("zcw2", 15);
        List<stu> list =new ArrayList<>();
        list.add(zcw1);
        list.add(zcw2);
        System.out.println(list);
//        System.out.println(zcw1.compareTo(zcw2));

        Collections.sort(list, (o1, o2) -> o1.age.compareTo(o2.age));
        System.out.println(list);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class stu{
        private String name;
        private Integer age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            stu stu = (stu) o;

            if (!name.equals(stu.name)) return false;
            return age.equals(stu.age);
        }


        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age.hashCode();
            return result;
        }

//        @Override
//        public int compareTo(stu o) {
//            if (this.age < o.age) {
//                return -1;
//            } else if (this.age > o.age) {
//                return 1;
//            }
//            return 0;
//        }
    }




}


