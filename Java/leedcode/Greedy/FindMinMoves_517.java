package Greedy;
/**
 * 517. 超级洗衣机
 * **/
public class FindMinMoves_517 {
	/**
	 * 将每个洗衣机中的衣服数量相等可以转化为：差值数组中每一项都变为0，差值数组为 diff[i] = machines[i] - mean
	 * 在把差值数组每一项变为0的操作中，只需要求出其中所需移动衣服最多的洗衣机，就是最少的移动次数。
	 * 当diff[i] < 0 时，可以从左右两边的洗衣机获取衣服，取左右中的最大值； 
	 * 当diff[i] > 0 时，需要把洗衣机的衣服向左右转移，此时移动次数等于diff[i]
	 * **/
    public int findMinMoves(int[] machines) {
        int len = machines.length;
        if (len <= 1) return 0;
        int mean = 0;
        for (int i = 0; i < len; ++i)	mean+= machines[i];
        if (mean % len != 0) return -1;
        mean /= len;
        int balance = 0, res = 0;
        for(int i = 0 ; i < machines.length; i ++){
            balance += machines[i] - mean;
            res = Math.max(res, Math.max(machines[i] - mean, Math.abs(balance)));
        }
        return res;
    }
}
