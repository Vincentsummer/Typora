//
// Created by vincent on 20-2-13.
//

#include "solution.h"

/**
 * 650. 只有两个键的键盘
 * 方法1： 动态规划 （vincent）  O(1000 * 素数个数) & O(N)
 * 方法2： 素数分解 (官方)       O(sqrt(N)) & O(1)
 * **/

namespace leetcode
{

    void getPrime(vector<int> &primes)
    {
        for (int i = 2; i <= 1000; ++i){
            int j = (int) sqrt(i);
            while (j > 1){
                if (i % j == 0) break;
                j--;
            }
            if (j == 1) primes.push_back(i);
        }
    }

    int Solution::minSteps(int n)
    {
        if (n == 1) return 0;
        vector<int> primes;
        int dp[1001];
        for (int i = 0; i <= 1000; ++i)
            dp[i] = INT16_MAX;

        getPrime(primes);
        for (int i = 2; i <= 1000; ++i) {
            for (int j = 0; j < primes.size(); ++j) {
                if (i <= primes[j]) {
                    if (i == primes[j])
                        dp[i] = primes[j];
                    break;
                }
                if (i % primes[j] == 0) {
                    int k = i / primes[j];
                    dp[i] = min(dp[i], primes[j] + dp[k]);
                }
            }
        }
        return dp[n];
    }

    int minSteps(int n)
    {
        int res = 0, d = 2;
        while (n > 1){
            while (n % d == 0){
                res += d;
                n /= d;
            }
            d++;
        }
        return res;
    }
} // namespace leetcode