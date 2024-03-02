package AlgorithmNotes.greed;

public class canJump55 {
    /**
     * 55. 跳跃游戏
     */
    public boolean canJump(int[] nums) {
        // 在当前跳跃长度寻找数值最大的
        // i + nums[i]: 表示加入从0开始到i位置，那可以从i跳跃到i + nums[i]的位置(相对0开始)
//        int k = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (i <= k) { // 限制了只能在改跳跃长度内遍历i, 而每次会更新k
//                k = Math.max(k, i + nums[i]);
//                if (k >= nums.length -1) return true;
//            }
//        }
//        return false;

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            // 每次最大的跳跃长度
            if (i <= k) {
                k = Math.max(k, nums[i] + i);
                if (k >= nums.length-1) {
                    return true;
                }
            }
        }
        return false;
    }



















}
