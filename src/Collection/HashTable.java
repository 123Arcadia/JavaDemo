package Collection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class HashTable {
    @Test
    public void test_HashTable() throws IOException {


        FileInputStream file = new FileInputStream("D:\\javaProject\\javaTesting\\src\\Collection\\HasTable.properties");
        Properties pro = new Properties();
        try {
            pro.load(file);

            System.out.println("pro.getProperty(\"name\") = " + pro.getProperty("name"));
            System.out.println("pro.getProperty(\"age\") = " + pro.getProperty("age"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        // pro.getProperty("name") = zcw
        // pro.getProperty("age") = 20

        pro.setProperty("name", "zcwzcw");
        System.out.println("pro.getProperty(\"name\") = " + pro.getProperty("name"));
        // pro.getProperty("name") = zcwzcw

        if (file != null) {
            file.close();
        }
    }

    @Test
    public void test() {
        Map<String ,String> map = new HashMap<>() {{
            put("1", "zzz");
            put("2", "ccc");
            put("3", "222");
        }};
        System.out.println("map = " + map);

        List<String> list = new ArrayList<>(Arrays.asList("1", "3", "4"));
        System.out.println("list = " + list);
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s + "->");
            }
        });
        list.forEach(s -> System.out.println("s = " + s));
        //s = 1
        //s = 3
        //s = 4

        map.put(null, null);

        System.out.println("map = " + map);
        System.out.println("map.get(null) = " + map.get(null).equals("nnn")); //true
        System.out.println("map.get(null) = " + map.get(null).toString()); //true
        //map = {null=null, 1=zzz, 2=ccc, 3=222}
        //map.get(null) = null
//        System.out.println("map.get(\"fff\") = " + fff);

    }

    @Test
    public void test01() {
        Random rand = new Random();
        double[] data = new double[50];
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextDouble() * (0.8 - 0.2) + 0.2;
            System.out.printf("%.5f\n", data[i]);
        }
    }

}
