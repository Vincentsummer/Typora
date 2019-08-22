package DP;

/**
 * 309. 最佳买卖股票时机（含冷冻期）
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * input：[1,2,3,0,2]
 * output：3
 * **/

public class MaxProfit_f {
	
	// 思路一：
	// dp[i] 表示第 i 天的最大利润，分为第 i 天不卖和卖两种情况，卖又需考虑买入的情况。则有
	// dp[i] = max(dp[i-1], dp[j-2] + price[i] - price[j]), for i > j >= 0.
	// 复杂度 O(n^2) & O(n)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0 || n == 1)
        	return 0;
        int dp[] = new int[n];
        dp[0] = 0;
        dp[1] = Math.max(prices[1] - prices[0], 0);
        int result = Integer.MIN_VALUE;
        for (int i = 2; i < n; ++i) {
        	dp[i] = dp[i-1];
        	for (int j = i-1; j >= 0; --j) {
        		if (j == 0 || j == 1 || j == 2)
        			dp[i] = Math.max(dp[i], prices[i] - prices[j]);
        		else
        			dp[i] = Math.max(dp[i], dp[j-2] + prices[i] - prices[j]);
        	}
        	result = Math.max(result, dp[i]);
        }
        return result;
    }
    
    // 思路二：
    // sell[i]表示截至第i天，最后一个操作是卖时的最大收益；
    // buy[i]表示截至第i天，最后一个操作是买时的最大收益；
    // 则有：sell[i] = max(buy[i-1]+prices[i], sell[i-1])  & buy[i] = max(cool[i-1]-prices[i], buy[i-1]) 
    // 复杂度 O(n) & O(n)
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 0 || n == 1)
        	return 0;
        int sell[] = new int[n];
        int buy[] = new int[n];
        sell[0] = 0;
        buy[0] = -prices[0];
        for (int i = 1; i < n; ++i) {
        	if (i == 1) {
        		sell[i] = Math.max(buy[i-1] + prices[i], sell[i-1]);
        		buy[i] = Math.max(-prices[i], buy[i-1]);
        	}
        	else {
            	sell[i] = Math.max(buy[i-1] + prices[i], sell[i-1]);
            	buy[i] = Math.max(sell[i-2] - prices[i], buy[i-1]);
        	}

        }
        return sell[n-1];
    }
}
