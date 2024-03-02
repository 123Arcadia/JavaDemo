package AlgorithmNotes.greed;

public class canCompleteCircuit134 {
    /**
     * 134. 加油站
     */

    public int canCompleteCircuit(int[] gas, int[] cost) {
//        // s[3]-s[1] = s[3] - s[2] + s[2] - s[1]
//        int curSUm = 0, total = 0;
//        int start = 0;
//        for (int i = 0; i < gas.length; i++) {
//            curSUm += gas[i] - cost[i];
//            total += gas[i] - cost[i];
//            if (curSUm < 0) {
//                start = i+1;
//                curSUm = 0;
//            }
//        }
//        if (total < 0) {
//            return  -1;
//        }
//        return start;
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, costOfSum = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                costOfSum += cost[j];
                if (sumOfGas < costOfSum) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) { // 可以走完一圈
                return i;
            } else {
                i = i + cnt + 1; // 复杂度O(n)
            }
        }
        return i;
    }
}
