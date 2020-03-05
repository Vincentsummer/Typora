//
// Created by vincent on 20-2-4.
//

#ifndef CPP_VTREE_H
#define CPP_VTREE_H

#include <iostream>
#include <cstring>
#include <string>
#include <stack>
#include <vector>

using namespace std;

namespace vincent
{
    typedef struct NTree{
        int val;
        vector<NTree*> children;
        NTree(){}
        NTree(int x) {val = x;}
        NTree(int x, vector<NTree*> ch){val = x; children = ch;}
    }Node;

    /// 二叉树结构体
    typedef struct TreeNode{
        int val;
        TreeNode *left;
        TreeNode *right;

        TreeNode() : val(0), left(NULL), right(NULL){}
        TreeNode(int x) : val(x), left(NULL), right(NULL){}
    }vBiTNode, *vBiTree;


    /// 带父节点指针的二叉树结构体
    typedef struct TreePNode{
        int data;
        TreePNode *left, *right, *parent;
    } *vPNode, vTreeNode;

    class VBinaryTree
    {
    public:
        /// 创建二叉树
        void vCreateTree(vBiTree &T);

        /// 先序遍历二叉树
        void vPreOrderTraversal(vBiTree T);         // 递归
        void vPreOrderIterater(vBiTree T);          // 非递归

        /// 中序遍历二叉树
        void vInorderTraversal(vBiTree T);          // 递归
        void vInorderIterater(vBiTree T);           // 非递归

        /// 后序遍历二叉树
        void vPostorderTraversal(vBiTree T);        // 递归
        void vPostorderIterater(vBiTree T);         // 非递归

    private:
    };

    class VBParentTree
    {
    public:
        void vCreateTree(vPNode &T, vPNode &parent);
        void vPreOrderTraversal(vPNode T);
        void vPreOrderWithParent(vPNode T);
    private:
    };
} // namespace vincent

#endif //CPP_VTREE_H
