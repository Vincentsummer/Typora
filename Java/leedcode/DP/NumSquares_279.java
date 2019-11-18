package DP;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 需要让组成和的完全平方数的个数最少。
 * **/

public class NumSquares_279 {
	
	// 思路一：递推式 : dp[i] 表示组成数字 i 所需要的最少的平方数。则有：
	// dp[i] = min(dp[i-1] + dp[i], dp[i-2] + dp[2], ..., dp[i / 2][dp[i - i / 2]])
    public int numSquares(int n) {
        if (n == 0)
        	return 0;
        if (n == 1)
        	return 1;
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i <= n; ++i) {
        	int tmp = (int)Math.sqrt(i);
        	if (tmp * tmp == i) {
        		dp[i] = 1;
        		continue;
        	}
        	int result = Integer.MAX_VALUE;
        	for (int j = i-1; j >= i / 2; --j) {
        		result = Math.min(result, dp[j] + dp[i-j]);
        	}
        	dp[i] = result;
        	
        }
        return dp[n];
    }
    
    // 思路二：
    // 四平方定理： 任何一个正整数都可以表示成不超过四个整数的平方之和。 
    // 推论：满足四数平方和定理的数n（四个整数的情况），必定满足 n=4^a * (8b+7)
    public int numSquares2(int n) {
    	// 先根据公式来缩小n
    	while(n % 4 == 0) {
            n /= 4;
        }
    	
        //如果满足公式 则返回4
        if(n % 8 == 7) {
            return 4;
        }
        
        //在判断缩小后的数是否可以由一个数的平方或者两个数平方的和组成
        int a = 0;
        while ((a * a) <= n) {
            int b = (int)Math.sqrt((n - a * a));
            if (a * a + b * b == n) {
                //如果可以 在这里返回
                if(a != 0 && b != 0) {
                    return 2;
                } else{
                    return 1;
                }
            }
            a++;
        }
        //如果不行 返回3
        return 3;
    }
}
