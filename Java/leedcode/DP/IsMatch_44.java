package DP;
/**
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。'*' 可以匹配任意字符串（包括空字符串）。
 * **/
public class IsMatch_44 {
    
    // 动态规划
	// dp[i][j] 表示  p 到 i 位置 s 到 j 位置是否匹配
	// dp[0][0] = true;
	// dp[i][0] = true if dp[i-1][0] && j == *
	//dp[0][i] = false;
	// 若(s[i] == p[j] || p[j] == "?") && dp[i-1][j-1]，则dp[i][j] = true;
	// 若p[j] == "*" && (dp[i-1][j] = true || dp[i][j-1] = true)，则dp[i][j] = true
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        dp[0][0] = true;

        for(int i = 1;i <= p.length(); i++){
            if(p.charAt(i-1) == '*'){
            	dp[i][0] = dp[i-1][0];
                for(int j = 1;j <= s.length(); j++){
                	dp[i][j] = (dp[i][j-1] || dp[i-1][j]);
                }
            }
            else {
            	dp[i][0] = false;
                for(int j = 1;j <= s.length(); j++){
                	dp[i][j] = (s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?') && dp[i-1][j-1];
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}
