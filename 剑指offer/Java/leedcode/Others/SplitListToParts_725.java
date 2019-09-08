package Others;

/**
 * 725. 分隔链表
 * **/

public class SplitListToParts_725 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int n = 0;
        ListNode[] res = new ListNode[k];
        ListNode p = root;
    	while(p.next != null) {
    		n++;
    		p = p.next;
    	}
    	int num = n / k, diff = n % k;
    	p = root;
    	for (int i = 0; i < k && p != null; ++i) {
    		res[i] = p;
    		int count = num + (diff-- > 0 ? 1 : 0);
    		for (int j = 0; j < count-1; ++j)
    			p = p.next;
    		ListNode next = p.next;
    		p.next = null;
    		p = next;
    	}
    	return res;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }