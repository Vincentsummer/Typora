package Greedy;

/**
 * 122. 买股票的最佳时机 2
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算所能获取的最大利润。可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：不能同时参与多笔交易（必须在再次购买前出售掉之前的股票）。
 * **/

public class MaxProfit {
	// 贪心法：股票可以当天买入并卖出，于是只要当天比前一天价格高就卖出即可。复杂度 O(n) & O(1)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2)
        	return 0;
        int result = 0;
        for (int i = 1; i < n; ++i)
        	result += prices[i] - prices[i-1] > 0 ? prices[i] - prices[i-1] : 0;
        return result;
    }
    
    // 动态规划法：sell[i] 表示截止第 i 天最后一次操作为卖时的最大利润。
    // buy[i] 表示截止第 i 天最后一次操作为买时的最大利润。则有
    // sell[i] = max(sell[i-1], buy[i-1]+ prices[i])
    // buy[i] = max(buy[i-1], sell[i-1] - prices[i]);
    // 复杂度：O(n) & O(n)
    public int maxProfit2(int[] prices) {
    	int n = prices.length;
    	if (n < 2)
    		return 0;
    	int sell[] = new int[n];
    	int buy[] = new int[n];
    	sell[0] = 0;
    	buy[0] = -prices[0];
    	int result = 0;
    	for (int i = 1; i < n; ++i) {
    		sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
    		buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
    		result = Math.max(result, sell[i]);
    	}
    	return result;   
    }
}
