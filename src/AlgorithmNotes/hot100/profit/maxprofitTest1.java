package AlgorithmNotes.hot100.profit;

public class maxprofitTest1 {


    /**
     * 121. 买卖股票的最佳时机
     */

    public int maxProfit(int[] prices) {
        // 找到最小值，在后面找到最大值，求差
        int minPrice = prices[0];
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            ans = Math.max(ans, prices[i] - minPrice);
            minPrice = Math.min(prices[i], minPrice);
        }
        return ans;
    }
}
