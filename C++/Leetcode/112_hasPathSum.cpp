//
// Created by vincent on 20-2-15.
//

#include "solution.h"

/**
 * 112. 路径总和
 * 深度优先搜索：后序遍历求解路径总和
 * **/

namespace leetcode
{
    bool Solution::hasPathSum(TreeNode *root, int sum)
    {

        stack<TreeNode*> aux;
        TreeNode *pre = nullptr;

        while (root || !aux.empty()){
            while(root){
                aux.push(root);
                sum -= root->val;
                root = root->left;
            }

            root = aux.top();
            if (!root->left && !root->right && sum == 0)
                return true;
            if (!root->right || pre == root->right){
                aux.pop();
                sum += root->val;
                pre = root;
                root = nullptr;
            } else {
                root = root->right;
            }
        }
        return false;
    }
} // namespace leetcode