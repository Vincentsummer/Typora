//
// Created by vincent on 20-2-6.
//

#include "solution.h"

/**
 * 476. 数字的补数
 * 位运算
 * **/

namespace leetcode
{

     int Solution::findComplement(int num)
     {
         long base = 1;

         while(num >= base)
             base <<= 1;

         return base - num - 1;

     }
} // namespace leetcode