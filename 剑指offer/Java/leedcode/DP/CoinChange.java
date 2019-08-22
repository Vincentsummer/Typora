package DP;
/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * **/
public class CoinChange {
	// 思路一：
	// dp[i] 表示凑成金额 i 所需的最少金币数
	// dp[i] = min(dp[i], dp[i - coin] + 1), for coin in coins。
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        if (amount == 0)
        	return 0;
        if (n == 0)
        	return -1;
        int dp[] = new int[amount+1];
        for (int i = 1; i <= amount; ++i) {
        	dp[i] = Integer.MAX_VALUE;
        	for(int j = 0; j < n; ++j) {
        		int tmp = i - coins[j];
        		if (tmp >= 0 && dp[tmp] != -1)
        			dp[i] = Math.min(dp[i], dp[tmp] + 1);
        	}
            if (dp[i] == Integer.MAX_VALUE)
        	    dp[i] = -1;
        }
        return dp[amount];
    }
    
    // 思路二：深度遍历，广度遍历
}
