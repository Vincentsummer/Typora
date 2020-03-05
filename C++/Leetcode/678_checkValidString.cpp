//
// Created by vincent on 20-2-11.
//

#include "solution.h"

/**
 * 678. 有效的括号字符串
 * 方法1：stack + deque (vincent)    O(N) & O(N)
 * 方法2：双向遍历　O(N) & O(1)
 * **/

namespace leetcode
{
    bool Solution::checkValidString(string s)
    {
        stack<int> left;
        deque<int> start;
        for (int i = 0; i < s.size(); ++i){
            if (s[i] == '(')    left.push(i);
            else if (s[i] == '*')    start.push_back(i);
            else{
                if (left.empty()){
                    if (start.empty()) return false;
                    else start.pop_front();
                }
                else left.pop();
            }
        }

        if (left.size() > start.size()) return false;

        while (!left.empty()){
            int le = left.top(), st = start.back();
            left.pop();
            start.pop_back();
            if (le > st)    return false;
        }
        return true;
    }

    bool checkValidString(string s) {
        int bucket = 0;
        int N = s.size();
        for (int i = 0; i < N; ++i) {
            if (s[i] == '(') {
                ++bucket;
            } else {
                --bucket;
                if (bucket < 0) bucket = 0;
            }
        }
        if (bucket > 0) return false;
        for (int i = N - 1; i >= 0; --i) {
            if (s[i] == ')') {
                ++bucket;
            } else {
                --bucket;
                if (bucket < 0) bucket = 0;
            }
        }
        return bucket == 0;
    }
} // namespace leetcode