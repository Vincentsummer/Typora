//
// Created by vincent on 20-2-21.
//

#include "solution.h"

/**
 * 24. 两两交换链表中的节点
 * 创建一个头结点指向head
 * **/

namespace leetcode
{
    ListNode * Solution::swapPairs(ListNode *head)
    {
        ListNode *p = new ListNode(0);
        p->next = head;
        for (ListNode *q = p; head && head->next; q = head, head = head->next){
            q->next = head->next;
            head->next = q->next->next;
            q->next->next = head;
        }
        head = p->next;
        delete p;
        return head;
    }
} // namespace leetcode