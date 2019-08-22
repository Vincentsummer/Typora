package DP;

/**
 * 303. 区域和检索 - 数组不可变
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * **/

public class NumArray {
	
	private int dp[];
	
    public NumArray(int[] nums) {
        dp = new int[nums.length];
        if (dp.length != 0) {
        	dp[0] = nums[0];
        	for (int i = 1; i < dp.length; ++i)
        		dp[i] = dp[i-1] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        if (i <= j && i >= 0 && j < dp.length) {
        	if (i == 0)
        		return dp[j];
        	else 
        		return dp[j] - dp[i-1];
        }
        return 0;	
    }
}
