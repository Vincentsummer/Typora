package Backtrack;

/**
 * 216. 组合总和 III
 * **/

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3_216 {
	// 回溯 + 子集树 + 剪枝
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Backtrack(res, n, 10, 1, k, new ArrayList<Integer>());
        return res;
    }
    
    public void Backtrack(List<List<Integer>> res, int n, int num, int t, int k, List<Integer> tmp) {
    	if (t > num)
    		return;
    	
    	if (n == 0 && k == 0) {
    		res.add(new ArrayList<Integer>(tmp));
    		return;
    	}
    	if (n <= 0 || k <= 0)
    		return;
    	
    	tmp.add(t);
    	Backtrack(res, n-t, num, t+1, k-1, tmp);
    	tmp.remove(tmp.size()-1);
    	Backtrack(res, n, num, t+1, k, tmp);
    }
}
