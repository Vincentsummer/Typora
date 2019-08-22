package Greedy;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * **/

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int k = 0;
        for (int i = 0; i < t.length(); ++i) {
        	if (k == s.length())
        		return true;
        	if (s.charAt(k) == t.charAt(i))
        		k++;
        }
        return false;
    }
}
