//
// Created by vincent on 20-2-11.
//

#include "solution.h"

/**
 * 899. 有序队列
 * K > 1时直接排序， K = 1时遍历一次寻找最小序
 * **/

namespace leetcode
{
    string Solution::orderlyQueue(string S, int K)
    {
        string re = S;
        if (K > 1)
            sort(re.begin(), re.end());
        else{
            int len = (int) S.size();
            string::iterator it = S.begin();
            for (int i = 0; i < len; ++i){
                S = S.substr(1, (unsigned long) (len - 1)) + S[0];
                if (re.compare(S) > 0) re = S;
            }
        }
        return re;
    }
} // namespace leetcode