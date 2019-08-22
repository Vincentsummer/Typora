package Greedy;

/**
 * 948. 令牌放置
 * 你的初始能量为 P，初始分数为 0，只有一包令牌。令牌的值为 token[i]，每个令牌最多只能使用一次，可能的两种使用方法如下：
 * 如果你至少有 token[i] 点能量，可以将令牌置为正面朝上，失去 token[i] 点能量，并得到 1 分。
 * 如果我们至少有 1 分，可以将令牌置为反面朝上，获得 token[i] 点能量，并失去 1 分。
 * 在使用任意数量的令牌后，返回我们可以得到的最大分数。
 * **/

import java.util.Arrays;

public class BagOfTokensScore {
	// 贪心：将数组排序后使用双指针，能量足够时翻小令牌，不够时翻大令牌。
    public int bagOfTokensScore(int[] tokens, int P) {
        int n = tokens.length;
        if (n == 0)
        	return 0;
        if (n == 1)
        	return tokens[0] > P ? 0 : 1;
        	
        Arrays.sort(tokens);
        int left = 0, right = n, score = 0;
        while (left < right) {
        	if (tokens[left] <= P) {
        		P -= tokens[left];
        		score++;
        		left++;
        	}
        	else {
        		if (--right == left || score == 0)
        			break;
        		P += tokens[right];
        		score--;

        	}
        }
        return score;
    }
}
