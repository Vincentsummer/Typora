//
// Created by vincent on 20-2-4.
//

#include "vTree.h"

namespace vincent
{
    // 创建二叉树
    void VBinaryTree::vCreateTree(vBiTree &T) {
        T = new vBiTNode();
        std::string s;
        std::cin >> s;
        if (s == "#")
            T = nullptr;
        else{
            T = new vBiTNode();
            bool flag = false;
            while(!flag){
                try{
                    T->val = stoi(s);
                    flag = true;
                }catch(std::invalid_argument&){
                    flag = false;
                    std::cout << "Invalid_argument Please Input again: " << std::endl;
                    std::cin >> s;
                }
            }
            vCreateTree(T->left);
            vCreateTree(T->right);
        }
    }

    /// 二叉树的先序遍历
    // 递归
    void VBinaryTree::vPreOrderTraversal(vBiTree T) {
        if (T != NULL){
            std::cout << T->val << " ";
            vPreOrderTraversal(T->left);
            vPreOrderTraversal(T->right);
        }
    }
    // 非递归
    void VBinaryTree::vPreOrderIterater(vBiTree T)
    {
        std::stack<vBiTree> aux;
        aux.push(T);
        while(T && !aux.empty()){

            vBiTree cur = aux.top();
            std::cout << cur->val << " ";
            aux.pop();
            if (cur->right)
                aux.push(cur->right);
            if (cur->left)
                aux.push(cur->left);
        }
    }

    /// 二叉树的中序遍历
    // 递归
    void VBinaryTree::vInorderTraversal(vBiTree T)
    {
        if (T != NULL){
            vPreOrderTraversal(T->left);
            std::cout << T->val << " ";
            vPreOrderTraversal(T->right);
        }
    }
    // 非递归
    void VBinaryTree::vInorderIterater(vBiTree T)
    {
        std::stack<vBiTree> aux;
        vBiTree cur = T;
        while (cur || !aux.empty()){
            while (cur){
                aux.push(cur);
                cur = cur->left;
            }
            cur = aux.top();
            std::cout << cur->val << " ";
            aux.pop();
            cur = cur->right;
        }
    }

    /// 二叉树的后序遍历
    // 递归
    void VBinaryTree::vPostorderTraversal(vBiTree T)
    {
        if (T != NULL){
            vPreOrderTraversal(T->left);
            vPreOrderTraversal(T->right);
            std::cout << T->val << " ";
        }
    }

    // 非递归
    void VBinaryTree::vPostorderIterater(vBiTree T)
    {
        std::stack<vBiTree> aux;
        TreeNode *cur = T, *pre = nullptr;
        while(cur || !aux.empty()){
            while (cur){
                aux.push(cur);
                cur = cur->left;
            }
            cur = aux.top();
            // 该节点的无右节点或右节点已访问，弹出该节点
            if (!cur->right || cur->right == pre){
                std::cout << cur->val << " ";
                pre = cur;
                cur = nullptr;
                aux.pop();
            }
            else
                cur = cur->right;
        }
    }

    /// 创建带父节点指针的二叉树
    void VBParentTree::vCreateTree(vPNode &T, vPNode &parent) {
        T = new vTreeNode;
        if (T == parent)
            T->parent = nullptr;
        else{
            T->parent = parent;
            std::cout << parent->data << std::endl;
        }
        std::string s;
        std::cin >> s;

        if (s == "#")
            T = nullptr;
        else{
            T = new vTreeNode();
            bool flag = false;
            while(!flag){
                try{
                    T->data = stoi(s);
                    flag = true;
                }catch(std::invalid_argument&){
                    flag = false;
                    std::cout << "Invalid_argument Please Input again: " << std::endl;
                    std::cin >> s;
                }
            }

            vCreateTree(T->left, T);
            vCreateTree(T->right, T);
        }
        return;
    }

    /// 带父节点的先序遍历
    void VBParentTree::vPreOrderTraversal(vPNode T) {
        if (T != nullptr){
            std::cout << T->data << " ";
            vPreOrderTraversal(T->left);
            vPreOrderTraversal(T->right);
        }
    }

    ///
    void VBParentTree::vPreOrderWithParent(vPNode T) {
        if (T != nullptr){
            if (T->parent != nullptr)
                std::cout << T->parent->data << " ";
            vPreOrderWithParent(T->left);
            vPreOrderWithParent(T->right);
        }
    }

} // namespace vincent
