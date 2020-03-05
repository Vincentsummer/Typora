//
// Created by vincent on 20-2-29.
//

#include "solution.h"

/**
 * 1154. 一年中的第几天
 * **/

namespace leetcode
{
    int months[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int Solution::dayOfYear(string date)
    {
        int year = stoi(date.substr(0, 4));
        int month = stoi(date.substr(5, 2));
        int day = stoi(date.substr(8, 2));
        int res = 0;
        if (__isleap(year)) months[1] += 1;
        for (int i = 0; i < month-1; ++i)
            res += months[i];
        res += day;
        return res;
    }
} // namespace leetcode