//
// Created by vincent on 20-2-4.
//

#include "solution.h"

/**
 * 142. 环形链表 II
 * 方法：快慢指针
 * 首先使用快慢指针判断链表是否存在环，不存在则返回null,存在则返回相遇点。
 * 然后使用双指针，一个从head开始，另一个从相遇点开始，同时移动，二者相遇点即为环入口处。
 * **/

namespace leetcode
{

    ListNode *getCycle(ListNode *head)
    {
        ListNode *quick_ptr = head;
        ListNode *slow_ptr = head;

        while (slow_ptr != NULL && quick_ptr->next != NULL){
            slow_ptr = slow_ptr->next;
            quick_ptr = quick_ptr->next;
            if (quick_ptr != NULL)
                quick_ptr = quick_ptr->next;
            if (slow_ptr == quick_ptr)
                return slow_ptr;
        }

        return NULL;
    }

    ListNode * Solution::detectCycle(ListNode *head)
    {
        ListNode *ptr = getCycle(head);

        if (ptr == NULL) return NULL;

        ListNode *ptr1 = head;
        ListNode *ptr2 = ptr;

        while (ptr1 != ptr2){
            ptr1 = ptr1->next;
            ptr2 = ptr2->next;
        }

        return ptr1;
    }

} // namespace leetcode