package DP;
/**
 * 123. 买卖股票的最佳时机 III
 * **/
public class maxProfit3_123 {
	
	// 思路一：动态规划 超时 O(n^3)
    public int maxProfit(int[] prices) {
    	int n = prices.length;
    	if (n <= 1)
    		return 0;
    	int dp[][] = new int[n][n];
    	int res = 0;
    	for (int k = 1; k < n; k++) {
	    	for (int i = 0; i < n - k; i++) {
	    		for (int j = i; j < i + k; j++) {
	    			dp[i][i+k] = Math.max(dp[i][j] + dp[j+1][i+k], prices[i+k] - prices[i]);
                    res = Math.max(res, dp[i][i+k]);
	    		}
	    	}	
    	}
    	return res;
    }
    
    // 思路二：维护两个数组，left[i] 表示前i天的最大收益，right[i] 表示后 i 天的最大收益
    // 则有 res = max(res, left[i]+right[i-1])
    // O(n)
    public int maxProfit1(int[] prices) {
    	int n = prices.length;
    	if (n <= 1)	 return 0;
    	int res = 0;
    	int left[] = new int[n];
    	int right[] = new int[n];
    	int minPrices = prices[0];
    	for (int i = 1; i < n; ++i) {
    		int diff = prices[i] - minPrices;
    		left[i] = Math.max(left[i-1], diff);
    		if (diff < 0) minPrices = prices[i];
    		res = Math.max(res, left[i]);
    	}
    	int maxPrices = prices[n-1];
    	for (int i = n - 2; i >= 0; --i) {
    		int diff = maxPrices - prices[i];
    		right[i] = Math.max(right[i+1], diff);
    		if (diff < 0) maxPrices = prices[i];
    		res = Math.max(res, right[i]);
    	}
    	
    	for (int i = 1; i < n; ++i)
    		res = Math.max(res, left[i-1] + right[i]);
    	return res;
    }
    
    // 思路三：优化空间，对于任意一天，考虑四个变量：
    // fstBuy: 在该天第一次买入股票可获得的最大收益 
    // fstSell: 在该天第一次卖出股票可获得的最大收益
    // secBuy: 在该天第二次买入股票可获得的最大收益
    // secSell: 在该天第二次卖出股票可获得的最大收益
    // 分别对四个变量进行相应的更新, 最后secSell就是 res
    public int maxProfit2(int[] prices) {
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for(int p : prices) {
            fstBuy = Math.max(fstBuy, -p);
            fstSell = Math.max(fstSell, fstBuy + p);
            secBuy = Math.max(secBuy, fstSell - p);
            secSell = Math.max(secSell, secBuy + p); 
        }
        return secSell;
    }
}
