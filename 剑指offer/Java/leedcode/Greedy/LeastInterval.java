package Greedy;

/**
 * 621. 任务调度器
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，
 * 或者在待命状态。然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行
 * 不同的任务，或者在待命状态。你需要计算完成所有任务所需要的最短时间。
 * **/

public class LeastInterval {
	// 贪心：总调度时间与只与最大任务数有关，找出最大任务即可。
    public int leastInterval(char[] tasks, int n) {
    	int len = tasks.length;
        int count[] = new int[26];
        int maxCount = 0, result = 0;
        for(int i = 0; i < len; ++i)
        	maxCount = Math.max(++count[tasks[i] - 'A'], maxCount);
        
        result += (maxCount - 1) * (n + 1);
        for (int i = 0; i < 26; ++i) {        	
        	if (count[i] == maxCount)
        		result++;
        }
        return Math.max(result, len);
    }
}
