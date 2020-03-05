//
// Created by vincent on 20-2-29.
//

#include "solution.h"

/**
 * 面试题67. 把字符串转换成整数
 * **/

namespace leetcode
{
    int Solution::strToInt(string str)
    {
        bool positive = true;
        int start = 0, end = 0;
        while (str[start] != '+' && str[start] != '-' && !isdigit(str[start])){
            if (str[start] == ' ') start++;
            else return 0;
        }
        if (str[start] == '-') positive = false;
        if (!isdigit(str[start])) start++;
        end = start;
        while (isdigit(str[end])){
            end++;
        }
        string res = str.substr(start, end - start);
        long num = 0;
        for (int j = 0; j < res.size(); j++){
            if (!positive){
                num = 10 * num + (res[j] - '0');
                if (num > INT32_MAX){
                    num = INT32_MAX;
                    break;
                }
            }
            else{
                num = 10 * num - (res[j] - '0');
                if (num < INT32_MIN){
                    num = INT32_MIN;
                    break;
                }
            }
        }
        return num;
    }
} // namespace leetcode