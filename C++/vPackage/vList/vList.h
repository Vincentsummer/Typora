#ifndef VLIST_H_
#define VLIST_H_

#include <iostream>

namespace vincent
{
	typedef struct ListNode{
		int 		val;
		ListNode	*next;
		ListNode() : val(0), next(NULL) {}
		ListNode(int x) : val(x), next(NULL) {}
	}vLNode, *vLinkList;

	class VList
	{
	public:
		void vCreateListReverse(int n);
		vLinkList vCreateListOrder(int n);
		void vPrintList(vLinkList pHead);
		void vDeleteNode(vLinkList* pListHead, vLinkList pTobeDeleted);
	};
} // namespace vincent

#endif /* VLIST_H_ */
