/**
 * ��������;
 * 1. ���ܲ��ԣ�����������ж���ڵ��һ���ڵ㡣
 * 2. ����������ԣ����������ͷ�ڵ�ָ��Ϊnullptr��
 * **/

#include <iostream>
#include <stack>

#include "../vPackage/includes/vList.h"

// ջʵ��
void printListReversingly_Stack(LinkList pHead){
	std::stack<LinkList> nodes;

	LinkList pNode = pHead;
	while (pNode != nullptr){
		nodes.push(pNode);
		pNode = pNode->m_pNext;
	}

	while (!nodes.empty()){
		pNode = nodes.top();
		printf("%d ", pNode->m_nValue);
		nodes.pop();
	}
}

// �ݹ�ʵ��
void printListReversingly_Recursively(LinkList pHead){
	if (pHead != nullptr){
		if (pHead->m_pNext != nullptr)
			printListReversingly_Recursively(pHead->m_pNext);
		printf("%d ", pHead->m_nValue);
	}
}
