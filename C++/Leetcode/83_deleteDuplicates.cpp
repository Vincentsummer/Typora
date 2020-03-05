//
// Created by vincent on 20-2-7.
//

#include "solution.h"

/**
 * 83 删除排序链表中的重复元素
 * 双指针
 * **/

namespace leetcode
{
    ListNode * Solution::deleteDuplicates(ListNode *head)
    {
        if (head == NULL || head->next == NULL) return head;
        ListNode *pre = head, *cur = head->next;
        while (cur != NULL){
            if (cur->val <= pre->val){
                pre->next = cur->next;
                delete(cur);
                cur = pre->next;
            }
            else{
                cur = cur->next;
                pre = pre->next;
            }
        }
        return head;
    }
} // namespace leetcode