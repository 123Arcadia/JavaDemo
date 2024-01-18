package Collection.List;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorRemove {

    @Test
    public void removeIterator() {
        ArrayList<String> res = new ArrayList<>(Arrays.asList("zcw", "1223"));
        ArrayList<String>removeList = new ArrayList<>();
        Iterator<String> iterator = res.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            if (s.equals("1223")) {
                iterator.remove();
            }
            removeList.add(s);
            System.out.println(iterator.next());
        }
        System.out.println(iterator);
        System.out.println("res = " + res);
        System.out.println("removeList = " + removeList);
        res.removeAll(removeList);
        System.out.println("res = " + res);
    }

    @Test
    public void removeIterator2() {
        ArrayList<String> res = new ArrayList<>();
        res.add("123");
        res.add("1223");
        ArrayList<String>removeList = new ArrayList<>();
        Iterator<String> iterator = res.iterator();
        while (iterator.hasNext()){
            String s =  iterator.next();
            if ("1223".equals(s)) {
                iterator.remove();
            }
//            System.out.println(iterator.next());
            System.out.println(s);
        }
        System.out.println(iterator);
//        System.out.println("removeList = " + removeList);
//        res.removeAll(removeList);
        System.out.println("res = " + res);
    }

    @Test
    public  void test() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if("1".equals(item)){
                iterator.remove();
            }
        }
        System.out.println(list); // [2, 3]
    }

    public static void main(String[] args) {

        ArrayList<String>res = new ArrayList<>(Arrays.asList("zcw", "1223"));
        ArrayList<String>removeList = new ArrayList<>();
        Iterator<String> iterator = res.iterator();
        for (String s : res) {
            if (s.equals("1223")) {
                removeList.add(s);
            }
        }
        System.out.println(iterator);
        System.out.println("res = " + res);
        res.removeAll(removeList);
        System.out.println("res = " + res);
    }
}
