#ifndef VBINARYTREE_H_
#define VBINARYTREE_H_

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
	// �������������������нڵ��ֵ��һ�����֣������ַ���ʾ������
	// ������������ʾ�Ķ�����T
	T = new BiTNode;
	std::string s;
	// ��������ո�
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
				std::cout << "Invalid_argument��Please Input again: " << std::endl;
				std::cin >> s;
			}
		}
		creatBitree(T->m_pLeft); 	// ����������
		creatBitree(T->m_pRight); 	// ����������
	}
}

void preOrderTraverse(BiTree T){
	if (T != nullptr){
		std::cout << T->m_nValue << " ";
		preOrderTraverse(T->m_pLeft);
		preOrderTraverse(T->m_pRight);
	}
}
#endif /* VBINARYTREE_H_ */
