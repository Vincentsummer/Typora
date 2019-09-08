package Greedy;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * **/

import java.util.Arrays;
import java.util.Comparator;

public class EraseOverlapIntervals_435 {
	// 思路一：动态规划
	// 题意可转化成找最大数量的不重叠区间，类似于最大递增子序列，先将数组排序(先根据起始时间升序，再根据结束时间升序)
	// dp[i] 表示前 i 个区间的最大不重叠区间，则 dp[i] = max(dp[i], dp[j] + 1), for j in (0, i);
	// 则maxLen = max(dp[i]), 结果为 length - maxlen;
	// 复杂度 O(n^2) & O(n)
    public int eraseOverlapIntervals(int[][] intervals) {
        if (0 == intervals.length || 0 == intervals[0].length)
        	return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] == arg1[0] ? arg0[1] - arg1[1] : arg0[0] - arg1[0];
			}
		});
        int dp[] = new int[intervals.length];
        int maxLen = 1;
        for (int i = 1; i < intervals.length; ++i) {
        	dp[i] = 1;
        	for (int j = 0; j < i; ++j) {
        		if (intervals[i][0] >= intervals[j][1])
        			dp[i] = Math.max(dp[i], dp[j] + 1);
        	}
        	maxLen = Math.max(dp[i], maxLen);
        }
        return intervals.length - maxLen;
    }
    
    // 贪心算法：将数组按照先根据结束时间排序再根据开始时间排序，则数组已满足结束时间递增。
    // 然后遍历数组，比较当前位置的开始时间与前一个满足非重叠区间的位置的结束时间，记录满足条件的长度即可。
    // 复杂度 O(nlogn) & O(1)
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (0 == intervals.length || 0 == intervals[0].length)
        	return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] == arg1[1] ? arg0[1] - arg1[1] : arg0[0] - arg1[0];
			}
		});
        int maxLen = 1, pre = 0;
        for (int i = 1; i < intervals.length; ++i) {
        	if (intervals[i][0] >= intervals[pre][1]) {
        		maxLen++;
        		pre = i;
        	}
        }
        return intervals.length - maxLen;
    }
}
