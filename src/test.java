import org.junit.Test;

import java.io.Console;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) { // 1 2 3 4
        int i = 0, sum = 0;
        while (i < args.length && args[i] != null) {
            sum += Integer.parseInt(args[i++]);
        }
        System.out.println("sum = " + sum);
        System.out.println("args[0] = " + args[0]);
    }

    @Test
    public void test01() {
        String s = "zcw";
        char t = s.charAt(1);
        System.out.println("t = " + t); // c
        System.out.println("s.codePointAt(1) = " + s.codePointAt(1)); // 99
        System.out.println("s.indexOf(\"c\", 1) = " + s.indexOf("c", 2));// -1
        System.out.println("s.indexOf(\"w\", 1) = " + s.indexOf("w", 1));// 2
        
        String str = "zcwzcwzcw";
        System.out.println("str.toUpperCase() = " + str.toUpperCase());
    }

    @Test
    public void test() {
        String s = null;
//        if (s.equals(null)){
//            System.out.println("null");
//        } else if (s == null) {
//            System.out.println("s == null");
//        } else{}
//        if (s == null) {
//            System.out.println("null");
//        }
        String a = new String();
//        String a = "";
        if (a==null) {
            System.out.println("null");
        } else {
            System.out.println("jjj"); // jjj
        }
    }

    @Test
    public void testConsole() {
        Console console = System.console();
        System.out.println("输入第1个:");
        console.readLine();
        System.out.println("输入第2个:");
        console.readLine();
        System.out.println("输入第3个:");
        console.readLine();
    }

    @Test
    public void test04() {
        Date date = new Date();
//        System.out.println("%c", new Date());
        System.out.println("date = " + date); // date = Mon Dec 12 00:41:19 CST 2022
    }

    @Test
    public void test05() {
        System.out.println("Paths.get(\"helloin01.txt\") = " + Paths.get("helloin01.txt"));
    }

    @Test
    public void test06() {
        BigInteger num = BigInteger.valueOf(100);
        BigInteger num1 = BigInteger.valueOf(100);

        BigInteger addNum = num.add(num1);
        System.out.println("num = " + num);
        System.out.println("addNum = " + addNum);
        System.out.println("num Class = " + num.getClass());
//        num = 100
//        addNum = 200
//        num Class = class java.math.BigInteger
    }

    @Test
    public void test07() {
        Map<Integer, String> l = new HashMap<>();
        l.put(1, "111");
        l.put(2, "222");
        l.put(3, "333");
        System.out.println("l = " + l);
        l.remove(2);
        System.out.println("l = " + l);
        System.out.println("l.get(2) = " + l.get(2));
    }




}
