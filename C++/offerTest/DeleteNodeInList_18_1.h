/*
 * DeleteNodeInList_18.h
 *
 *  Created on: Aug 8, 2019
 *      Author: vincent
 */

#ifndef DELETENODEINLIST_18_1_H_
#define DELETENODEINLIST_18_1_H_

#include "../vPackage/includes/vList.h"

/**
 * 测试用例：
 * 1. 功能测试
 * 2. 特殊输入测试（指向链表头结点的为nullptr指针，指向待删除节点的为nullptr指针）
 * **/

/**O(1)时间内删除单链表中某一节点**/
void DeleteNode(ListNode** pListHead, ListNode* pTobeDeleted){
	if (!pListHead || !pTobeDeleted)
		return;
	// 要删除的不是尾节点
	if (pTobeDeleted->m_pNext){
		ListNode* pNext = pTobeDeleted->m_pNext;
		pTobeDeleted->m_nValue = pNext->m_nValue;
		pTobeDeleted->m_pNext = pNext->m_pNext;

		delete pNext;
		pNext = nullptr;
	}

	// 链表只有一个节点，删除头节点（也是尾节点）
	else if (*pListHead == pTobeDeleted){
		delete pTobeDeleted;
		pTobeDeleted = nullptr;
		*pListHead = nullptr;
	}

	// 链表中有多个节点，删除尾节点，此时仍需顺序遍历链表。
	else {
		ListNode* pNode = *pListHead;
		while(pNode->m_pNext != pTobeDeleted)
			pNode = pNode->m_pNext;

		pNode->m_pNext = nullptr;
		delete pTobeDeleted;
		pTobeDeleted = nullptr;
	}
}

#endif /* DELETENODEINLIST_18_1_H_ */
