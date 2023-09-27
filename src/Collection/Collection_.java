package Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;

public class Collection_ {
    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("jack");
        list.add(10);
        list.add(true);
        System.out.println("list = " + list);

        list.remove("jack");
        //list.remove(0);
        System.out.println("list = " + list);

        System.out.println((list.contains("jack"))); // false


        List<String> list1 = Arrays.asList(new String[]{"A", "B", "C"});
        System.out.println("list1 = " + list1 + "==>" + list1.getClass());

        //迭代器
        Iterator interator = list1.iterator();
        while (interator.hasNext()) {
            System.out.print(interator.next() + "->");

        }

        System.out.println();
        //集合转数组
        Object[] arr = list1.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }



    }

    @Test
    public void test() {
        /// 这是基本类型 包装类型：
        // == : 比较地址
        // equals ：比较内容
        Integer i1 = 10;
        //使用构造方法;编译器提示可以省略new Integer()
        Integer i2 = 10;
        Integer i3 = 300;
        //使用静态方法
        Integer i4 = 300;
        System.out.println(i1==i2);   //True
        System.out.println(i3==i4); // false

        System.out.println("=======");
//        String s1 = "zcw";
//        String s2 = "zcw";
//        System.out.println(s1==s2);
        String s1= new String("123");
        String s2= new String("123");
        String s3= new String("123");
        System.out.println(s1==s2); //false
        System.out.println(s1==s3); //false
        System.out.println(s2.equals(s3)); //true
        System.out.println("=========");
        String str3 = new String("string");

        String str4 = new String("string");
        System.out.println(str3 == str4); //false

    }

    @Test
    public void test_List() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, new String[]{"a", "b", "c"});
        System.out.println(list);
        // [a, b, c]
        list.addAll(List.of(new String[]{"f", "g"}));

//       错误写法：of不适合of()
//       list.addAll(List.of(new String[]{"f", null}));
        System.out.println("list = " + list);
        // list = [a, b, c, f, g]

        System.out.println("list.containsAll(List.of(new String[]{\"v\", \"b\"})); = " + list.containsAll(List.of(new String[]{"v", "b"})));;
        // false

        System.out.println("list.containsAll(List.of(new String[]{\"a\", \"b\"})); = " + list.containsAll(List.of(new String[]{"a", "b"})));;
        // true

        list.add(2, "zcw");
        System.out.println("list = " + list);
        // list = [a, b, zcw, c, f, g]

        list.remove(2);
    }

    @Test
    public void test_COllection() {
        Collection<Integer> collection = new ArrayList<>();
        collection.add(12);
        collection.add(2);
        collection.add(99);
        System.out.println("collection = " + collection.spliterator());

        List<Integer> list = new ArrayList<>(Arrays.asList(null, 1, 23, null));
        System.out.println("list = " + list);


        @Data
        @AllArgsConstructor
        class stu {
            String name;
            int age;
        }
        HashSet<String> set = new HashSet<>();
        stu stu1 = new stu("zcw1", 21);
        stu stu2 = new stu("zcw2", 12);
        stu stu3 = new stu("zcw3", 2);
        stu stu4 = new stu("zcw4", 44);

        set.add(stu1.name);
        set.add(stu2.name);
        System.out.println("set = " + set);
        list.isEmpty();

        List<stu> stuList = new ArrayList<>();
        stuList.add(stu1);
        stuList.add(stu2);
        Collections.sort(stuList, new Comparator<stu>() {
            @Override
            public int compare(stu o1, stu o2) {
                return Integer.compare(o1.age,o2.age);
            }
        });
        /**
         * 只会排序之前add的元素
         */
        stuList.add(stu3);
        stuList.add(stu4);
        System.out.println("stuList = " + stuList);


    }

    @Test
    public void test_sort() {
        List list = new ArrayList<Integer>();
        list.add(1);
        list.add(6);
        list.add(3);

        Collections.sort(list);
        int maxV = (int) Collections.max(list);
        Optional max = list.stream().max(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer) o1 - (Integer) o2;
            }
        });
        System.out.println("list = " + list); // list = [1, 3, 6]
        System.out.println("maxV = " + maxV); // maxV = 6
        System.out.println("max = " + max); // max = Optional[6]
    }

}
