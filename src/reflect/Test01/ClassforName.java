package reflect.Test01;

import org.junit.Test;

import java.util.*;

public class ClassforName {




    @Test
    public void sum() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 4));
        System.out.println(list.stream().mapToInt(s -> s).reduce(Integer::sum));
        System.out.println(list.stream().mapToInt(s -> s).reduce(1, Integer::sum));
    }

    @Test
    public void compare() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,6,3,4,2));
        //如果y > x返回1，就交换
        Collections.sort(list, (y, x) -> y - x);
        Collections.sort(list, (x, y) -> y - x);
        System.out.println("list = " + list);
    }

}
