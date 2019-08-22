package DP;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * **/

public class MaxSubArray {
	
	
	// 分析数组规律，O(n)解法
    public int maxSubArray(int[] nums) {
    	int sum = 0, max = -Integer.MAX_VALUE;
    	for (int i = 0; i < nums.length; ++i) {
    		sum += nums[i];
    		max = Math.max(sum, max);
    		if (sum < 0) {
    			sum = 0;
    		}
    	}
    	return max;
    }
    
    // 动态规划解法，用f(i)表示以第 i 个数字结尾的子数组的和最大和，递归式如下：
    // if i = 0 or f(i-1)<=0，f(i) = nums[i]。
    // if i != 0 and f(i-1) > 0，f(i-1) + nums[i]。
    public int maxSubArray_dp(int[] nums) {
    	int n = nums.length, max = -Integer.MAX_VALUE;
    	int[] dp = new int[n];
    	for (int i = 0; i < n; ++i) {
    		if (i == 0 || nums[i] <= 0)
    			dp[i] = nums[i];
    		else if (i != 0 && dp[i-1] > 0)
    			dp[i] = dp[i-1] + nums[i];
    		max = Math.max(max, dp[i]);
    	}
    	return max;
    }
} 
