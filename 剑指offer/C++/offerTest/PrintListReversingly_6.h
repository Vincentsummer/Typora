/**
 * 测试用例;
 * 1. 功能测试：输入的链表有多个节点或一个节点。
 * 2. 特殊输入测试：输入的链表头节点指针为nullptr。
 * **/

#include <iostream>
#include <stack>

#include "../myPackage/includes/myList.h"

// 栈实现
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

// 递归实现
void printListReversingly_Recursively(LinkList pHead){
	if (pHead != nullptr){
		if (pHead->m_pNext != nullptr)
			printListReversingly_Recursively(pHead->m_pNext);
		printf("%d ", pHead->m_nValue);
	}
}
