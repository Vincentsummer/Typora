#ifndef VBITREEWITHPARENT_H_
#define VBITREEWITHPARENT_H_

#include <iostream>
#include <cstring>

#include "vBinaryTree.h"


// ���и��ڵ�ָ��Ķ�����
class TreeNode{
public:
	int 	data;
	TreeNode *left, *right, *parent;
};

typedef TreeNode *TNode;


// ����������
void creatBiTreeWithParent(TNode &T, TNode &parent){
	// �������������������нڵ��ֵ��һ�����֣������ַ���ʾ������
	// ������������ʾ�Ķ�����T
	T = new TreeNode;
	if (T == parent)
		T->parent = nullptr;
	else{
		T->parent = parent;
		std::cout << parent->data << std::endl;
	}
	std::string s;
	// ��������ո�
	std::cin >> s;

	if (s == "#")
		T = nullptr;
	else{
		T = new TreeNode();		// ���ɸ����
		bool flag = false;
		while(!flag){
			try{
				T->data = stoi(s);
				flag = true;
			}catch(std::invalid_argument&){
				flag = false;
				std::cout << "Invalid_argument��Please Input again: " << std::endl;
				std::cin >> s;
			}
		}

		creatBiTreeWithParent(T->left, T); 		// ����������
		creatBiTreeWithParent(T->right, T); 	// ����������
	}
	return;
}

// ǰ�����
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

#endif /* VBITREEWITHPARENT_H_ */
