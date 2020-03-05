//
// Created by vincent on 20-2-7.
//

#include "solution.h"

/**
 * 26. 删除排序数组中的重复项
 * 双指针
 * **/

namespace leetcode
{
    int Solution::removeDuplicates(vector<int> &nums)
    {
        int len = nums.size();
        if (len <= 1) return len;
        int cur = nums[0], res = 0;

        for (int i = 1; i < len; ++i){
            if (nums[i] > cur){
                cur = nums[i];
                nums[i] = nums[res];
                nums[res] = cur;
            }
            res++;
        }
    }
} // namespace leetcope