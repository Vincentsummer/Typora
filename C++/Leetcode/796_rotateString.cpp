//
// Created by vincent on 20-2-5.
//

#include "solution.h"

/**
 * 796. 旋转字符串
 * 方法1 ： 穷举法    O(N^2) & O(1)  (本文方法)
 * 判断 B 是否为 A+A 的子串 ：
 * 方法2 ： 判断子串   O(N^2) & O(N)   直接判断
 * 方法3 : Rabin-Karp 字符串哈希   O(N) & O(N)     优化算法
 * 方法4 : KMP 算法     O(N) & O(N)     优化算法
 * **/

namespace leetcode
{
    bool Solution::rotateString(string A, string B)
    {
        if (A.size() != B.size()) return false;
        int i = 0, n = 0, len = A.size();

        for (; i < len; ++i){
            int j = (n + i) % len;
            if (A[j] != B[i]){
                n = j;
                while(n < len && A[n] != B[0])
                    n++;
                if (n == len && n != 0) return false;
                i = 0;
            }
        }
        return true;
    }
} // namespace leetcode