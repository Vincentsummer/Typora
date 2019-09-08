package DP;

/**
 * 70. 爬楼梯 （斐波那契数列）
 * 假设你正在爬楼梯。需要 n（正整数） 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * **/

public class ClimbStairs_70 {
    public int climbStairs(int n) {
        if (n == 1)
        	return 1;
        if (n == 2)
        	return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; ++i)
        	dp[i] = dp[i-1] + dp[i-2];
        return dp[n-1];
    }
}
