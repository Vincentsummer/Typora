package DP;

/**
 * 1043. 分隔数组以得到最大和
 * **/
public class MaxSumAfterPartitioning_1043 {
	/*
	对于数组求 最大  最小问题 一般为 动态规划问题
	建立一位数组res res[i] 表示：从 0 到 位置 i 子数组的最大和
	    对于每一个位置 都可能有 k 种情况：
	         ①：只使用它自己位置的数字
	         ②：让他保持和前面 1 个数字相同
	         ........
	         ⑩：让他保持和前面 k - 1 个数字相同
	    转移方程：
	         ①：domainMax = Math.max(domainMax, A[i - j + 1]);
	            作用：求它和前面 j - 1 个数字的最大值 为保持相同的数字
	         ②：res[i] = Math.max(res[i], ((i - j < 0)? 0 : res[i - j])  + j * domainMax);
	            作用：求位置 i 的时候 k 种情况 的最大值
	    边界条件：
	         ①：i - j + 1 >= 0
	            作用：保证它的前面有 j - 1 个元素
	         ②：i - j < 0？
	            作用：判断 当 前面恰有 j - 1 个元素 且 第 i 个位置 保持和前面 j - 1 个数字相同
	*/
    public int maxSumAfterPartitioning(int[] A, int K) {
        int dp[] = new int[A.length];
        int maxValue = 0;
        for (int i = 0; i < A.length; ++i) {
        	maxValue = A[i];
        	for (int j = 1; j <= K && i - j + 1 >= 0; ++j) {
        		maxValue = Math.max(maxValue, A[i - j + 1]);
        		dp[i] =  Math.max(dp[i], (i - j < 0 ? 0 : dp[i-j]) + j * maxValue);
        	}
        }
        return dp[A.length - 1];
    }
}
