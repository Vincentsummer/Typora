package DP;

/**
 * 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * **/

public class CountBits {
    public int[] countBits(int num) {
    	int result[] = new int[num+1];
        for (int i = 1; i <= num; ++i) {
        	result[i] = result[i & (i-1)] + 1;
        }
        return result;
    }
}
