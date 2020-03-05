//
// Created by vincent on 20-2-4.
//

#include "solution.h"

/**
 * 108. 将有序数组转换为二叉搜索树
 * 二分递归构建BST
 * **/

namespace leetcode
{
    TreeNode* getBST(vector<int> &nums, int start, int end)
    {
        TreeNode *root = new TreeNode();
        if (end - start > 1){
            int cur = (end + start) / 2;
            root->val = nums[cur];
            root->left = getBST(nums, start, cur - 1);
            root->right = getBST(nums, cur + 1, end);
        }else{
            root->val = nums[end];
            if (end != start) root->left = new TreeNode(nums[start]);
        }
        return root;
    }

    TreeNode* Solution::sortedArrayToBST(vector<int> &nums)
    {
        int len = (int) nums.size();
        if (len == 0) return NULL;
        else return getBST(nums, 0, len-1);
    }
} // namespace leetcode