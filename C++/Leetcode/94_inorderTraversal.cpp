//
// Created by vincent on 20-2-16.
//

#include "solution.h"

/**
 * 94. 二叉树的中序遍历
 * **/

namespace leetcode
{
    vector<int> Solution::inorderTraversal(TreeNode *root)
    {
        stack<TreeNode*> aux;
        vector<int> res;
        TreeNode* cur = root;
        while (cur || !aux.empty()){
            while (cur){
                aux.push(cur);
                cur = cur->left;
            }
            cur = aux.top();
            res.push_back(cur->val);
            aux.pop();
            cur = cur->right;
        }
        return res;
    }
} // namespace leetcode