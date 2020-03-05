//
// Created by vincent on 20-2-28.
//

#include "solution.h"

/**
 * 面试题 04.01. 节点间通路
 * BFS + 邻接表
 * **/

namespace leetcode
{
    bool Solution::findWhetherExistsPath(int n,
                                         vector<vector<int>> &graph,
                                         int start,
                                         int target)
    {
        vector<vector<int>> g(n);   // 邻接表

        for (vector<int> v : graph) {
            g[v[0]].push_back(v[1]);
        }

        vector<bool> visited(n, false);
        queue<int> aux;
        aux.push(start);
        while (!aux.empty()){
            int cur = aux.front();
            aux.pop();
            if (cur == target) return true;
            visited[cur] = true;
            for (int next : g[cur]){
                if (next == target) return true;
                if (!visited[next]) aux.push(next);
            }
        }
        return false;
    }
} // namespace leetcode