//
// Created by vincent on 20-2-4.
//

namespace vincent
{
    /**逆序创建单链表**/
    void VList::vCreateListReverse(int n) {

        LinkList L = new LNode();
        L->next = nullptr;
        for (int i = n; i > 0; --i){
            LinkList p = new LNode();
            std::cin >> p->val;
            p->next = L->next;
            L->next = p;
        }
        return L->next;
    }

/**顺序创建单链表**/
    vLinkList VList::vCreateListOrder(int n) {

        vLinkList L, p;
        L = new vLNode();
        p = L;
        for (int i = n; i > 0; --i){
            vLinkList temp = new vLNode();
            std::cin >> temp->val;
            p->next = temp;
            p = temp;
        }
        p->next = nullptr;
        return L->next;
    }

// 顺序遍历并打印链表
    void VList::vPrintList(vLinkList pHead) {
        vLinkList pNode = pHead;
        while(pNode != nullptr){
            std::cout << pNode->next << " ";
            pNode = pNode->next;
        }
        std::cout << std::endl;
    }

/**O(1)时间内删除单链表中某一节点**/
    void VList::vDeleteNode(vLinkList *pListHead, vLinkList pTobeDeleted) {
        if (!pListHead || !pTobeDeleted)
            return;
        // 要删除的不是尾节点
        if (pTobeDeleted->next) {
            LinkList pNext = pTobeDeleted->next;
            pTobeDeleted->val = pNext->val;
            pTobeDeleted->next = pNext->next;

            delete pNext;
            pNext = nullptr;
        }

            // 链表只有一个节点，删除头节点（也是尾节点）
        else if (*pListHead == pTobeDeleted) {
            delete pTobeDeleted;
            pTobeDeleted = nullptr;
            *pListHead = nullptr;
        }

            // 链表中有多个节点，删除尾节点，此时仍需顺序遍历链表。
        else {
            vLinkList pNode = *pListHead;
            while (pNode->next != pTobeDeleted)
                pNode = pNode->next;

            pNode->next = nullptr;
            delete pTobeDeleted;
            pTobeDeleted = nullptr;
        }
    }
} // namespace vincent