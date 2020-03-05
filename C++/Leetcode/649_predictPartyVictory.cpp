//
// Created by vincent on 20-2-2.
//

/**
 * 649 dota参议院
 * 贪心策略
 * 思路：参议员不需要在轮到自己的时候就立即使用禁令，可以等待另一个阵营的参议员投票时再使用。
 * 算法：
 * 使用一个整数队列表示所有的参议员：1 代表 'Radiant' 阵营；0 代表 'Dire' 阵营。然后
 * 遍历队列：对于当前队头的参议员，如果另外一个阵营有禁令，则禁止当前参议员的权利；如果另外
 * 一个阵营没有禁令，则该参议员所在阵营的禁令数量加 1。
 * 时间空间复杂度 : O(N) & O(N)
 * **/

#include "solution.h"
namespace leetcode
{
    string Solution::predictPartyVictory(string senate)
    {
        queue<int> q;
        int dota[] = {0,0};
        int bans[] = {0,0};

        for (auto s : senate){
            int x = s == 'R' ? 1 : 0;
            dota[x]++;
            q.push(x);
        }
        while (dota[0] > 0 && dota[1] > 0) {
            int x = q.front();
            q.pop();
            if (bans[x] > 0){
                dota[x]--;
                bans[x]--;
            }else{
                bans[x^1]++;
                q.push(x);
            }

            if (dota[0] <= bans[0]) return "Radiant";
            if (dota[1] <= bans[1]) return "Dire";
        }

        return dota[0] == 0 ? "Radiant" : "Dire";
    }
} // namespace leetcode