//
// Created by vincent on 20-2-27.
//

#include "solution.h"

/**
 * 150. 逆波兰表达式求值
 * 栈
 * **/

namespace leetcode
{
    bool isExp(string e)
    {
        return e == "+" || e == "-" || e == "*" || e == "/";
    }

    int Solution::evalRPN(vector<string> &tokens)
    {
        stack<int> aux;
        int a, b;
        for (auto num : tokens){
            if (isExp(num)){
                a = aux.top();
                aux.pop();
                b = aux.top();
                aux.pop();
                if (num == "+")
                    aux.push(b + a);
                else if (num == "-")
                    aux.push(b - a);
                else if (num == "*")
                    aux.push(b * a);
                else if (num == "/")
                    aux.push(b / a);
            }
            else
                aux.push(stoi(num));
        }
        return aux.top();
    }
} // namespace leetcode