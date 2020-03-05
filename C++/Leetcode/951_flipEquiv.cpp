//
// Created by vincent on 20-2-3.
//

#include "solution.h"

/**
 * 951. 翻转等价二叉树
 * 方法：递归
 * 若二叉树 root1，root2 根节点值相等，那么只需要检查其孩子是否相等即可。
 * **/

namespace leetcode
{
    bool Solution::flipEquiv(TreeNode *root1, TreeNode *root2)
    {
        if (root1 == root2)
            return true;
        if (root1 == NULL || root2 == NULL || root1->val != root2->val)
            return false;

        return (flipEquiv(root1->left, root2->left) && flipEquiv(root1->right, root2->right)) ||
                (flipEquiv(root1->left, root2->right) && flipEquiv(root1->right, root2->left));
    }
} // namesapce leetcode