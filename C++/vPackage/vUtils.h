//
// Created by vincent on 20-2-25.
//

#ifndef CPP_VUTILS_H
#define CPP_VUTILS_H

#include <zconf.h>
#include <cstdio>
#include <malloc.h>

namespace vincent
{
    void swap(int *A, int idx1, int idx2);  // 交换数组中的两个值

    void gerCurwd();    // 获取当前工作路径
} // namespace vincent

#endif //CPP_VUTILS_H
