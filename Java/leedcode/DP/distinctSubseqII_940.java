package DP;

/**
 * 940. 不同的子序列 II
 * **/

import java.util.Arrays;

public class distinctSubseqII_940 {
	/**
	 * 用 dp[k] 表示 S[0 .. k] 可以组成的不同子序列的数目。
	 * 如果 S 中的所有字符都不相同，例如 S = "abcx"，那么状态转移方程就是简单的 dp[k] = dp[k-1] * 2
	 * 当 S 中有相同字母的时候，就要考虑重复计数
	 * dp[k] = 2 * dp[k - 1] - dp[last[S[k]] - 1]
	 * 即在计算 dp[k] 时，首先会将 dp[k - 1] 对应的子序列的末尾添加 S[k] 得到额外的 dp[k - 1] 个子序列，
	 * 并减去重复出现的子序列数目，这个数目即为上一次添加 S[k] 之前的子序列数目 dp[last[S[k]] - 1]。
	 * **/
    public int distinctSubseqII(String S) {
        int len = S.length(), MOD = 1_000_000_007;
        int dp[] = new int[len+1];
        int last[] = new int[26];
        dp[0] = 1;
        Arrays.fill(last, -1);
        for (int i = 0; i < len; ++i) {
        	int x = S.charAt(i) - 'a';
            dp[i+1] = dp[i] * 2 % MOD;
            if (last[x] >= 0)
                dp[i+1] -= dp[last[x]];
            dp[i+1] %= MOD;
        	last[x] = i;
        }
        return --dp[len] < 0 ? dp[len] + MOD : dp[len];
    }
}
