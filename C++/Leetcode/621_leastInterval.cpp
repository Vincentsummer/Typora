//
// Created by vincent on 20-2-12.
//

#include "solution.h"

/**
 * 621. 任务调度器
 * 贪心策略：完成所有任务的最短时间只与个数最多的任务有关。
 * **/

namespace leetcode
{
    int Solution::leastInterval(vector<char> &tasks, int n)
    {
        if (n == 0) return (int) tasks.size();
        int word[26] = {0}, maxTask = 0;
        for (auto task : tasks){
            int i = task - 'A';
            word[i]++;
            if (maxTask < word[i])
                maxTask = word[i];
        }

        int count = 0;
        for (int i = 0; i < 26; ++i){
            if (word[i] != maxTask) break;
            count++;
        }
        return max((maxTask - 1) * (n + 1) + count, (const int &) tasks.size());

    }
} // namespace leetcode