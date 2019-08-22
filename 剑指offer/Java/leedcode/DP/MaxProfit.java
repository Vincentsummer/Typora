package DP;

/**
 * 120. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * **/

public class MaxProfit {
	
	// 记录一个前 i 天的最小值，以此减最小值即可。复杂度 O(n) & O(1)
    public int maxProfit(int[] prices) {
    	if (prices.length == 0)
    		return 0;
        int result = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; ++i) {
        	minNum = Math.min(prices[i], minNum);
        	result = Math.max(prices[i] - minNum, result);
        }
        return result;
    }
}
