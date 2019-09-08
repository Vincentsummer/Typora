package Backtrack;

/**
 * 996. 正方形数组的数目
 * **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumSquarefulPerms_996 {
	// 思路一：回溯+去重+剪枝
	private int res = 0;
    public int numSquarefulPerms(int[] A) {
        int n = A.length;
        if (n == 1)
        	return isCompleteSquare(n) ? 1 : 0;
        Set<Integer> record = new HashSet<>();
        Backtrack(A, 0, n, res, record);
        return res;
    }
    
    public void Backtrack(int A[], int t, int n, int res, Set<Integer> record) {
    	if (t >= n) {
    		int num = 0;
            for (int i = 0; i < n; ++i)
            	num += (A[i] << i);
            if (!record.contains(num)) {
        		record.add(num);
        		res++;
            }
    		return;
    	}
    	for (int i = t; i < n; ++i) {
    		if (i == t || A[i] != A[t]) {
                swap(A, i, t);
                if (t != 0 && !isCompleteSquare(A[t-1] + A[t])){
                    swap(A, i, t);
    		        continue;
                }
    			Backtrack(A, t+1, n, res, record);
                swap(A, i, t);
    		}
    	}
    }
 
    public boolean isCompleteSquare(int n) {
    	int res = (int)Math.sqrt(n);
    	return res * res == n;
    }
    
    public void swap(int A[], int idx1, int idx2) {
    	int tmp = A[idx1];
    	A[idx1] = A[idx2];
    	A[idx2] = tmp;
    }
    
    // 思路二：回溯+图
    // 构造一张图，包含所有的边 i 到 j ，如果满足 A[i] + A[j] 是一个完全平方数。
    // 目标就是求这张图的所有哈密顿路径，即经过图中所有点仅一次的路径。
    // 我们使用 count 记录对于每一种值还有多少个节点等待被访问，与一个变量 todo 记录还剩多少个节点等待被访问。
    // 对于每一个节点，我们可以访问它的所有邻居节点（从数值的角度来看，从而大大减少复杂度）。
    Map<Integer, Integer> count;
    Map<Integer, List<Integer>> graph;
    public int numSquarefulPerms1(int[] A) {
        int N = A.length;
        count = new HashMap();
        graph = new HashMap();

        // count.get(v) : 数组 A 中值为 v 的节点数量
        for (int x: A)
            count.put(x, count.getOrDefault(x, 0) + 1);

        // graph.get(v) : 在 A 中的值 w 满足 v + w 是完全平方数
        //                (ie., "vw" is an edge)
        for (int x: count.keySet())
            graph.put(x, new ArrayList());

        for (int x: count.keySet())
            for (int y: count.keySet()) {
                int r = (int) (Math.sqrt(x + y) + 0.5);
                if (r * r == x + y)
                    graph.get(x).add(y);
            }

        // 增加从 x 开始的可行路径数量
        int ans = 0;
        for (int x: count.keySet())
            ans += dfs(x, N - 1);
        return ans;
    }

    public int dfs(int x, int todo) {
        count.put(x, count.get(x) - 1);
        int ans = 1;  
        if (todo != 0) {
            ans = 0;
            for (int y: graph.get(x)) if (count.get(y) != 0) {
                ans += dfs(y, todo - 1);
            }
        }
        count.put(x, count.get(x) + 1);
        return ans;
    }
}
