#ifndef BITREENEXTNODE_8_H_
#define BITREENEXTNODE_8_H_

#include "../vPackage/includes/vBiTreeWithParent.h"

/**
 * ����������
 * 1. ��ͨ����������ȫ������������ȫ��������
 * 2. ����������������еļ��������
 * 3. ��ͬλ�õĽڵ����һ���ڵ�
 * **/

TNode GetNext(TNode pNode){
	if (pNode == nullptr)
		return nullptr;

	TNode pNext = nullptr;
	// ���ýڵ�������������nextΪ���������������ӽڵ�
	if (pNode->right != nullptr){
		TNode pRight = pNode->right;
		while(pRight->left != nullptr)
			pRight = pRight->left;

		pNext = pRight;
	}

	// ���ýڵ������������и��ڵ㣬���������
	// 1. ����Ϊ���ڵ�����ӽڵ㣬��nextΪ���ڵ�
	// 2. ����Ϊ���ڵ�����ӽڵ㣬������ָ�򸸽ڵ��ָ�����ϱ�����ֱ���ҵ�һ���������ڵ�����ӽڵ�Ľڵ㡣
	// ��ýڵ�ĸ��ڵ㼴Ϊnext��
	else if (pNode->parent != nullptr){
		TNode pCurrent = pNode;
		TNode pParent = pNode->parent;
		while (pParent != nullptr && pCurrent == pParent->right){
			pCurrent = pParent;
			pParent = pParent->parent;
		}
		pNext = pParent;
	}

	return pNext;
}

#endif /* BITREENEXTNODE_8_H_ */
