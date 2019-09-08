package Backtrack;

/**
 * 282. 给表达式添加运算符
 * **/

import java.util.ArrayList;
import java.util.List;

public class AddOperators_282 {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        Backtrack(res, num, target, 0, 0, 0, "");
        return res;
    }
    
    public void Backtrack(List<String> res, String num, int target, long curRes, long preNum, int t, String curOp) {
    	if (t == num.length() && curRes == (long)target) {
    			res.add(new String(curOp));
    		return;
    	}
    	
    	for (int i = t; i < num.length(); ++i) {
    		String cur = num.substring(t, i + 1);
            if (cur.length() > 1 && cur.charAt(0) == '0')
                break;

    		long curNum = Long.parseLong(String.valueOf(cur));
    		if (curOp.length() > 0) {
        		Backtrack(res, num, target, curRes + curNum, curNum, i+1, curOp + "+" + cur);
        		Backtrack(res, num, target, curRes - curNum, -curNum, i+1, curOp + '-' + cur);
        		Backtrack(res, num, target, curRes - preNum + preNum * curNum, preNum * curNum, i+1, curOp + '*' + cur);
    		}
    		else
    			Backtrack(res, num, target, curNum, curNum, i+1, curOp + cur);
    	}
    }
}
