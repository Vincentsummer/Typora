#ifndef MYBINARYTREE_H_
#define MYBINARYTREE_H_

#include <iostream>
#include <cstring>
#include <string>

class BinaryTreeNode{
public:
	int 			m_nValue;
	BinaryTreeNode* m_pLeft;
	BinaryTreeNode* m_pRight;
};

typedef BinaryTreeNode BiTNode, *BiTree;

void creatBitree(BiTree &T){
	// 按先序次序输入二叉树中节点的值（一个数字），空字符表示空树。
	// 构造二叉链表表示的二叉树T
	T = new BiTNode;
	std::string s;
	// 接受输入空格
	std::cin >> s;
	if (s == "#")
		T = nullptr;
	else{
		T = new BiTNode();
		bool flag = false;
		while(!flag){
			try{
				T->m_nValue = stoi(s);
				flag = true;
			}catch(std::invalid_argument&){
				flag = false;
				std::cout << "Invalid_argument！Please Input again: " << std::endl;
				std::cin >> s;
			}
		}

		creatBitree(T->m_pLeft); 	// 构造左子树
		creatBitree(T->m_pRight); 	// 构造右子树
	}
}

void preOrderTraverse(BiTree T){
	if (T != nullptr){
		std::cout << T->m_nValue << " ";
		preOrderTraverse(T->m_pLeft);
		preOrderTraverse(T->m_pRight);
	}
}

#endif /* MYBINARYTREE_H_ */
