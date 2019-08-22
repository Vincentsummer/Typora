package Greedy;

/**
 * 659. 分割数组为连续子数组
 * 输入一个按升序排序的整数数组（可能包含重复数字），
 * 你需要将它们分割成几个子序列，其中每个子序列至少包含三个连续整数。返回你是否能做出这样的分割？
 * **/

import java.util.HashMap;

public class IsPossible {
	// 贪心：
	// 1.先尽可能多的构建子序列 
	// 2.在尽可能满足 1 的基础上,尽可能长的构建子序列
    public boolean isPossible(int[] nums) {
        int n = nums.length;
        if (n < 3)
        	return false;
    	HashMap<Integer, Integer> countMap = new HashMap<>(); // 记录 k 剩余的个数
    	HashMap<Integer, Integer> recordMap = new HashMap<>();  // 记录以 k 结尾的连续子序列的个数
    	for (int num : nums) {
    		if (!countMap.containsKey(num))
    			countMap.put(num, 1);
    		else
    			countMap.put(num, countMap.get(num) + 1);
    	}
    	
    	for (int num : nums) {
    		if (countMap.get(num) == 0)
    			continue;
    		countMap.put(num, countMap.get(num) - 1);
    		if (recordMap.containsKey(num-1) && recordMap.get(num-1) > 0) {
    			recordMap.put(num-1, recordMap.get(num-1) - 1);
    			int tmp = recordMap.containsKey(num) ? recordMap.get(num)+1 : 1;
    			recordMap.put(num, tmp);
    			}
    		else if (countMap.containsKey(num+1) && countMap.containsKey(num+2) && countMap.get(num+1) > 0 && 
    				countMap.get(num+2) > 0) {
    			countMap.put(num+1, countMap.get(num+1) - 1);
    			countMap.put(num+2, countMap.get(num+2) - 1);
    			int tmp = recordMap.containsKey(num+2) ? recordMap.get(num+2)+1 : 1;
    			recordMap.put(num+2, tmp);
    		}
    		else
    			return false;
    	}
    	return true;
    }
}
