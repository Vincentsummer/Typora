//
// Created by vincent on 20-2-21.
//

#include "solution.h"

/**
 * 342. 4的幂
 * 方法一：移位 （vincent） O(n) & O(1)
 * 方法二：位操作  O(1) & O(1)
 *       若 x 为 2 的偶数次幂 即 x & 0xaaaaaaaa == 0。
 * 方法三：操作 + 数学运算  O(1) & O(1)
 *      若 x 为 2 的幂且 x % 3 == 1，则 x 为 4 的幂。
 * **/

namespace leetcode {
    bool Solution::isPowerOfFour(int num) {
        long base = 1;
        while (num > base)
            base <<= 2;
        return num == base;
    }

    bool isPowerOfFour1(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0xaaaaaaaa) == 0;
    }

    bool isPowerOfFour2(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num % 3) == 1;
    }
}