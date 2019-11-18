package DP;

/**
 * 221.  最大正方形
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * **/

public class MaximalSquare_221 {
	
	// dp[i][j]表示以第i行第j列为右下角所能构成的最大正方形边长, 则递推式为: 
    // dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0)
        	return 0;
        if (m == 1 && n == 1)
        	return (int)(matrix[0][0] - '0');
        
        int dp[][] = new int[m+1][n+1];
        int result = 0;
        
        for (int i = 1; i <= m; ++i) {
        	for (int j = 1; j <= n; ++j) {
        		if (matrix[i-1][j-1] == '1') {
        			dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
        			result = Math.max(result, dp[i][j]);
        		}
        	}
        }
        return result * result;
    }
}
