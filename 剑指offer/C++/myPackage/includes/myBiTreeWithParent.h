#ifndef MYBITREEWITHPARENT_H_
#define MYBITREEWITHPARENT_H_

#include <iostream>
#include <cstring>
#include "myBinaryTree.h"


// 带有父节点指针的二叉树
class TreeNode{
public:
	int 	data;
	TreeNode *left, *right, *parent;
};

typedef TreeNode *TNode;


// 创建二叉树
void creatBiTreeWithParent(TNode &T, TNode &parent){
	// 按先序次序输入二叉树中节点的值（一个数字），空字符表示空树。
	// 构造二叉链表表示的二叉树T
	T = new TreeNode;
	if (T == parent)
		T->parent = nullptr;
	else{
		T->parent = parent;
		std::cout << parent->data << std::endl;
	}
	std::string s;
	// 接受输入空格
	std::cin >> s;

	if (s == "#")
		T = nullptr;
	else{
		T = new TreeNode();		// 生成根结点
		bool flag = false;
		while(!flag){
			try{
				T->data = stoi(s);
				flag = true;
			}catch(std::invalid_argument&){
				flag = false;
				std::cout << "Invalid_argument！Please Input again: " << std::endl;
				std::cin >> s;
			}
		}

		creatBiTreeWithParent(T->left, T); 		// 构造左子树
		creatBiTreeWithParent(T->right, T); 	// 构造右子树
	}
	return;
}

// 前序遍历
void preOrderTraverse(TNode T){
	if (T != nullptr){
		std::cout << T->data << " ";
		preOrderTraverse(T->left);
		preOrderTraverse(T->right);
	}
}

void preOrderWithParent(TNode T){
	if (T != nullptr){
		if (T->parent != nullptr)
			std::cout << T->parent->data << " ";
		preOrderTraverse(T->left);
		preOrderTraverse(T->right);
	}
}

#endif /* MYBITREEWITHPARENT_H_ */
