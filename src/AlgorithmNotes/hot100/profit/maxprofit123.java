package AlgorithmNotes.hot100.profit;

public class maxprofit123 {

    /**
     * 123. 买卖股票的最佳时机 III
     */


    public int maxProfit(int[] prices) {
        /**
         * 123
         *
         * - 未进行过任何操作；(没有利润，不用计算)
         * - 只进行过一次买操作； buy1 = Math.max(buy1, -prices[i])  / 不做操作， 买入
         * - 进行了一次买操作和一次卖操作，即完成了一笔交易； sell1 = Math.max(sell1, prices[i] + buy1)  / 不做操作， 买出第一次买入的股票
         * - 在完成了一笔交易的前提下，进行了第二次买操作； buy2 = Math.max(buy2, sell1 - prices[i[)  / 不做操作， 买入
         * - 完成了全部两笔交易: sell2 = Math.max(buy2 + prices[i], sell2)  / 卖出, 不做操作
         */
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; i++) {
            buy1 = Math.max(buy1, - prices[i]);
            sell1 = Math.max(sell1, prices[i] + buy1);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return  sell2;
    }
}
