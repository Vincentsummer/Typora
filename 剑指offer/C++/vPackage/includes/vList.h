#ifndef VLIST_H_
#define VLIST_H_

#include <iostream>

typedef struct ListNode{
	int 		m_nValue;
	ListNode* 	m_pNext;
}LNode, *LinkList;

/**逆序创建单链表**/
LinkList creatList_Reverse(int n){
	// ��λ������n��Ԫ�ص�ֵ����������ͷ�ڵ�ĵ������Ա�L
	LinkList L = new LNode();
	L->m_pNext = nullptr;  // �Ƚ���һ����ͷ�ڵ�ĵ�����
	for (int i = n; i > 0; --i){
		LinkList p = new LNode(); // �����½ڵ�
		std::cin >> p->m_nValue;  // ����Ԫ��ֵ
		p->m_pNext = L->m_pNext;	// ���뵽��ͷ
		L->m_pNext = p;
	}
	return L->m_pNext;
}

/**顺序创建单链表**/
LinkList creatList_Order(int n){
	//˳������n��Ԫ�ص�ֵ��������ͷ�ڵ�ĵ������Ա�L
	LinkList L, p;
	L = new LNode();
	p = L;
	for (int i = n; i > 0; --i){
		LinkList temp = new LNode();
		std::cin >> temp->m_nValue;
		p->m_pNext = temp;
		p = temp;
	}
	p->m_pNext = nullptr;
	return L->m_pNext;
}

// 顺序遍历并打印链表
void PrintList(LinkList pHead){
	LinkList pNode = pHead;
	while(pNode != nullptr){
		std::cout << pNode->m_nValue << " ";
		pNode = pNode->m_pNext;
	}
	std::cout << std::endl;
}

/**O(1)时间内删除单链表中某一节点**/
void vDeleteNode(LinkList* pListHead, LinkList pTobeDeleted){
	if (!pListHead || !pTobeDeleted)
		return;
	// 要删除的不是尾节点
	if (pTobeDeleted->m_pNext){
		LinkList pNext = pTobeDeleted->m_pNext;
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
		LinkList pNode = *pListHead;
		while(pNode->m_pNext != pTobeDeleted)
			pNode = pNode->m_pNext;

		pNode->m_pNext = nullptr;
		delete pTobeDeleted;
		pTobeDeleted = nullptr;
	}
}
#endif /* VLIST_H_ */
