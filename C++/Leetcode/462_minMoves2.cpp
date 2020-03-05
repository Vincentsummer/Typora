//
// Created by vincent on 20-2-8.
//

#include "solution.h"

/**
 * 462. 最少移动次数使数组元素相等 II
 * 排序后选择中位数为基准
 * **/

namespace leetcode
{
    int Solution::minMoves2(vector<int> &nums)
    {
        int len = nums.size();
        if (len <= 1) return 0;

        sort(nums.begin(), nums.end());
        int res = 0, center = nums[len / 2];
        for (int i = 0; i < len; ++i){
            res += abs(nums[i] - center);
        }
        return res;
    }
} // namespace leetcode