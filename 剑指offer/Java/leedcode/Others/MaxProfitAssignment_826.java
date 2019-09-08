package Others;

/**
 * 826. 安排工作以达到最大收益
 * **/

import java.util.Arrays;

public class MaxProfitAssignment_826 {
	// 排序，双指针
	// 使用 “双指针” 的方法去安排任务。我们记录最大可用利润 best。
	// 对于每个能力值为 skill 的工人，找到难度小于等于能力值的任务，并将如结果中。
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int N = difficulty.length;
        Job[] jobs = new Job[N];
        for (int i = 0; i < N; ++i)
            jobs[i] = new Job(difficulty[i], profit[i]);
        Arrays.sort(jobs, (a, b) -> a.d - b.d);
        Arrays.sort(worker);

        int ans = 0, i = 0, best = 0;
        for (int skill : worker) {
            while (i < N && skill >= jobs[i].d)
                best = Math.max(best, jobs[i++].p);
            ans += best;
        }
        return ans;
    }
    
class Job{
	int d, p;
	Job(int x, int y){d = x; p=y;}
}
}
