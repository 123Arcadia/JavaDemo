package Collection.MapTest;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TreeMap01 {
    @Test
    public void test_TreeMap() {
        TreeMap<User, Integer> treeMap = new TreeMap<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.age.compareTo(o2.age);
            }
        });

        treeMap.put(new User("zcw01", 1), 1);
        treeMap.put(new User("zcw02", 2), 2);
        treeMap.put(new User("zcw05", 5), 5);
        treeMap.put(new User("zcw03", 3), 3);
        System.out.println("treeMap = " + treeMap);
        Set<User> userSet = treeMap.keySet();
        for (User user : userSet) {
            System.out.print(user.toString() + " ");
        }
        System.out.println("-----------------");
        Iterator<User> iterator = treeMap.keySet().iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void test_TreeMap_Value() {
        String s = "zcw111";
        int x = 7, y = 8;
        x = x ^ y; //15
        System.out.println(x);
        y = x ^ y; //7

        System.out.println(y);
        x = x ^ y;
        System.out.println("x = " + x);//x = 8
        System.out.println("y = " + y);//y = 7
    }


    @Test
    public void test() {
        String s = "zcw12";
        StringBuilder builder = new StringBuilder(s);
        System.out.println("builder = " + builder.reverse()); // builder = 11wcz
        StringBuilder reverse = builder.reverse();
        System.out.println("reverse = " + reverse); //  reverse = zcw11
        char[] chars = s.toCharArray();
        int len = chars.length;
        System.out.println(chars[2]);
        System.out.println(chars[len - 2 - 1]);
    }

    @Test
    public void array_asList() {
        int[] arr = {1, 9, 6, 10, 3};
        List<int[]> list = Arrays.asList(arr); // [[I@28f67ac7]
        System.out.println(list);

        String s = "zcw,123,7896";
        List<String> list1 = Arrays.asList(s.split(","));
        System.out.println("list1 = " + list1); // list1 = [zcw, 123, 7896]
        System.out.println("list1[1] = " + list1.get(1)); // list1 = 123

        Integer[] arr1 = {1, 9, 6, 10, 3};
        List<Integer> integerList = Arrays.asList(arr1);// [1, 9, 6, 10, 3]
        System.out.println(integerList); //

        List list2 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        System.out.println("list2 = " + list2); // list2 = [a, b, c]


        Integer[] myArray = {1, 2, 3};
        System.out.println("Arrays.stream(myArray) = " + Arrays.stream(myArray));
        // Arrays.stream(myArray) = java.util.stream.ReferencePipeline$Head@4ec6a292

        List myList = Arrays.stream(myArray).collect(Collectors.toList());
        System.out.println("myList = " + myList); // myList = [1, 2, 3]
        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int[] myArray2 = {1, 2, 3};
        List myList1 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
        System.out.println("myList1 = " + myList1); // myList` = [1, 2, 3]

    }
}

class User {
    String name;
    Integer age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
