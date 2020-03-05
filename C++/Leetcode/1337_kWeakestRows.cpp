//
// Created by vincent on 20-2-13.
//

#include "solution.h"

/**
 * 1337. 方阵中战斗力最弱的 K 行
 * 自定义排序
 * **/

namespace leetcode
{
    vector<int> Solution::kWeakestRows(vector<vector<int>> &mat, int k)
    {
        vector<int> res, sum;

        for (int i = 0; i < mat.size(); ++i){
            res.push_back(i);
            int count = 0;
            for (int j = 0; j < mat[0].size(); ++j){
                if (mat[i][j] != 1) break;
                count++;
            }
            sum.push_back(count);
        }

        sort(res.begin(), res.end(), [&sum](int a, int b)->bool
        {
            if (sum[a] == sum[b]) return a < b;
            return sum[a] < sum[b];
        });

        res = vector<int>(res.begin(), res.begin() + k);

        return res;
    }
} // namespace leetcode