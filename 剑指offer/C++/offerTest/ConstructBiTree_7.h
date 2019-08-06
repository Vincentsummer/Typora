/**
 * ����������
 * ��ͨ����������ȫ������������ȫ��������
 * ��������������нڵ�����/��ڵ㣻ֻ��һ���ڵ㣩
 * ����������ԣ������������ָ��Ϊnullptr, �����ǰ������ͺ��������ƥ�䣩
 * **/
#ifndef CONSTRUCTBITREE_7_H_
#define CONSTRUCTBITREE_7_H_

#include <exception>

#include "../vPackage/includes/vBinaryTree.h"

BiTree ConstructCore(int* startPreorder, int* endPreorder, int* startInorder, int* endInorder){
	// ǰ��������еĵ�һ�������Ǹ�����ֵ
	int rootValue = startPreorder[0];
	BiTree root = new BiTNode();
	root->m_nValue = rootValue;
	root->m_pLeft = nullptr;
	root->m_pRight = nullptr;

	// ������ֻ����һ���ڵ�
	if (startPreorder == endPreorder){
		if (startInorder == endInorder && *startPreorder == *startInorder)
			return root;
		else
			throw "Invalid input.";
	}

	// �����������Ѱ�Ҹ�����ֵ
	int* rootInorder = startInorder;
	while (rootInorder <= endInorder && *rootInorder != rootValue)
		++rootInorder;

	if (rootInorder == endInorder && *rootInorder != rootValue)
		throw "Invalid input.";

	int leftLength = rootInorder - startInorder;
	int* leftPreorderEnd = startPreorder + leftLength;
	if (leftLength > 0){
		// ����������
		root->m_pLeft = ConstructCore(startPreorder+1, leftPreorderEnd, startInorder, rootInorder-1);
	}
	if (leftLength < endPreorder - startPreorder){
		// ����������
		root->m_pRight = ConstructCore(leftPreorderEnd+1, endPreorder, rootInorder+1, endInorder);
	}

	return root;
}

BiTree Construct(int* preorder, int* inorder, int length){
	if (preorder == nullptr || inorder == nullptr || length <= 0)
		return nullptr;

	return ConstructCore(preorder, preorder + length - 1, inorder, inorder + length - 1);
}

#endif /* CONSTRUCTBITREE_7_H_ */
