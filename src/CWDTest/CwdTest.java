package CWDTest;

import java.io.File;
import java.util.*;

public class CwdTest {
    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir"));
        System.out.println(file);

        Map<Integer, String> list = new HashMap<>();
        list.put(1, "z");
        list.put(2, "c");
        list.put(3, "w");
        System.out.println("list = " + list);

        list.remove(1);
        System.out.println("list = " + list);

        list.remove("w");
        System.out.println("list = " + list);

        File file1 = new File("user.dir");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getParent());

//        String s = "I love zcw!";
//        for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i));
//        }

        Date date = new Date();
        System.out.println(date);
        String str =  "I love me. My name is zcw!";
        System.out.println(Arrays.toString(str.split("e"))); //[I lov,  m, . My nam,  is zcw!]
        System.out.println(Arrays.toString(str.split(" "))); //[I, love, me., My, name, is, zcw!]

        String[] res = str.split(" ");
        for (int i = 0; i < res.length; i++) {
            System.out.println("res[i] = " + res[i]);
//            res[i] = I
//            res[i] = love
//            res[i] = me.
//                    res[i] = My
//            res[i] = name
//            res[i] = is
//            res[i] = zcw!
        }
        System.out.println("res = " + Arrays.toString(res)); //res = [I, love, me., My, name, is, zcw!]
        System.out.println("=========");
//        Map<String, Integer> m = new TreeMap<>();
//        String[] text = {"sumomo", "mo", "momo", "mo",
//                "momo", "no", "uchi"};
//        for (String s : text) {
//            int currentCount = m.getOrDefault(s, 0);
//            m.put(s, currentCount + 1);
//        }
//        System.out.println("m = " + m);
//        //m = {mo=2, momo=2, no=1, sumomo=1, uchi=1}

        /*
        List<Integer> list1 = List.of(1,2,3);
        System.out.println(list1);
        list1.add(4);  //报错
        System.out.println("list1 = " + list1);
        */

    }


}
