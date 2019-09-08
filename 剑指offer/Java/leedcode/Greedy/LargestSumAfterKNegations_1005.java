package Greedy;

/**
 * 1005. K次取反后最大化的数组和
 * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个个索引 i 并将 A[i] 替换为 -A[i]，
 * 然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
 * 以这种方式修改数组后，返回数组可能的最大和。
 * **/

import java.util.Arrays;

public class LargestSumAfterKNegations_1005 {
	// 贪心：先排序，尽可能多的翻转负数，当所有负数翻转完毕后没有到达指定次数则只翻转绝对值最小的数。
    public int largestSumAfterKNegations(int[] A, int K) {
        int n = A.length;
        int result = 0;
        Arrays.sort(A);
        for (int i = 0; i < n; ++i) {
        	if (K > 0) {
        		if (A[i] < 0){
                    result += -A[i];
                    K--;
                }
        		else if (A[i] == 0)
        			K = 0;
        		else if (i > 0 && A[i] > 0 && A[i-1] < 0) {
                    if (K % 2 != 0){
                        if (A[i] > -A[i-1])
                            result += A[i] + 2 * A[i-1];
                        else
                            result -= A[i];
                    }
                    else
                        result += A[i];
                    K = 0;
        		}
                else if (A[i] > 0){
                    result += K % 2 != 0 ? -A[i] : A[i];
                    K = 0;
                }                    
        	}
        	else
        		result += A[i];
        }
        return result;
    }
}
