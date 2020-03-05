//
// Created by vincent on 20-2-13.
//

#include "solution.h"

/**
 * 1252. 奇数值单元格的数目
 * 常规解
 * **/

namespace leetcode
{
    int Solution::oddCells(int n, int m, vector<vector<int>> &indices)
    {
        int rows[n] = {0};
        int cols[m] = {0};
        for(auto indice : indices){
            rows[indice[0]] ++;
            cols[indice[1]] ++;
        }
        int res = 0;
        for(int i=0; i<n; i++){
            for(int j = 0; j < m; j++){
                if((rows[i]+cols[j]) % 2)
                    res += 1;
            }
        }
        return res;
    }
} // namespace leetcode