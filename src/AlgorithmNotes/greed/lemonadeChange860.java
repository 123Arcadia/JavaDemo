package AlgorithmNotes.greed;

public class lemonadeChange860 {
    /**
     * bills[i] 不是 5 就是 10 或是 20
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0, twenty = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            }
            if (bill == 10) {
                if (five<=0){
                    return false;
                }
                ten++;
                five--;
            }
            if (bill == 20) {
                // 优先消耗10,
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                    twenty++;
                }else if (five >= 3) {
                    five -= 3;
                    twenty++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
