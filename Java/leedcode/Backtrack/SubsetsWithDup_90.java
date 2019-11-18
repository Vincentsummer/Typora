package Backtrack;

/**
 * 90. 子集 II
 * **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDup_90 {
	
	// 回溯
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        Backtrack(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    
    public void Backtrack(List<List<Integer>> res, List<Integer> addList, int[] nums, int index) {
    	if (index == nums.length)
    		return;
    	res.add(new ArrayList<>(addList));
    	int lastUsed = Integer.MIN_VALUE;
    	for (int i = index; i < nums.length; ++i) {
    		if (nums[i] != lastUsed) {
    			addList.add(nums[i]);
    			Backtrack(res, addList, nums, i+1); 
    			addList.remove(addList.size()-1);
    			lastUsed = nums[i];
    		}
    	}
    }
}
