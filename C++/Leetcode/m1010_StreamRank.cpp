//
// Created by vincent on 20-3-2.
//

#include "solution.h"

/**
 * 面试题 10.10. 数字流的秩
 * **/

namespace leetcode
{
    void StreamRank::track(int x)
    {
        number[x]++;
    }

    int StreamRank::getRankOfNumber(int x)
    {
        int res = 0;
        for (int i = 0; i <= x; i++)
            res += number[i];
        return res;
    }
} // namespace leetcode