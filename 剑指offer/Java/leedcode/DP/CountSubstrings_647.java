package DP;
/**
 * 647. 回文子串
 * **/
public class CountSubstrings_647 {
	private int res;
    public int countSubstrings(String s) {
        int len = s.length();
        if (len == 0) return 0;
        for (int i = 0; i < len; ++i) {
        	for (int j = i; j < len; ++j) {
        		if (isPalindrome(s.substring(i, j+1)))
        			res++;
        	}
        }
        return res;
    }
    public boolean isPalindrome(String s) {
    	int i = 0, j = s.length() - 1;
    	while (i <= j) {
    		if (s.charAt(i) != s.charAt(j))
    			return false;
    		i++;
    		j--;
    	}
    	return true;
    }
    
    // 思路二：中心扩展法
    public int countSubstrings2nd(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            //以当前点i位置，向两边扩展,以i i+1位置向两边扩展
            result += countSegment(s, i, i);
            result += countSegment(s, i, i + 1);
        }
        return result;
    }

    public int countSegment(String s, int start, int end) {
        int count = 0;
        //start往左边跑，end往右边跑，注意边界
        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++)) {
            count++;
        }
        return count;
    }
}
