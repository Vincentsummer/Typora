package DP;

import java.util.LinkedList;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。如：
 * [
 *    [2],
 *   [3,4],
 *  [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * **/

import java.util.List;
import java.util.Queue;

// 递推式：dp[i][j]表示以第 i 行 第 j 个数为终点的最小路径和，则有
// 1. if i == j == 0, dp[i][j] = triangle[i][j]
// 2. else if j == 0, dp[i][j] = dp[i-1][j] + triangle[i][j]
// 3. else if j == i, dp[i][j] = dp[i-1][j-1] + triangle[i][j]
// 4. else dp[i][j] = min(dp[i-1][i-1], dp[i-1][j]) + triangle[i][j]
// 时间和空间复杂度 O(n^2)
public class MinTotalOfTriangle_120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int result = Integer.MAX_VALUE;
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
        	List<Integer> vList = triangle.get(i);
        	for (int j = 0; j >= i; ++j) {
        		if (i == 0)
        			dp[0][0] = vList.get(j);
        		else {
        			if (j == 0)
        				dp[i][j] = dp[i-1][j] + vList.get(j);
        			else if (j == i)
        				dp[i][j] = dp[i-1][j-1] + vList.get(j);
        			else
        				dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + vList.get(j);
				}
        		if (i == n-1)
        			result = Math.min(result, dp[i][j]);
        	}
        }
        return result;
    }
    
    // 方法二：将dp[i][j] 替换成队列
    // 
    public int minimumTotal_1(List<List<Integer>> triangle){
        int result = Integer.MAX_VALUE;
        int n = triangle.size();
        Queue<Integer> sumQueue = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
        	List<Integer> vList = triangle.get(i);
        	int pre1 = 0, pre2 = 0;
        	for (int j = 0; j <= i; ++j) {
        		if (i == 0)
        			sumQueue.offer(vList.get(j));
        		else {
        			pre1 = pre2;
        			if (j != i - 1)
        				pre2 = sumQueue.poll();
        			if (j == 0)	
        				sumQueue.offer(pre2 + vList.get(j));
        			else if (j == i)
        				sumQueue.offer(pre2 + vList.get(j-1));
        			else
        				sumQueue.offer(Math.min(pre1, pre2) + vList.get(j));
				}
        	}
        }
       while(!sumQueue.isEmpty())
    	   result = Math.min(result,  sumQueue.poll());
       
       return result;
	}
    
    // 方法三：自底向上，原地修改，复杂度 O(n^2) & O(1)
    public int minimumTotal_2(List<List<Integer>> triangle) {
		for (int i = triangle.size() - 2; i >= 0; --i) {
			for (int j = 0; j < triangle.get(i).size(); ++j)
				triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1)));
		}
		return triangle.get(0).get(0);
	}
}
