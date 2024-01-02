package bytes;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ByteToint {
    @Test
    public void test_onint() {
        byte[] arr = new byte[4];
//        arr[0] = 0; // 低八位: 00
//        arr[1] = 1; // 01
//        arr[2] = 2; // 10
//        arr[3] = 3; // 高八位: 11
        // res = 50462976
        //11000000, 10000000, 01000000, 00

        arr[0] = 1; // 01
        arr[1] = 3; // 11
        arr[2] = 0;
        arr[3] = 0;
        // 结果就是: 00000000, 00000000, 11000000, 00000001,
        //res = 769
        //11, 00000001
        int res = byteToint(arr, 0);
        System.out.println("res = " + res); // 0
        String toBinaryString = Integer.toBinaryString(res);
        int len = toBinaryString.length();
        System.out.println(toBinaryString);
    }

    /**
     * 这个函数将一个字节数组和一个偏移量作为输入。字节数组是由多个字节组成的数组，
     * 偏移量表示从数组开始位置算起的字节数组中某个特定的位置。该函数将这四个字节解释为一个32位的整数。
     * 它使用位运算符将每个字节正确地组合为一个整数值。具体步骤如下：
     * 1. 将字节数组中偏移量位置的字节与0xFF进行按位与操作，得到一个无符号的字节值。
     * 2. 将字节数组中偏移量+1位置的字节左移8位，然后与0xFF00进行按位与操作，得到一个无符号的字节值。
     * 3. 将字节数组中偏移量+2位置的字节左移16位，然后与0xFF0000进行按位与操作，得到一个无符号的字节值。
     * 4. 将字节数组中偏移量+3位置的字节左移24位，然后与0xFF000000进行按位与操作，得到一个无符号的字节值。 5. 将上述四个无符号字节值按位或操作，得到一个32位的整数值。 6. 将该整数值强制转换为int类型，并将其赋给变量value。 7. 返回变量value作为函数的结果。
     * @param ary byte[] 从该中选择以offset起始的4个元素，组成一个int数值， 对用int的4个8位字节
     * @param offset
     * @return
     */
    public int byteToint(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset] & 0xFF) | ((ary[offset + 1] << 8) & 0xFF00) | ((ary[offset + 2] << 16) & 0xFF0000)
                | ((ary[offset + 3] << 24) & 0xFF000000));
        return value;

    }

    @Test
    public void intByteSize() {
        int x = 3;
        System.out.println(Integer.bitCount(x));
        System.out.println(Byte.BYTES); // 1
        System.out.println(Integer.BYTES); // 4
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        //1111111111111111111111111111111
        //10000000000000000000000000000000

        List<stu> list = new ArrayList<>();
        list.add(new stu(19, "01"));
        list.add(new stu(10, "03"));
        list.add(new stu(22, "02"));
        System.out.println("list = " + list);
        //list = [ByteToint.stu(age=19, name=01), ByteToint.stu(age=10, name=03), ByteToint.stu(age=22, name=02)]
        List<Integer> res = list.stream().map(stu::getAge).collect(Collectors.toList());
        List<Integer> res_01 = list.stream().map(s -> s.getAge()).collect(Collectors.toList());
        List<Integer> res1 = list.stream().mapToInt(stu::getAge).boxed().collect(Collectors.toList());
        int[] array = list.stream().mapToInt(stu::getAge).toArray();
        System.out.println("res = " + res);
        System.out.println("res_01 = " + res_01);
        System.out.println("res1 = " + res1);
        //res = [19, 10, 22]
        //res1 = [19, 10, 22]
        System.out.println(Arrays.toString(array));
        System.out.println("=========");
        List<List<Integer>> list1 = new ArrayList<>();
        List<Integer> son1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> son2 = new ArrayList<>(Arrays.asList(5, 6, 7));
        list1.add(son1);
        list1.add(son2);
        System.out.println("list1 = " + list1);
        //list1 = [[1, 2, 3], [5, 6, 7]]
        list1.add(new ArrayList<>(son1));
        System.out.println("list1 = " + list1);
        //list1 = [[1, 2, 3], [5, 6, 7], [1, 2, 3]]
        System.out.println(new ArrayList<>(list1));
        //[[1, 2, 3], [5, 6, 7], [1, 2, 3]]

        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        List<String> mapList = new ArrayList<>(map.keySet());
        String c = mapList.get(mapList.size() - 1);
//        while (map.containsKey(c)) {
//            int num = map.get(c);
//            son1.add(num);
//        }
        System.out.println("mapList = " + mapList);
        System.out.println("son1 = " + son1);
        System.out.println(Integer.valueOf("04"));
        int a = Integer.MAX_VALUE;
        int b = (int) Math.pow(10, 9);
        System.out.println("a = " + a);
        //a = 2147483647
        System.out.println("b = " + b);
        //b = 1000000000
        System.out.println("a > b = " + (a > b)); // true
    }

    @Data
    @AllArgsConstructor
    class stu {
        int age;
        String name;
    }

}
