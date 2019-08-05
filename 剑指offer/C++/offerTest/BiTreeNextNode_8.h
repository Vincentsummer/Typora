#ifndef BITREENEXTNODE_8_H_
#define BITREENEXTNODE_8_H_

#include "../myPackage/includes/myBiTreeWithParent.h"

/**
 * 测试用例：
 * 1. 普通二叉树（完全二叉树，不完全二叉树）
 * 2. 特殊二叉树（代码中的几种情况）
 * 3. 不同位置的节点的下一个节点
 * **/

TNode GetNext(TNode pNode){
	if (pNode == nullptr)
		return nullptr;

	TNode pNext = nullptr;
	// 若该节点有右子树，则next为其右子树的最左子节点
	if (pNode->right != nullptr){
		TNode pRight = pNode->right;
		while(pRight->left != nullptr)
			pRight = pRight->left;

		pNext = pRight;
	}

	// 若该节点无右子树且有父节点，分两种情况
	// 1. 若其为父节点的左子节点，则next为父节点
	// 2. 若其为父节点的右子节点，则沿着指向父节点的指针向上遍历，直到找到一个是它父节点的左子节点的节点。
	// 则该节点的父节点即为next。
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
