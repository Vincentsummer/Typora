#include <iostream>

typedef struct ListNode{
	int 		m_nValue;
	ListNode* 	m_pNext;
}LNode, *LinkList;

LinkList creatList_Reverse(int n){
	// 逆位序输入n个元素的值，建立带表头节点的单链线性表L
	LinkList L = new LNode();
	L->m_pNext = nullptr;  // 先建立一个带头节点的单链表
	for (int i = n; i > 0; --i){
		LinkList p = new LNode(); // 生成新节点
		std::cin >> p->m_nValue;  // 输入元素值
		p->m_pNext = L->m_pNext;	// 插入到表头
		L->m_pNext = p;
	}
	return L->m_pNext;
}

LinkList creatList_Order(int n){
	//顺序输入n个元素的值，建立带头节点的单链线性表L
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
