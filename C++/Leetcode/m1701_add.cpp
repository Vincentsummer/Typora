//
// Created by vincent on 20-3-2.
//

#include "solution.h"

/**
 * 面试题 17.01. 不用加号的加法
 * 位运算：使用异或和与及移位操作
 *  1. 与操作得到需要进位的位，此时相与后左移一位得到进位的结果。
 *  2. 异或操作得到无需进位的位相加后的结果。
 *  重复上述步骤，直到无进位，即为最终结果。
 * **/

namespace leetcode
{
    int Solution::add(int a, int b)
    {
        while (a){
            b ^= a;
            a &= (b^a);
            a <<= 1;
        }
        return b;
    }
} // namespace leetcode