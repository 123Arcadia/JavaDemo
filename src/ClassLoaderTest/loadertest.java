package ClassLoaderTest;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
    }


}


