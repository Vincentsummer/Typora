package Backtrack;

/**
 * 565. 数组嵌套
 * **/

public class ArrayNesting_565 {
    public int arrayNesting(int[] nums) {
        int n = nums.length, res = 0;
        boolean record[] = new boolean[n];
    	for (int i = 0; i < n; ++i) {
    		 int count = 1;
    		if (!record[nums[i]]) {
        		record[nums[i]] = true;
        		int j = nums[i];
        		while (!record[nums[j]]) {
        			count++;
        			record[nums[j]] = true;
        			j = nums[j];
        		}
    		}
    		res = Math.max(res, count);
    		if (res > n / 2)
    			return res;
    	}
    	return res;
    }
}
