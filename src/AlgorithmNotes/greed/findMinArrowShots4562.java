package AlgorithmNotes.greed;

import java.util.Arrays;
import java.util.Comparator;

public class findMinArrowShots4562 {
    /**
     * 452. 用最少数量的箭引爆气球
     */

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int cnt = 1;
        int prel = points[0][0], prer = points[0][1];
        for (int i = 1; i < points.length; i++){
            int l = points[i][0], r = points[i][1];
            if (l > prer) {
                System.out.println("-- prel = " + prel + ", prer = " + prer +", l = " + l + ", r = " + r);
                prel = points[i][0];
                prer = points[i][1];
                cnt++; // 这里加的是上一个point需要一支箭
            }
            // System.out.println(cnt + ", l = " + l + ", r = " + r);
        }
        return cnt;

        // 注意:
        ///[[1,2],[2,3],[3,4]]
        // 当 prel = 1, prer = 2, l = 3, r = 4时，cnt++
    }
}
