//
// Created by vincent on 20-2-16.
//

#include "solution.h"

/**
 * 144. 二叉树的前序遍历
 * **/

namespace leetcode
{
    vector<int> Solution::preorderTraversal(TreeNode *root)
    {
        stack<TreeNode*> aux;
        vector<int> res;
        aux.push(root);
        TreeNode* cur = aux.top();
        while(root && !aux.empty()){
            while (cur){
                res.push_back(cur->val);
                aux.push(cur);
                cur = cur->left;
            }
            cur = aux.top();
            aux.pop();
            if (!cur->right)
                cur = nullptr;
            else
                cur = cur->right;
        }
        return res;
    }

    /// 压栈 中、右，左
    vector<int> preorderTraversal1(TreeNode *root)
    {
        stack<TreeNode*> aux;
        vector<int> res;
        aux.push(root);
        while(root && !aux.empty()){
            TreeNode* cur = aux.top();
            res.push_back(cur->val);
            aux.pop();
            if (cur->right)
                aux.push(cur->right);
            if (cur->left)
                aux.push(cur->left);
        }
        return res;
    }

    /**
     * 莫里斯遍历:
     * 算法的思路是从当前节点向下访问先序遍历的前驱节点，每个前驱节点都恰好被访问两次。
     * 首先从当前节点开始，向左孩子走一步然后沿着右孩子一直向下访问，直到到达一个叶子节
     * 点（当前节点的中序遍历前驱节点），更新输出并建立一条伪边 pre.right = root
     * 更新这个前驱的下一个点。如果我们第二次访问到前驱节点，由于已经指向了当前节点，
     * 我们移除伪边并移动到下一个顶点。
     * 如果第一步向左的移动不存在，就直接更新输出并向右移动。
     * **/
    vector<int> preorderTraversal2(TreeNode *root)
    {
        vector<int> res;
        TreeNode* cur = root;           // 当前开始遍历的节点
        TreeNode* curLeft = nullptr;    // 记录当前结点的左子树
        //找到当前左子树的最右侧节点，且这个节点应该在指向根结点之前，否则整个节点又回到了根结点。
        while (!cur){
            curLeft = cur->left;
            //若最右侧节点的右指针没有指向根结点，创建连接然后往下一个左子树的根结点进行连接操作。
            if (!curLeft){
                while(!curLeft->right && curLeft->right != cur)
                    curLeft = curLeft->right;
                if (!curLeft->right){
                    curLeft->right = cur;
                    cur = cur->left;
                    continue;
                }
                //当左子树的最右侧节点有指向根结点，即已遍历。
                else
                    curLeft->right = nullptr;
            }
            // 一直向右
            cur = cur->right;
        }
    }
} // namespace leetcode