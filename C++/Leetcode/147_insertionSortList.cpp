//
// Created by vincent on 20-2-29.
//

#include "solution.h"

/**
 * 147. 对链表进行插入排序
 * 指针
 * **/

namespace leetcode
{
    ListNode * Solution::insertionSortList(ListNode *head)
    {
        ListNode *node = new ListNode(0);
        node->next = head;
        ListNode *cur = head;
        while(cur && cur->next){
            ListNode *p = cur->next, *pre = node, *q = pre->next;
            while (q != p){
                if (q->val > p->val){
                    cur->next = p->next;
                    p->next = q;
                    pre->next = p;
                    break;
                }
                pre = q;
                q = q->next;
            }
            if (q == p) cur = cur->next;
        }
        head = node->next;
        delete(node);
        return head;
    }
} // namespace leetcode