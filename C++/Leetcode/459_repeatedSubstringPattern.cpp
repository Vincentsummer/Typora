//
// Created by vincent on 20-2-21.
//

#include "solution.h"

/**
 * 459. 重复的子字符串
 * 方法一： 构造s+s
 * 方法二： 使用KMP算法
 * **/

namespace leetcode
{
    bool Solution::repeatedSubstringPattern(string s)
    {
        return (s + s).find(s, 1) != s.size();
    }
} // namespace leetcode
