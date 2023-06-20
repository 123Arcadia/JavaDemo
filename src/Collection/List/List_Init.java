package Collection.List;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class List_Init {
    /**
     * 匿名内部类
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>() {{
            add("z");
            add("b");
            add("c");
        }};
        System.out.println("list = " + list); // list = [z, b, c]
    }

    @Test
    public void List_Collentions() {
        /**
         * List 不可变
         */
        List<String> list = Collections.nCopies(3, "z");
        System.out.println("list = " + list); // list = [z, z, z]
//      会报错：  list.add("add");
        /**
         * List 可变
         */
        List<String> list1 = new ArrayList<>(Collections.nCopies(3, "z"));
        System.out.println("list1 = " + list1); // list1 = [z, z, z]
        list1.add("add");
        System.out.println("list1 = " + list1); // list1 = [z, z, z, add]
    }

    @Test
    public void Lists() {
        /**
         * JDK9 引入的Lists创建
         * List<Integer> list = Lists.newArrayList(1, 2, 3);
         */
//        List<String> list = Lists.newArrayList("1", "2", "3");
//        System.out.println("getList4: " + list);
    }

    /**
     * ImmutableList
     */
    @Test
    public void ImmutableList_Test() {
//        List<String> list = ImmutableList.of("1", "add", "3");
//        System.out.println("list = " + list);
    }

    /**
     * stream创建
     */
    @Test
    public void stream_List() {
        List<String> list = Stream.of("1", "zcw", "All").collect(Collectors.toList());
        System.out.println("list = " + list); // list = [1, zcw, All]
        list.add("add");
        System.out.println("list = " + list); // list = [1, zcw, All, add]


        Map<String, String> map = Stream.of(new String[][] {
                { "Hello", "World" },
                { "John", "Doe" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
        System.out.println("map = " + map); // map = {Hello=World, John=Doe}
    }

}
