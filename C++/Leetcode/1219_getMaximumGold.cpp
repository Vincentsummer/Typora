//
// Created by vincent on 20-2-2.
//

#include "solution.h"

/**
 * 1219. 黄金矿工
 * 深度优先遍历/回溯
 * **/

namespace leetcode
{
    int result = 0;
    int m, n;
    int dx[4] = {-1, 0, 1, 0}, dy[4] = {0, 1, 0, -1};
    vector<vector<int>> g;

    bool isArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n) && g[x][y];
    }

    void backtrace(int x, int y, int gold){

        gold += g[x][y];
        result = max(result, gold);
        int t = g[x][y];
        g[x][y] = 0;

        for (int i = 0; i < 4; i++){
            int nextx = x + dx[i], nexty = y + dy[i];
            if (isArea(nextx, nexty))
                backtrace(nextx, nexty, gold);
        }
        g[x][y] = t;
    }

    int Solution::getMaximumGold(vector<vector<int>>& grid)
    {
        m = static_cast<int>(grid.size());
        n = static_cast<int>(grid[0].size());

        g = grid;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] != 0)
                    backtrace(i, j, 0);
            }
        }

        return result;
    }

} // namespace leetcode