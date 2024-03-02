package AlgorithmNotes.greed;

import org.junit.Test;

import java.util.*;

public class reconstructQueue406 {
    /**
     *
     */

//    public int[][] reconstructQueue(int[][] people) {
//        Arrays.sort(people, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] == o2[0]) {
//                    return o1[1] - o2[1];
//                }
//                return - o1[0] + o2[0];
//            }
//        });
//        for (int[] person : people) {
//            System.out.println(Arrays.toString(person));
//        }
//        LinkedList<int[]> queue= new LinkedList<>();
//        for (int i = 0; i < people.length; i++) {
//            queue.add(people[i][1], people[i]); // 在他的k出插入
//        }
//        return queue.toArray(new int[people.length][]);
//    }
    //[7, 0]
    //[5, 0]
    //[7, 1]
    //[6, 1]
    //[5, 2]
    //[4, 4]


    @Test
    public void test() {
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>(10);
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,43,2,3,21,78));
//        int[] ints = list.toArray(new int[0]);
//
//        System.out.println(Arrays.toString(ints));
    }


    public int[][] reconstructQueue(int[][] people) {
        //前面 正好 有 ki 个身高大于或等于 hi 的人
        // 先按照身高大到小, 同身高k小的在前
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return -o1[0] + o2[0];
            }
        });
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
            queue.add(people[i][1], people[i]);
        }
        return queue.toArray(new int[people.length][]);
    }

}
