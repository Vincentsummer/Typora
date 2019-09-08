package Others;

/**
 * 556. 下一个更大元素 III
 * **/

import java.util.ArrayList;
import java.util.List;

public class nextGreaterElement_556 {
	private int res;
    public int nextGreaterElement(int n) {
    	List<Integer> nums = new ArrayList<>();
    	int tmp = n;
    	while (tmp != 0) {
    		nums.add(tmp % 10);
    		tmp /= 10;
    	}
    	int len = nums.size();
    	res = Integer.MAX_VALUE;
    	Backtrack(nums, len-1, n);
    	return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    public void Backtrack(List<Integer> nums, int t, int n) {
    	if (t < 0) {
    		int tmp = 0;
    		for (int i = nums.size() - 1; i >= 0; --i)
    			tmp = 10 * tmp + nums.get(i);
    		if (tmp > n)
    			res = Math.min(res, tmp);
    		return;
    	}
    	
    	for (int i = t; i >= 0; --i) {
    		swap(nums, i, t);
    		Backtrack(nums, t-1, n);
    		swap(nums, i, t);
    	}
    }
    
    public void swap(List<Integer> nums, int idx1, int idx2) {
    	int tmp = nums.get(idx1);
    	nums.set(idx1, nums.get(idx2));
    	nums.set(idx2, tmp);
    }
}
