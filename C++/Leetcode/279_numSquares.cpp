//
// Created by vincent on 20-2-28.
//

#include "solution.h"

/**
 * 279. 完全平方数
 * 解法1：四平方定理： 任何一个正整数都可以表示成不超过四个整数的平方之和。
 *      推论：满足四数平方和定理的数n（四个整数的情况），必定满足 n=4^a(8b+7)
 * 解法2：动态规划 O(N*logN) & O(N)
 *      dp[i] = min(d[i], dp[i - j*j] + 1)
 * **/

namespace leetcode
{

    int Solution::numSquares(int n)
    {
        vector<int> dp(n+1);
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++)
                dp[i] = min(dp[i], dp[i - j * j] + 1);
        }
        return dp[n];
    }
} // namespace leetcode