//
// Created by vincent on 20-2-11.
//

#include "solution.h"

/**
 * 1235. 规划兼职工作
 * 排序+动态规划
 * 1. 先按结束时间排序job.
 * 2. 递推公式 : 设dp[i]为ｉ时间点为止可以获取的最大值，则有
 *  当ｉ时间点有job结束时 : dp[i] = max(dp[i-1], job.profit + dp[job.start]); 注意可能多个job同时结束
 *  当ｉ时间点无job结束 : dp[i] = dp[i-1];
 * **/

namespace leetcode
{

    struct Job
    {
        int start, end, profit;
        Job(int s, int e, int p) {start = s; end = e; profit = p;}
    };

    static bool sortFun(const Job &j1, const Job &j2){
        return j1.end < j2.end;
    }

    int Solution::jobScheduling(
            vector<int> &startTime,
            vector<int> &endTime,
            vector<int> &profit)
    {

        int len = (int) startTime.size();
        vector<Job> jobs;
        for (int i = 0; i < len; i++){
            jobs.emplace_back(Job(startTime[i], endTime[i], profit[i]));
        }

        sort(jobs.begin(), jobs.end(), sortFun);

        int maxEnd = jobs[len - 1].end;
        vector<int> dp(maxEnd + 1, 0);
        int maxProfit = 0, d = 0;

        for (int i = 0; i < len; i++){
            auto job = jobs[i];
            if (d == job.end)
                dp[job.end] = max(dp[job.end], job.profit + dp[job.start]);
            else{
                while(d < job.end){
                    dp[d+1] = dp[d];
                    d++;
                }
                dp[job.end] = max(dp[job.end-1], job.profit + dp[job.start]);
            }

            maxProfit = max(maxProfit, dp[job.end]);
        }
        return maxProfit;
    }
} // namespace leetcode