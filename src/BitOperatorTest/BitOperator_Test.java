package BitOperatorTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BitOperator_Test {
    /**
     * 位运算
     */
    @Test
    public void BitOperator() {
        int x = 2;
        System.out.println("Integer.toBinaryString(x) = " + Integer.toBinaryString(x)); // 1 0
        System.out.println(~2); // -3
        System.out.println(1 >> 2); // 0
        System.out.println(1 >>> 2); // 0
        System.out.println(1 << 2); // 4

        /**
         * (>>算术右移): 若符号为正，则在高位插入0；若符号为负，则在高位插入1
         */
        int a = (-1) >> 2;
        int b = (-1) >>> 2;

        System.out.println(a); // -1
        System.out.println(Integer.toBinaryString(a)); // 11111111 11111111 11111111 11111111
        System.out.println(b); // 1073741823
        System.out.println(Integer.toBinaryString(b)); // 11111111 11111111 11111111 111111
        int c = -20;
        System.out.println(Integer.toBinaryString(c)); // 11111111 11111111 11111111 11101100
        int d = c >> 2;
        System.out.println("d = " + d); // d = -5
        System.out.println(Integer.toBinaryString(d)); // 11111111 11111111 11111111 11111011
    }

    @Test
    public void equals_test() {
        int x = 1;
        int y = 1;
        System.out.println(x == y); //true
        System.out.println(Objects.equals(x, y)); // true

        int[] x1 = new int[]{128};
        int[] y1 = new int[]{128};
        System.out.println("x1 = " + x1 + ", y1 = " + y1); // x = 1, y = 1
        System.out.println(x1 == y1); // false
        System.out.println(Objects.equals(x1, y1)); // false

        int[] x2 = new int[]{12};
        int[] y2 = new int[]{12};
        System.out.println("x2 = " + x2 + ", y2 = " + y2); // x = 1, y = 1
        System.out.println(x2 == y2); // false
        System.out.println(Objects.equals(x2, y2)); // false

        Integer xi = new Integer(12);
        Integer yi = new Integer(12);
        System.out.println("xi = " + xi + ", yi = " + yi);
        System.out.println(xi == yi); // false
        System.out.println(Objects.equals(xi, yi)); // true

        Integer xii = 12;
        Integer yii = 12;
        System.out.println("xii = " + xii + ", yii = " + yii);
        System.out.println(xii == yii); // true
        System.out.println(Objects.equals(xii, yii)); // true

        /**
         * -128 ~ 127
         */
        Integer xiii = 128;
        Integer yiii = 128;
        System.out.println("xiii = " + xiii + ", yiii = " + yiii);
        System.out.println("xiii.HasCode(): " + xiii.hashCode() + ", yiii.HasCode(): " + yiii.hashCode());
        // xiii.HasCode(): 128, yiii.HasCode(): 128
        System.out.println(xiii == yiii); // false
        System.out.println(Objects.equals(xiii, yiii)); // true
        System.out.println(xiii.equals(yiii)); // true

    }

    /**
     * 进制转换
     */
    @Test
    public void BinaryOctHex() {
        System.out.println(0x7fffffff); // 2147483647
        System.out.println(Integer.toBinaryString(Integer.parseInt("2147483647"))); // 01111111  11111111  11111111  11111111
//        0x7FFFFFFF 就表示 是一个十六进制的int的最大值

        /**
         * radix: 进制
         */
        System.out.println("Integer.parseInt(\"zcw\", 1) = " + Integer.parseInt("5", 10));

        int i = Integer.parseInt("7fffffff", 16);
        System.out.println("i = " + i); // i = 2147483647
    }

    @Test
    public void Mpatest() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 11);
        map.put(1, 22);
        map.put(2, 31);
        map.put(null, 11);
        System.out.println("map = " + map);
        if (!map.containsKey(null)) {
            System.out.println("Map没有33");
        } else {
            System.out.println("map");
        }

        int x = 4;
        int y = x;
        x = 5;
        System.out.println("x = " + x); // 5
        System.out.println("y = " + y); // 4

    }


}
