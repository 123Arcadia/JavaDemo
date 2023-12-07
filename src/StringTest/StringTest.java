package StringTest;

import org.junit.Test;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

public class StringTest {
    public static void main(String[] args) {
        /**
         * s1,s2,s4是可以== 都是true
         */
        String s1 = new String("zcw");
        String s2 = new String("zcw");
        String s3 = "zcw";
        String s4 = String.valueOf("zcw");
        System.out.println(s1 == s2);  //false
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1.equals(s3)); //true
        System.out.println(s4 == s3); // true
        System.out.println("s1.hashCode() = " + s1.hashCode());
        System.out.println("s2.hashCode() = " + s2.hashCode());
        System.out.println("s3.hashCode() = " + s3.hashCode());
        System.out.println("s4.hashCode() = " + s4.hashCode());
        //s1.hashCode() = 120430
        //s2.hashCode() = 120430
        //s3.hashCode() = 120430
        //s4.hashCode() = 120430
        System.out.println("TestArrSum(\"zcw\", \"my\", \"name\") = " + TestArrSum("zcw", "my", "name"));
        //TestArrSum("zcw", "my", "name") = zcwmyname
    }

    public static String TestArrSum(String... s) {
        String sum = "";
        for (int i = 0; i < s.length; i++) {
            sum += s[i];
        }
        return sum;
    }

    @Test
    public void NullAndEquals() {
        String s = "";
        String s1 = null;
        System.out.println("".equals(s)); // true
        System.out.println("".equals(s1)); // false
        System.out.println(s.equals(null)); // false
//        System.out.println(s1.equals(null)); // 报错

        System.out.println("-----------------");
        String str1 = "zcw";
        String str2 = "zcw";
        String str3 = str2;
        String str4 = new String("zcw");
        String str5 = String.valueOf("zcw");
        System.out.println(str1 == str2);
        //true
        System.out.println(str2 == str3);
        //true
        System.out.println(str1 == str4);
        // false
        System.out.println(str4 == str5);
        //false
        System.out.println(str4.equals(str5));
        //true
        System.out.println(str4 == str1);
        //false
        str2 = "ZCW";
        System.out.println(str2);
        System.out.println(str3);
        // zcw (str2和str3地址不同?)
        System.out.println(str1 == str2);
        //false
        System.out.println(str2 == str3);
        //false
    }

    @Test
    public void test_() {
        int num = 692;
        int suit = num / 22; // 9
        System.out.println(31/8); // 3
        for (int i = 1; i < 1 + suit; i++) {

        }
        System.out.println(suit);
    }


    @Test
    public void test_slpit() {
        String str = "A hormone regulation–based approach for distributed and online scheduling of machines and automated guided vehicles.pdf";
        String res = str.replace(" ", "_");
        System.out.println("res = " + res);
        //res = A_hormone_regulation-based_approach_for_distributed_and_online_scheduling_of_machines_and_automated_guided_vehicles.pdf
        String str1 = "[][][K03]";
//        String str1 = "[][][]";
        String str1Val = str1.substring(1, str1.length() - 1);
        String[] str1ValRes = str1Val.split("]\\[");
        System.out.println("str1ValRes = " + Arrays.toString(str1ValRes));
        System.out.println(str1ValRes == null); // false
        System.out.println(str1ValRes.length == 0); // true
        str1ValRes = new String[]{"","",""};
        System.out.println("".equals(str1ValRes[1]));
        System.out.println("str1ValRes[1] = "+str1ValRes[1]);
    }

    @Test
    public void test_sub() throws UnknownHostException {
        String pos = "H05069";
        System.out.println("pos.substring(3,6) = " + pos.substring(3, 6));
        // parseInt 解析会去掉前导0
        String position = pos.substring(0, 3) + "_" + Integer.parseInt(pos.substring(3, 6));
        System.out.println("position = " + position);
        //res = A_hormone_regulation-based_approach_for_distributed_and_online_scheduling_of_machines_and_automated_guided_vehicles.pdf

        System.out.println(Inet4Address.getLocalHost().getHostAddress());
    }

    @Test
    public void testSuppress() {
        Integer num = 10;
        int res = uncheck(num);
        System.out.println("res = " + res);
    }

    @SuppressWarnings("unchecked")
    private int uncheck(Integer num) {
        return num / 0;
    }

    // 比较两个字符串的汉明距离
    @Test
    public void test_hammingDistance() {
        String s1 = "abc";
        String s2 = "abd";
        System.out.println(calculateDistance("zcw", "zcc")); // 1
        StringBuilder sb = new StringBuilder().append("device Update error:");
        System.out.println(sb + " "+sb.length()); // 20
        List<Integer> list = null;
        if (true) {
            list = new ArrayList<>();
        }
        list.add(1);
        System.out.println("list = " + list);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt());
        }
        System.out.println("list = " + list);
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            newList.add(list.get(i));
        }
        System.out.println("newList = " + newList);

        List<Integer> list1 = null;
        try {
            list1.remove(1);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage() + ", " + e.getMessage());
        }

    }

    public static int calculateDistance(String str1, String str2) {
        int distance = 0;
        int str1Length = Math.min(str1.length(), str2.length());
        for (int i = 0; i < str1Length; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }


    @Test
    public void getLoc() {
        String[] cntrs = {"K060100801", "C050080402", "C070070302"};
        for (String cntr : cntrs) {
            System.out.println(cntr.substring(0, 3) + "_" + Integer.valueOf(cntr.substring(3, 6)) + "_" + cntr.substring(6));
        }
        //K06_10_0801
        //C05_8_0402
        //C07_7_0302
        Map<String, List<String>> map = new HashMap<>();
        map.put("zcw", new ArrayList<String>(Arrays.asList("1", "2", "3")));
        List<String> list1 = map.get("zcw");
        list1.remove("1");
        System.out.println("list1 = " + list1);
    }


}




