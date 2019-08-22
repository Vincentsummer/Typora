package DP;

/**
 * 300. 最长上升子序列
 *给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * **/

public class LengthOfLIS {
	
	// 思路一：dp[i] 表示数组前 i 个数字中以 i 结尾的LIS的长度。
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0)
        	return 0;
        if (n == 1)
        	return 1;
        
        int dp[] = new int[n];
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < n; ++i) {
        	dp[i] = 1;
        	for (int j = i-1; j >= 0; ++j) {
        		if (nums[j] < nums[i]) {
        			dp[i] = Math.max(dp[j] + 1, dp[i]);
        		}        		
        	}
        	result = Math.max(result, dp[i]);
        }
        return result;
    }
    
    /**
     * 思路二：
     * dp[i]: 所有长度为i+1的递增子序列中, 最小的那个序列尾数。
     * 由定义知dp数组必然是一个递增数组, 可以用 maxL 来表示最长递增子序列的长度。
     * 对数组进行迭代, 依次判断每个数num将其插入dp数组相应的位置：
     * 1. num > dp[maxL], 表示num比所有已知递增序列的尾数都大, 将num添加入dp
     * 数组尾部, 并将最长递增序列长度maxL加 1
     * 2. dp[i-1] < num <= dp[i], 只更新相应的dp[i]
    **/
    public int lengthOfLIS2(int[] nums) {

        int maxL = 0;
        int[] dp = new int[nums.length];
        for (int num : nums) {
            // 二分法查找
            int lo = 0, hi = maxL;
            while (lo < hi) {
                int mid = lo+(hi-lo) / 2;
                if (dp[mid] < num)
                    lo = mid+1;
                else
                    hi = mid;
            }
            dp[lo] = num;
            if (lo == maxL)
                maxL++;
        }
        return maxL;
    }
}
