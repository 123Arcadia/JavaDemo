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

        String classPath = System.getProperty("java.class.path");
        System.out.println("classPath = " + classPath);
        //classPath = D:\javaIDEA\IntelliJ IDEA 2022.2.1\lib\idea_rt.jar;D:\javaIDEA\IntelliJ IDEA 2022.2.1\plugins\junit\lib\junit5-rt.jar;D:\javaIDEA\IntelliJ IDEA 2022.2.1\plugins\junit\lib\junit-rt.jar;D:\javaProject\javaTesting\out\production\javaTesting;D:\javaProject\javaTesting\lib\junit-4.13.1.jar;D:\javaProject\javaTesting\lib\hamcrest-core-1.3.jar;D:\javaProject\javaTesting\lib\lombok-1.18.22.jar;D:\CSProject\maven-Repo\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar
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


}


