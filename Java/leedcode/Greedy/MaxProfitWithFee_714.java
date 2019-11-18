package Greedy;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能
 * 再继续购买股票了。返回获得利润的最大值。
 * **/

public class MaxProfitWithFee_714 {
	
	// 思路一：动态规划
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n < 2)
        	return 0;
        int sell = 0, buy = -prices[0];
        for (int i = 1; i < n; ++i) {
        	sell = Math.max(sell, buy + prices[i] - fee);
        	buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }
    
    // 思路二：贪心
    /**
     * 假设此次交易利润：B - A - fee， 下次交易利润：D - C - fee， 两次交易利润之和为 B - A + D - C - fee * 2，
     *  而只进行一次交易的利润为：D - A - fee， 只有当两次交易利润之和大于只进行一次交易的利润，才会进行两
     *  次交易，因此：B - A + D - C - fee * 2 > D - A - fee，化简得 B - fee > C，即下次交易的买入额要小于这次
     *  交易的卖出额-fee，才会获得更高总利润。 这里设定minimum为此次交易卖出额-fee， 如果下一次股票价格
     *  比minimum小，说明值得买入（重新赋值minimum）； 如果大于minimum + fee，则按照minimum来计算
     *  交易利润，计算结果等价于上一次交易不卖出，这次卖出（即公式里的D - A - fee）； 而其他情况不值得交易
     *  （得不偿失或者根本没利润）。
     * **/
    public int maxProfit2(int[] prices, int fee) {
    	int n = prices.length;
    	if (n < 2)
    		return 0;
    	int minimum = prices[0], result = 0;
    	for (int i = 0; i < n; ++i) {
    		if (prices[i] < minimum)
    			minimum = prices[i];
    		else if (prices[i] > minimum + fee) {
    			result += prices[i] - minimum - fee;
    			minimum = prices[i] - fee;
    		}
    	}
    	return result;
    } 
}
