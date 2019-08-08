/*
 * DeleteDuplication_18_2.h
 *
 *  Created on: Aug 8, 2019
 *      Author: vincent
 */

#ifndef DELETEDUPLICATION_18_2_H_
#define DELETEDUPLICATION_18_2_H_

#include "../vPackage/includes/vList.h"

/**
 * 测试用例：
 * 1. 功能测试
 * 2. 特殊输入测试（指向头结点的指针为nullptr指针，链表中的所有节点都是重复的）
 * **/

void DeleteDuplication(ListNode** pHead){
	if (pHead == nullptr || *pHead == nullptr)
		return;
	// 指向当前节点的前一个节点的指针
	ListNode* pPreNode = nullptr;
	// 指向当前接节点的指针，注意此处的pHead为二级指针。
	ListNode* pNode = *pHead;
	while(pNode != nullptr){
		ListNode* pNext = pNode->m_pNext;

		// 判断当前节点是否为重复节点
		bool needDelete = false;
		if (pNext != nullptr && pNext->m_nValue == pNode->m_nValue)
			needDelete = true;

		if (!needDelete){
			pPreNode = pNode;
			pNode = pNode->m_pNext;
		}
		else{
			int value = pNode->m_nValue;
			ListNode* pToBeDel = pNode;
			while(pToBeDel != nullptr && pToBeDel->m_nValue == value){
				pNext = pToBeDel->m_pNext;

				delete pToBeDel;
				pToBeDel = nullptr;

				pToBeDel = pNext;
			}

			if (pPreNode == nullptr)
				*pHead = pNext;
			else
				pPreNode->m_pNext = pNext;
			pNode = pNext;
		}
	}
}

#endif /* DELETEDUPLICATION_18_2_H_ */
