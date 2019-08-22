package DP;

/**
 * 357. 计算各个位数不同的数字个数
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * **/

public class CountNumbersWithUniqueDigits {
	
	// dp[i] 表示 i 位数的结果，则有
	// dp[i] = dp[i-1] + 9 * C(i-1, 11 - i)， C为组合数, i >= 2
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;
        int result = 10, tmp = 1;
        for (int i = 2; i <= n; ++i) {
        	tmp *= 11 - i;
        	result += 9 * tmp;
        }
        return result;
    }
}
