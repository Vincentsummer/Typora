package DP;
import java.util.Arrays;

/**
 * 1156. 单字符重复子串的最大长度
 * **/
public class MaxRepOpt1_1156 {
	  /**
     * 通过滑动窗口，记录索引i的左侧连续重复的字符的个数和右边连续字符的个数,并记录连续的字符的最长长度；
     * 索引i的左侧连续的重复个数为left[i-1],右侧连续重复的个数为right[i+1],主要分为以下三种情况:
     * text[i-1]的字符总数大于left[i-1], ans = max(ans,left[i-1]+1),这时从其他位置交换一个字符即可;
     * text[i+1]的字符总数大于right[i+1], ans = max(ans,right[i+1]+1)，这时从其他位置交换一个字符即可;
     * text[i-1] == text[i+1] 且 text[i-1] != text[i]，这时就需要进行链接前后的字符串，从其他地方找一相
     * 同的字符与text[i]进行交换，这时需要计算是否其他地方还有剩余的与text[i]相同的字符，通过比较
     * count[text[i-1]-'a']与 left[i-1] + right[i+1]是否相等, 即判断
     * count[text[i-1]-'a'] == (left[i-1] + right[i+1]); 时间复杂度 O(n)
     * **/
    public int maxRepOpt1(String text) {
    	int n = text.length();
        int count[] = new int[26];
        int left[] = new int[n];
        int right[] = new int[n];
        int res = 1;
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 0; i < n; ++i)
        	count[text.charAt(i) - 'a']++;
        
        int c = 1;
        for (int i = 1; i < n; i++) {
        	if (text.charAt(i-1) == text.charAt(i))
        		c++;
        	else c = 1;
        	left[i] = c;
        	res = Math.max(res, left[i]);
        }
        c = 1;
        for (int i = n - 2; i >= 0; i--) {
        	if (text.charAt(i) == text.charAt(i+1))
        		c++;
        	else c = 1;
        	right[i] = c;
        	res = Math.max(res, right[i]);
        }
        for (int i = 1; i < n - 1; ++i) {
        	if (count[text.charAt(i-1) - 'a'] > left[i-1])
        		res = Math.max(res, left[i-1] + 1);
        	if (count[text.charAt(i+1) - 'a'] > right[i+1])
        		res = Math.max(res, right[i+1] + 1);
        	if (text.charAt(i-1) == text.charAt(i+1) && text.charAt(i-1) != text.charAt(i)) {
        		if (count[text.charAt(i-1) - 'a'] > (left[i-1] + right[i+1]))
        			res = Math.max(res, left[i-1] + right[i+1] + 1);
        		else res = Math.max(res, left[i-1] + right[i+1]);
        	}
        }
        return res;
    }
}
