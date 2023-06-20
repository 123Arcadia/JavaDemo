package Collection.MapTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapTest02 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        Map<Integer, List<Integer>> map = new HashMap<>();
        System.out.println("list1 = " + list1);
        map.put(null, list1);
        System.out.println("map = " + map);
        //list1 = [1, 2]
        //map = {null=[1, 2]}

        char[] chars = new char[]{'a', 'b'};
        char[] chars1 = new char[2];
        chars1[1] = 'm';
        System.out.println("chars = " + chars); // chars = [C@7ba4f24f
        System.out.println(chars[0]); // a
        System.out.println(chars[1]); // b
        System.out.printf("%d\n", chars.length); // 2
        String s = "123";
        System.out.println("Integer.valueOf(s, 1) = " + Integer.valueOf(s, 1));


    }

}
