//
// Created by vincent on 20-2-10.
//

#include "solution.h"

/**
 * 479. 最大回文数乘积
 * 构造回文数，判断是否满足
 * **/

namespace leetcode
{
    int Solution::largestPalindrome(int n)
    {
        if (n == 1) return 9;
        long maxValue = (long) (pow(10, n) - 1);
        for (long i = maxValue - 1; i > maxValue / 10; i--){
            string s1 = to_string(i), s2 = s1;
            reverse(s2.begin(), s2.end());
            long rev = stol((s1 + s2));
            for (long x = maxValue; x * x >= rev; x--){
                if (rev % x == 0) return (int)(rev % 1337);
            }
        }
        return -1;
    }
} // namespace leetcode