//
// Created by vincent on 20-2-12.
//

#include "solution.h"

/**
 * 1209. 删除字符串中的所有相邻重复项 II
 * 指针
 * **/

namespace leetcode
{
    string::iterator findDuplicates(string &s, const int &k)
    {
        int count = 1;
        string::iterator it = s.begin();
        for (string::iterator tmp = it + 1; it != s.end(); tmp++){
            if (*it == *tmp)    count++;
            else {
                it = tmp;
                count = 1;
            }
            if (count == k) return it;
        }
        return it;
    }

    string Solution::removeDuplicates(string s, int k)
    {
        string::iterator it;
        while ((it = findDuplicates(s, k)) != s.end()){
            s.erase(it, it + k);
        }
        return s;
    }
} // namespace leetcode