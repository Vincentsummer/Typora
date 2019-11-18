package Others;

/**
 * 696. 计数二进制子串
 * **/
public class CountBinarySubstrings_696 {
	// 使用pre保存前一个字符的个数,cur保存当前字符的个数, if pre >= cur, res++
    public int countBinarySubstrings(String s) {
    	int res = 0, pre = 0, cur = 1;
    	for (int i = 1; i < s.length(); ++i) {
    		if (s.charAt(i-1) != s.charAt(i)) {
    			pre = cur;
    			cur = 1;
    		}
    		else cur++;
            
            if (pre >= cur) res++;
    	}
    	return res;
    }
}
