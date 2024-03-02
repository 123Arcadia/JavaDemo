package AlgorithmNotes.greed;

import java.util.Arrays;
import java.util.Comparator;

public class eraseOverlapIntervals435 {

    /**
     * 435. 无重叠区间
     */

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                // 按point[1]升序
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        //[1, 2]
        //[2, 3]
        //[1, 3]
        //[3, 4]
        int cnt=1;
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }
        int prel = intervals[0][0], prer = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (l >= prer) {
                cnt++;
                prel = intervals[i][0];
                prer = intervals[i][1];
            }
        }
        return intervals.length-cnt;
    }
}
