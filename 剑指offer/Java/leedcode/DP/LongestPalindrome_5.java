package DP;
/**
 * 5. 最长回文子串
 * **/
public class LongestPalindrome_5 {
	// 中心扩展法
	private String res = "";
    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); ++i) {
        	getPalindrome(s, i, i);
        	getPalindrome(s, i, i+1);
        }
        return res;
    }
    
    public void getPalindrome(String s, int start, int end) {
    	while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
    		start--;
    		end++;
    	}
    	if (res.length() < end - start - 1) res = s.substring(start+1, end);
    }
}
