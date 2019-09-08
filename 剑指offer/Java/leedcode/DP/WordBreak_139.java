package DP;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 拆分时可以重复使用字典中的单词。可以假设字典中没有重复的单词。
 * **/

import java.util.List;

public class WordBreak_139 {
	
	// 递推式：dp[i] 表示字符串前 i 个字符能否用wordDict拆分的结果。
	public boolean wordBreak1(String s, List<String> wordDict) {
    	int strLen = s.length(), dictLen = wordDict.size();
        if (strLen == 0 || dictLen == 0)
        	return false;
        
        boolean dp[] = new boolean[strLen];
        for (int i = 0; i < strLen; ++i) {
        	String sub = s.substring(0, i+1);
        	if (wordDict.contains(sub))
        		dp[i] = true;
        	else {
				for (int j = i; j >= 0; --j) {
					if (!dp[j])
						continue;
					String tmp = sub.substring(j+1, i+1);
					if (wordDict.contains(tmp)) {
						dp[i] = true;
						break;
					}
					dp[i] = false;
				}
			}
        }
        return dp[strLen-1];
	}
}
