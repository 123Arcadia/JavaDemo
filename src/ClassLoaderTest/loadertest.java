package ClassLoaderTest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
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
        //jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b
        //jdk.internal.loader.ClassLoaders$PlatformClassLoader@28f67ac7
        //null
        //null
    }


    /**
     * 读取 properties
     */
    @Test
    public void test2() throws Exception {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(loadertest.class.getClassLoader().getResource(""));
        System.out.println(loadertest.class.getClassLoader().getResourceAsStream("").toString());
        //D:\javaProject\javaTesting
        //file:/D:/javaProject/javaTesting/out/production/javaTesting/
        //java.io.ByteArrayInputStream@67b64c45


        Properties pros = new Properties();
        //此时的文件默认在当前的module下。
        //读取配置文件的方式一：
//        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/ClassLoaderTest"+"/test.properties");
//        pros.load(fis);

        //读取配置文件的方式二：使用ClassLoader
        //配置文件默认识别为：当前module的src下
        ClassLoader classLoader = loadertest.class.getClassLoader();
        /**
         * 要创建properties文件
         */
        InputStream is = classLoader.getResourceAsStream("ClassLoaderTest"+"/test.properties");
        pros.load(is);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ",password = " + password);
        //user = zcw,password = zcw123
    }

    /**
     *	- ClassLoader来说：不能使用"/"开头，默认从ClassPath类路径下获取
     * 	- class来说：
     * 	        path不以’/‘开头时，默认是指所在类的相对路径，从这个相对路径下取资源；
     *          path以’/'开头时，则是从项目的ClassPath根下获取资源，就是要写相对于classpath类路径下的绝对路径
     */
    @Test
    public void testgetResource() {
        System.out.println(System.getProperty("user.dir"));
        //D:\javaProject\javaTesting
        System.out.println(loadertest.class.getResource(""));
        //file:/D:/javaProject/javaTesting/out/production/javaTesting/ClassLoaderTest/
        /**
         * /: 表示从根目录下
         * "": 表示从该类下
         */
        System.out.println(loadertest.class.getResource("/"));
        //file:/D:/javaProject/javaTesting/out/production/javaTesting/
        System.out.println(loadertest.class.getResource("").getFile());
        ///D:/javaProject/javaTesting/out/production/javaTesting/ClassLoaderTest/
        System.out.println(loadertest.class.getResourceAsStream(""));
        //java.io.ByteArrayInputStream@67b64c45
        System.out.println(loadertest.class.getResourceAsStream("/"));
        //java.io.ByteArrayInputStream@4411d970

        Properties pros = new Properties();
        String file = loadertest.class.getResource("/ClassLoaderTest/test.properties").getFile();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            pros.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ",password = " + password);
    }


    @Test
    public void test01() throws ClassNotFoundException {
        // 根加载加载的路径
//        System.out.println(System.getProperty("sun.boot.class.path"));
//        // 扩展加载器
//        System.out.println(System.getProperty("java.ext.dirs"));
//
//        Class<?> aClass = Class.forName(String.valueOf(loadertest.class));
//        System.out.println(aClass.getClassLoader()); // AppClassLoader
//        System.out.println(aClass.getClassLoader().getParent()); // ExtClassLoader
//        System.out.println(aClass.getClassLoader().getParent().getParent());

        Properties properties = System.getProperties();
        System.out.println("System.getenv() = " + System.getenv());

        ClassLoader classLoader = loadertest.class.getClassLoader();
        System.out.println("classLoader = " + classLoader);
        //classLoader = jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b
        ClassLoader pare1 = classLoader.getParent();
        System.out.println("pare1 = " + pare1);
        //pare1 = jdk.internal.loader.ClassLoaders$PlatformClassLoader@c818063
        ClassLoader pare2 = pare1.getParent();
        System.out.println("pare2 = " + pare2);
        //pare2 = null
        ClassLoader pare3 = pare2.getParent();
        System.out.println("pare3 = " + pare3); // null

    }

}


