package Backtrack;
/**
 * 131. 分割回文串
 * **/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Partition_131 {
    public List<List<String>> partition(String s) {
        List<List<String>>	res = new ArrayList<>();
        backtrack(s, res, new ArrayList<String>(), 0);
        return res;
    }
    
    public void backtrack(String s, List<List<String>> res, List<String> cur, int t) { 
    	if (t >= s.length()) {
    		res.add(new ArrayList<String>(cur));
    		return;
    	}
    	
    	for (int i = t; i < s.length(); ++i) {
    		String tmp = s.substring(t, i+1);
    		if (isPalindrome(tmp)) {
    			cur.add(tmp);
    			backtrack(s, res, cur, i+1);
    			cur.remove(cur.size()-1);
    		}
    	}
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
    
    // 思路二：DP
    public List<List<String>> partition1(String s) {
        List<List<String>> res = new ArrayList<>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])) dp[j][i] = true;
            }
        }
        System.out.println(Arrays.deepToString(dp));
        dfs(res, dp, 0, n, s, new ArrayList<String>());
        return res;
    }
    
    private void dfs(List<List<String>> res, boolean[][] dp, int i, int n, String s, ArrayList<String> tmp) {
        if (i == n) res.add(new ArrayList<>(tmp));
        for (int j = i; j < n; j++) {
            if (dp[i][j]) {
                tmp.add(s.substring(i, j + 1));
                dfs(res, dp, j + 1, n, s, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
