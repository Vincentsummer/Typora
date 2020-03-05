//
// Created by vincent on 20-2-3.
//

#include "solution.h"

/**
 * 731. 我的日程安排表 II
 * 维护两个list,分别是原始记录和二重预订;核心函数是比较两个线段是否重叠及获取重叠区域。
 * 当有一个新的预定时,首先检查二重预定list,如果判断存在重复,则返回false;否则通过与原
 * 始记录list比较,来更新二重预定list,然后将其加入原始记录list。
 * **/

namespace leetcode
{
    MyCalendarTwo::MyCalendarTwo()
    {
        records.clear();
        intersects.clear();
    }

    bool MyCalendarTwo::book(int start, int end)
    {
        interval i;
        i.first = start;
        i.second = end;

        /// 对比二重预定表
        for (auto intersect : intersects){
            interval result = find_repeat(i, intersect);
            if (real_interval(result))
                return false;
        }

        /// 对比原始表
        for (auto record : records){
            interval result = find_repeat(i, record);
            if (real_interval(result))
                intersects.push_back(result);
        }

        records.push_back(i);
        return true;
    }

    /// 寻找重复区间
    MyCalendarTwo::interval MyCalendarTwo::find_repeat(interval &i1, interval &i2)
    {
        interval i;
        i.first = max(i1.first, i2.first);
        i.second = min(i1.second, i2.second);

        return i;
    }

    bool MyCalendarTwo::real_interval(interval &i)
    {
        return i.first < i.second;
    }


} // namspace leetcode