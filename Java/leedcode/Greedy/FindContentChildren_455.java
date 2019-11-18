package Greedy;

/**
 * 455. 分发饼干
 * **/

import java.util.Arrays;

public class FindContentChildren_455 {
	
	// 贪心：用尽量小的饼干满足尽量多的孩子，需先对数组进行排序。
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0, result = 0;
        while(i < g.length && j < s.length) {
        	if (s[i] >= g[i]) {
        		result++;
        		++i;
        	}
        	++j;
        }
        return result;
    }
}
