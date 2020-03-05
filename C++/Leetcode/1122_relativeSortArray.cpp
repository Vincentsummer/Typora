//
// Created by vincent on 20-2-12.
//

#include "solution.h"

/**
 * 1122. 数组的相对排序
 * 使用map记录数字及其出现的频率
 * **/

namespace leetcode
{
    vector<int> Solution::relativeSortArray(vector<int> &arr1, vector<int> &arr2)
    {
        vector<int> res;
        map<int, int> m;    //<num,count>;
        for (auto n: arr1) ++m[n];
        for (auto a : arr2) {
            while (m[a]--) res.push_back(a);
            m.erase(a);
        }
        for (map<int, int>::iterator it = m.begin(); it != m.end(); ++it) {
            while ((it->second)--) res.push_back(it->first);
        }
        return res;
    }
} // namespace leetcode