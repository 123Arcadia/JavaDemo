package AlgorithmNotes.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class merge56 {

    /**
     * 56.合并区间
     */

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }

        List<int[]> merged = new ArrayList<int[]>();
//        for (int i = 0; i < intervals.length; i++) {
//            int l = intervals[i][0], r =  intervals[i][1];
//            if (merged.size() == 0 || merged.get(merged.size()-1)[1] > l) {
//                merged.add(new int[]{l, r});
//            } else {
//                merged.get(merged.size()-1)[1] = Math.max(r, merged.get(merged.size()-1)[1]);
//            }
//        }
        merged.add(new int[]{intervals[0][0], intervals[0][1]});
        int prel = intervals[0][0], prer = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (l > prer) {
                // 没有重叠
                merged.add(new int[]{l, r});
            } else {
                merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1], r);
            }
            prel = merged.get(merged.size()-1)[0] ;
            prer = merged.get(merged.size()-1)[1] ;
        }
        return merged.toArray(new int[merged.size()][]);
    }


    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
                // 必须按照[1]排序
            }
        });
        int ans = 0;
        int preR = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            int l = intervals[i][0];
            if (l >= preR) {
                // 只统计不重叠的数量，最后相减
                ans++;
                preR = intervals[i][1];
            }
        }
        return intervals.length-ans;
    }
}
