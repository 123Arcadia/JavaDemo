package Collection.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;

public class Sort {

    @Test
    public void test_sort() {
        List<stu> list = new ArrayList<>(Arrays.asList(new stu("zcw1", 17), new stu("zcw2", 23),
                new stu("zcw3", 3)));

        Collections.sort(list, new Comparator<stu>() {
            @Override
            public int compare(stu o1, stu o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        System.out.println("list = " + list);

        for (stu i : list) {
            if (i.getAge() == 17){
                list.remove(i); // stu(name=zcw3, age=3) note: 坑!!!remove会实时改变list，导致原本第三个元素无法遍历
                continue;
            }

            System.out.println(i);
        }


//        list.remove(new stu("zcw3", 3));
        System.out.println("list = " + list);
    }

}


@Data
@AllArgsConstructor
class  stu{
    private String name;
    private int age;

}
