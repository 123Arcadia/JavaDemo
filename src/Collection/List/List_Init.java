package Collection.List;

import org.junit.Test;

import java.util.*;
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


    @Test
    public void test_shuffle(){
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 23; i++) {
            list.add(i);
            map.put(i, i + 1);
        }
        System.out.println("map = " + map);
        System.out.println("list = " + list);
        Set<Integer> set = map.keySet();
        System.out.println("set = " + set);
        List<Integer> listmap = new ArrayList<>(set);
        shuffle(listmap);
        System.out.println("listmap = " + listmap);

        Collections.sort(listmap, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        System.out.println("listmap sort = " + listmap);

        shuffle(listmap);
        HashMap<Integer, Integer> mapClone = map;

        for (Integer i : listmap) {
            System.out.println(i + ", " + map.get(i));
        }
        System.out.println("map = " + map);

    }


    public <T> void shuffle(List<T> list) {
        int size = list.size();
        Random random = new Random();

        for(int i = 0; i < size; i++) {

            int randomPos = random.nextInt(size);

            Collections.swap(list, i, randomPos);
        }
    }
}
