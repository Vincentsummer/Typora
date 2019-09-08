package DP;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：'A' -> 1，'B' -> 2，......，'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * **/

public class NumDecodings_91 {
	/**
	 * 递归方程：dp[i]表示字符串前 i 个字符的解码方法个数，注意 “0”开头的字符不能单独出现。
	 * 若s[0] = "0" : return 0
	 * 若s.length = 1 and s[0] != 0 : return 1
	 * 1. if s[i] == s[i-1] == "0" : return 0
	 * 2. if s.sub[i-1, i] > "26" or s[i] == "0" : dp[i] = dp[i-1]
	 * 3. if s.sub[i-1, i] <= "26" and s[i-1] == "0" : dp[i] = d[i-2]
	 * 4. if s.sub[i-1, i] <= "26" and s[i-1] != "0" : dp[i] = dp[i-1] + dp[i-2]
	 * **/
    public int numDecodings(String s) {
    	if (s.charAt(0) == '0')
    		return 0;
        if (s.length() == 1)
        	return 1;

        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
        	String tmpString = s.substring(i-2, i);
        	int tmp = Integer.parseInt(tmpString);
        	if (tmpString.equals("00") || (tmp > 26 && tmpString.charAt(1) == '0'))
        		return 0;
        	else if (tmp > 26 || tmpString.charAt(1) == '0')
        		dp[i] = dp[i-1];
        	else if (tmp <= 26) {
        		if ( tmpString.charAt(0) == '0')
        			dp[i] = dp[i-2];
        		else
        			dp[i] = dp[i-1] + dp[i-2];
        	}
        }
        return dp[n];
    }
}
