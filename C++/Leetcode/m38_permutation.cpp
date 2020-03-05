//
// Created by vincent on 20-2-15.
//

#include "solution.h"

/**
 * 面试题38. 字符串的排列
 * 排列树 回溯
 * **/

namespace leetcode
{
    set<string> resSet;

    void swap(string &s, int idx1, int idx2){
        char tmp = s[idx1];
        s[idx1] = s[idx2];
        s[idx2] = tmp;
    }

    void backtrack(string &s, int t){
        if (t >= s.size()){
            if (resSet.find(s) == resSet.end())
                resSet.insert(s);
            return;
        }

        for (int i = t; i < s.size(); ++i){
            swap(s, i, t);
            backtrack(s, t+1);
            swap(s, i, t);
        }
    }


    vector<string> Solution::permutation(string s)
    {
        backtrack(s, 0);
        return vector<string>(resSet.begin(), resSet.end());
    }

    void iterativeBacktrack(string s)
    {
        int t = 0;
        while (t >= 0){
            for (int i = 0; i < s.size(); ++i){
                swap(s, i, t);

            }
        }
    }

    vector<string> permutation(string s)
    {

    }
} //namespace leetcode