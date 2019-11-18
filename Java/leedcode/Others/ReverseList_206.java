package Others;

/**
 * 206. 反转链表
 * **/

public class ReverseList_206 {
	// 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。由于节点没有引用其上一个节点，
	// 因此必须事先存储其前一个元素。在更改引用之前，还需要另一个指针来存储下一个节点。
	// 最后返回新的头引用。
	
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
