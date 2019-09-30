import java.util.Comparator;
import java.util.PriorityQueue;

import vList.ListNode;

public class test9 {

	public static void main(String[] args) {
		PriorityQueue<ListNode> aux = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode arg0, ListNode arg1) {
				return arg0.val - arg1.val;
			}
		});
	}

}
