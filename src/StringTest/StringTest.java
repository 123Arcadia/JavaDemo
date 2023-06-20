package StringTest;

import org.junit.Test;

public class StringTest {
    public static void main(String[] args) {
        /**
         * s1,s2,s4是可以== 都是true
         */
        String s1  = new String("zcw");
        String s2  = new String("zcw");
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
}
