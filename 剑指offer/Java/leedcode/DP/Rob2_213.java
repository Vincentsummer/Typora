package DP;

/**
 * 213. 打家劫舍2
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都<围成一圈>，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * **/

public class Rob2_213 {
	// 将情况分为偷第1家和不偷第1家，然后同Rob1的解法，比较两种情况。
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 2)
        	return 0;
        if (n == 1)
        	return nums[0];
        
        int dp[] = new int[n];
        int result = 0;
        dp[0] = nums[0];	// 偷第1家
        dp[1] = dp[0];
        for (int i = 2; i < n - 1; ++i)
        	dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        result = dp[n-2];
        
        dp[0] = 0; 		// 不偷第1家
        dp[1] = nums[1];
        for (int i = 2; i < n; ++i)
        	dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        
        result = Math.max(result, dp[n-1]);
        return result;
    }
}
