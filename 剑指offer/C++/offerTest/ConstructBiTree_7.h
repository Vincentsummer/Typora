/**
 * 测试用例：
 * 普通二叉树（完全二叉树，不完全二叉树）
 * 特殊二叉树（所有节点无右/左节点；只有一个节点）
 * 特殊输入测试（二叉树根结点指针为nullptr, 输入的前序遍历和后序遍历不匹配）
 * **/
#ifndef CONSTRUCTBITREE_7_H_
#define CONSTRUCTBITREE_7_H_

#include <exception>
#include "../myPackage/includes/myBinaryTree.h"

BiTree ConstructCore(int* startPreorder, int* endPreorder, int* startInorder, int* endInorder){
	// 前序遍历序列的第一个数字是根结点的值
	int rootValue = startPreorder[0];
	BiTree root = new BiTNode();
	root->m_nValue = rootValue;
	root->m_pLeft = nullptr;
	root->m_pRight = nullptr;

	// 二叉树只含有一个节点
	if (startPreorder == endPreorder){
		if (startInorder == endInorder && *startPreorder == *startInorder)
			return root;
		else
			throw "Invalid input.";
	}

	// 在中序遍历中寻找根结点的值
	int* rootInorder = startInorder;
	while (rootInorder <= endInorder && *rootInorder != rootValue)
		++rootInorder;

	if (rootInorder == endInorder && *rootInorder != rootValue)
		throw "Invalid input.";

	int leftLength = rootInorder - startInorder;
	int* leftPreorderEnd = startPreorder + leftLength;
	if (leftLength > 0){
		// 构建左子树
		root->m_pLeft = ConstructCore(startPreorder+1, leftPreorderEnd, startInorder, rootInorder-1);
	}
	if (leftLength < endPreorder - startPreorder){
		// 构建右子树
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
