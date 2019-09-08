package DP;

/**
 * 368. 最大整除集
 * 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
 * 果有多个目标子集，返回其中任何一个均可。
 * **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset_368 {
	
	// 排序后DP，dp[i] 表示以 nums[i] 为最大值的最大整除集。
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        if (n == 0)
        	return result;
        int dp[] = new int[n];
        int maxSub = dp[0];
        int maxIndex = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
        	for (int j = 0; j < i; ++j) {
        		if (nums[i] % nums[j] == 0) {
        			dp[i] = Math.max(dp[i], dp[j] + 1);
        		}
        	}
        	if (maxSub < dp[i]) {
        		maxSub = dp[i];
        		maxIndex = i;
        	}
        }
        int j = 1;
        for (int i = 0; i <= maxIndex; ++i) {
        	if (nums[maxIndex] % nums[i] == 0 && dp[i] == j) {
        		result.add(nums[i]);
        		++j;
        	}
        }
        return result;
    }
}
