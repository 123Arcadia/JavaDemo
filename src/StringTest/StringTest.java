package StringTest;

import org.junit.Test;

import java.net.Inet4Address;
import java.net.UnknownHostException;

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
}
