package AlgorithmNotes.hot100.Prices;

/**
 * 股票系列:
 *
 * 121. 买卖股票的最佳时机
 * 122. 买卖股票的最佳时机 II
 * 123. 买卖股票的最佳时机 III
 * 188. 买卖股票的最佳时机 IV
 * 309. 买卖股票的最佳时机含冷冻期
 * 714. 买卖股票的最佳时机含手续费
 *
 *
 * 作者：灵茶山艾府
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solutions/2201406/shi-pin-jiao-ni-yi-bu-bu-si-kao-dong-tai-o3y4/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class PricesMutilCase {


    /**
     * 121
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

//        int ans= 0;
//        int minPrices = prices[0];
//        for (int price : prices) {
//            ans = Math.max(ans, price - minPrices); // 最高的利润
//            minPrices = Math.min(minPrices, price); // 之前最小的代价
//        }
//        return ans;

        int ans= 0 ;
        for (int i = 1; i < prices.length; i++) {
            // 今天买明天卖，出现亏损就重新累加
            ans += Math.max(0, prices[i] - prices[i-1]);
        }
        return ans;
    }

    /**
     * 123
     *
     * - 未进行过任何操作；(没有利润，不用计算)
     * - 只进行过一次买操作； buy1 = Math.max(buy1, -prices[i])  / 不做操作， 买入
     * - 进行了一次买操作和一次卖操作，即完成了一笔交易； sell1 = Math.max(sell1, prices[i] + buy1)  / 不做操作， 买出第一次买入的股票
     * - 在完成了一笔交易的前提下，进行了第二次买操作； buy2 = Math.max(buy2, sell1 - prices[i[)  / 不做操作， 买入
     * - 完成了全部两笔交易: sell2 = Math.max(buy2 + prices[i], sell2)  / 卖出, 不做操作
     */
    public int maxProfit123(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n ;i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, prices[i] + buy1);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }


    /**
     * 188. 买卖股票的最佳时机 IV
     */
//    public int maxProfit(int k, int[] prices) {
//
//    }



}
