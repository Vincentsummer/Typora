#include <iostream>

typedef struct ListNode{
	int 		m_nValue;
	ListNode* 	m_pNext;
}LNode, *LinkList;

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
