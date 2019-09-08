package DP;
/**
 * 132. 分割回文串 II
 * **/
import java.util.Arrays;

public class MinCut_132 {
	/**
	 * 状态：dp[i]表示字符串s[0...i]最少的分割次数。
	 * 状态转移：dp[i] = min(dp[i], dp[j-1] + 1, 0=<j<=i&&s[j...i]是回文串)。
	 * 边界：dp[0] = 0。
	 * **/
    public int minCut(String s) {
        int n = s.length();
        if (n < 1)
        	return 0;
        int dp[] = new int[n];
        Arrays.fill(dp, n-1);
        dp[0] = 0;
        for (int i = 1; i < n; ++i) {
        	for (int j = 0; j <= i; ++j) {
        		if (isPali(s, i, j))
        			dp[i] = Math.min(dp[i], j == 0 ? 0 : dp[j-1] + 1);
        	}
        }
        return dp[n-1];
    }
    
    public boolean isPali(String s, int i, int j) {
    	while (j <= i) {
    		if (s.charAt(i) != s.charAt(j))
    			return false;
    		j++;
    		i--;
    	}
    	return true;
    }
    
    // 思路二：中心扩展法
    public int minCut1(String s) {
        int n = s.length();
        if (n < 1)
        	return 0;
        int dp[] = new int[n];
        Arrays.fill(dp, n-1);
        dp[0] = 0;
        for (int i = 0; i < n; ++i) {
        	minCutHelper(s, i, i, dp);
        	minCutHelper(s, i, i+1, dp);
        }
        return dp[n-1];
    }
    
    public void minCutHelper(String s, int i, int j, int dp[]) {
    	while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
    		dp[j] = Math.min(dp[j], i == 0 ? 0 : dp[i-1] + 1);
    		i--;
    		j++;
    	}
    }
}
