package DP;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * **/

public class NumMatrix {
	
	private int dp[][];
	
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return;
        dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < dp.length; ++i) {
        	for (int j = 0; j < dp[0].length; ++j) {
        		if (i == 0 && j == 0)
        			dp[i][j] = matrix[i][j];
        		else if (i == 0)
        			dp[i][j] = dp[i][j-1] + matrix[i][j];
        		else if (j == 0)
        			dp[i][j] = dp[i-1][j] + matrix[i][j];
        		else
        			dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
        	}
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (dp == null)
            return 0;
    	if (row1 == 0 && col1 == 0)
    		return dp[row2][col2];
    	else if (row1 == 0)
    		return dp[row2][col2] - dp[row2][col1-1];
    	else if (col1 == 0)
    		return dp[row2][col2] - dp[row1-1][col2];
    	else
    		return dp[row2][col2] - dp[row2][col1-1] - dp[row1-1][col2] + dp[row1-1][col1-1];
    }
}
