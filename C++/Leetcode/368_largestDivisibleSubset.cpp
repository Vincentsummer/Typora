//
// Created by vincent on 20-2-15.
//

#include "solution.h"

/**
 * 368. 最大整除子集
 * 动态规划
 * **/

namespace leetcode
{
    /**
     * 设定状态: dp[i]: 以nums[i]结尾的序列最大长度
     * last[i]: 在最大序列中 nums[i]的上一个元素在nums出现的下标
     * 状态转移方程：
     * 使用二重循环,对于每一个nums[i]，看他可以接在之前的哪个序列dp[j]上，使得dp[i]最长
     * nums[i]%nums[j] == 0是可以接的条件，dp[i]<=dp[j]是使得dp[i]变长的条件
     * 初始状态：dp[i] = 1 (i:1 - n) 每一个只有自己的序列长度为1
     * O(N^2) & O(N)
     * **/
    vector<int> Solution::largestDivisibleSubset(vector<int> &nums)
    {
        int len = (int) nums.size();

        vector<int> res, dp(len, -1), last(len, -1);
        sort(nums.begin(), nums.end());
        int maxLen = 0, idx;
        for (int i = 0; i < len; ++i){
            for (int j = 0; j < i; ++j){
                if (nums[i] % nums[j] == 0 && dp[i] <= dp[j]){
                    dp[i] = dp[j] + 1;
                    last[i] = j;
                }
            }
            if (dp[i] > maxLen){
                maxLen = dp[i];
                idx = i;
            }
        }

        for (int i = idx; i != -1; i = last[i])
            res.push_back(nums[i]);
        return res;
    }

    /**
     * vincent:
     * 使用二维数组记录以nums[i]结尾的所有的最长子数组
     * O(N^2) & O(N^2)
     * **/
    vector<int> largestDivisibleSubset(vector<int>& nums) {
        int len = (int) nums.size();
        if (len <= 1) return nums;

        vector<vector<int> > res;
        sort(nums.begin(), nums.end());
        res.emplace_back(nums.begin(), nums.begin()+1);
        vector<int> tmp;
        int maxLen = 1, maxSize = 0, maxIdx = 0;
        for (int i = 1; i < len; ++i){
            int maxLen = 1, idx = -1;
            for (int j = i-1; j >= 0; --j){
                if (nums[i] % nums[j] == 0){
                    if (maxLen < res[j].size() + 1){
                        maxLen = res[j].size() + 1;
                        idx = j;
                    }
                }
            }
            if (idx != -1)
                tmp = vector<int>(res[idx].begin(), res[idx].end());
            tmp.push_back(nums[i]);
            res.push_back(move(tmp));

            if (maxSize < res[i].size()){
                maxSize = res[i].size();
                maxIdx = i;
            }
        }
        return res[maxIdx];
    }
} // namespace leetcode