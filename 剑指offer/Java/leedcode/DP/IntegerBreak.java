package DP;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * **/

public class IntegerBreak {
	// 思路一：动态规划
	// dp[i] 表示数字 i 所能拆分的最大乘积，则有
	// dp[i] = max(dp[i], max(i-j, dp[i-j]) * j)
    public int integerBreak(int n) {
        if (n == 1)
        	return 1;
        int dp[] = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
        	for (int j = 1; j <= i / 2; ++j) {
        		dp[i] = Math.max(dp[i], Math.max(i-j, dp[i-j]) * j);
        	}
        }
        return dp[n];
    }
    
    // 思路二：贪心
    // n > 5 时， 尽可能多地拆分成3，n <= 5时，不用拆分。
    // 可证当 n >= 5 时，i - j < dp[i-j]，且3(n - 3) > 2(n - 2)
    // n < 5 时，i - j >= dp[i-j]
    public int integerBreak2(int n) {
        if (n == 0 || n == 1 || n == 2)
        	return 1;
        else if (n == 3)
        	return 2;
        else if (n % 3 == 0)
        	return (int)(Math.pow(3, n / 3));
        else
        	return (int)(Math.pow(3, (n-2) / 3) * (n % 3 == 1 ? 4 : 2));
    }
}
