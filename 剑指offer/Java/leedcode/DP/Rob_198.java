package DP;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * **/

public class Rob_198 {
	
	// 递推式：dp[i] 表示前 i 家能偷到的最大金额。
	// 1. 偷第 i 家：dp[i] = dp[i-2] + nums[i]
	// 2. 不偷第 i 家：dp[i] = dp[i-1];
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0)
        	return 0;
        
        int dp[] = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; ++i) {
        	dp[i] = Math.max(dp[i - 2] + nums[i], dp[i-1]);
        }
        return dp[n-1];
    }
}
