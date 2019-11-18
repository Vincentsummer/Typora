package Others;

/**
 * 945. 使数组唯一的最小增量
 * **/

public class MinIncrementForUnique_945 {
	/**
	 * 首先统计出范围中每个数出现的次数，然后对于范围中的每个数 x：
	 * 如果 x 出现了两次以上，就将额外出现的数记录下来；
	 * 如果 x 没有出现过，那么在记录下来的数中选取一个 v，将它增加到 x，需要进行的操作次数为 x - v。
	 * **/
    public int minIncrementForUnique(int[] A) {
        int count[] = new int[80000];
        int res = 0;
        for (int i = 0; i < A.length; ++i)
            count[A[i]]++;
        int move = 0;
        for (int i = 0; i < 80000; ++i){
            if (count[i] > 1){
                move += count[i] - 1;
                res -= i * (count[i] - 1);
            }
            else if (move > 0 && count[i] == 0){
                move--;
                res += i;
            }
        }
        return res;
    }
}
