//
// Created by vincent on 20-2-23.
//

#include "solution.h"

/**
 * 437. 路径总和 III
 * 方法一：双递归
 * 方法二：回溯 + DFS
 * **/

namespace leetcode
{
    int Solution::pathSum(TreeNode *root, int sum)
    {
        if (!root) return 0;
        int res = 0, n = sum;
        stack<TreeNode*> aux;
        TreeNode *p = root, *pre = nullptr;
        while (p || !aux.empty()){
            while (p){
                aux.push(p);
                n -= p->val;
                p = p->left;
                if (n == 0) res++;
            }
            p = aux.top();
            if (!p->right || pre == p->right){
                aux.pop();
                n += p->val;
                pre = p;
                p = nullptr;
            }
            else
                p = p->right;
        }

        return res + pathSum(root->left, sum) + pathSum(root->right, sum);
    }

    // 回溯 + DFS
    int helper(TreeNode *root, unordered_map<int, int> m, int sum, int pathSum)
    {
        if (!root) return 0;
        pathSum += root->val;
        int res = m[pathSum - sum];
        m[pathSum]++;
        res += helper(root->left, m, sum, pathSum) + helper(root->right, m, sum, pathSum);
        m[pathSum]--;
        return res;
    }

    int pathSum(TreeNode *root, int sum)
    {
        unordered_map<int, int> m{{0,1}};
        return helper(root, m, sum ,0);
    }
} // namespace leetcode