//
// Created by vincent on 20-2-16.
//

#include "solution.h"

namespace leetcode
{
    vector<int> Solution::postorderTraversal(TreeNode *root)
    {
        stack<TreeNode*> aux;
        vector<int> res;
        TreeNode *cur = root, *pre = nullptr;
        while(cur || !aux.empty()){
            while (cur){
                aux.push(cur);
                cur = cur->left;
            }
            cur = aux.top();
            // 该节点的无右节点或右节点已访问，弹出该节点
            if (!cur->right || cur->right == pre){
                res.push_back(cur->val);
                pre = cur;
                cur = nullptr;
                aux.pop();
            }
            else
                cur = cur->right;
        }
        return res;
    }
} // namespace leetcode