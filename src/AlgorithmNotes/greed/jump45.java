package AlgorithmNotes.greed;

public class jump45 {
    /**
     * 45. 跳跃游戏 II
     */

    public int jump(int[] nums) {
        // 使用贪心
        // 反向遍历, 到达终点的起点中选择距离最远（跳跃数最短）
//        int pos= nums.length-1;
//        int steps = 0;
//        while (pos > 0) {
//            for (int i = 0; i < nums.length; i++) {
//                if (i + nums[i] >= pos) { // 找到距离最远
//                    pos = i;0
//                    steps++;
//                    break;
//                }
//            }
//        }
//        return steps;
        // 正向遍历
        int n = nums.length;
        int lastEnd = 0, maxStep = 0; // 记录每轮的最远位置
        int step = 0; // 记录步数
        for (int i = 0; i < n - 1; i++) { // 因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置, 否则就不会到达终点
            maxStep = Math.max(maxStep, i + nums[i]);
            if (i == lastEnd) {
                // 当遍历到当前最远的位置,记为一个最短步数
                step ++;
                lastEnd = maxStep;
            }
        }
        return step;
    }
}
