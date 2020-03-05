//
// Created by vincent on 20-2-29.
//

#include "solution.h"

/**
 * 851. 喧闹和富有
 * DFS + 记忆化搜索
 * **/

namespace leetcode
{
    int dfs(int cur, const vector<vector<int> > &g, vector<int> &answer, const vector<int> quiet)
    {
        if (answer[cur] == -1){
            answer[cur] = cur;
            for (int v : g[cur]){
                int q = dfs(cur, g, answer, quiet);
                if (quiet[answer[cur]] > quiet[q]) answer[cur] = q;
            }
        }
        return answer[cur];
    }

    vector<int> Solution::loudAndRich(vector<vector<int>> &richer, vector<int> &quiet)
    {
        int n = quiet.size();
        vector<vector<int> > g(n);
        vector<int> answer(n, -1);
        // 邻接表
        for (auto v : richer)
            g[v[1]].push_back(v[0]);

        for (int i = 0; i < n; i++)
            dfs(i, g, answer, quiet);

        return answer;
    }
} // namespace leetcode