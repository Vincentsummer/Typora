package Greedy;

/**
 * 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
 * 同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * **/

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels_763 {
	// 遍历字符串 找到和起点相同的最后一个字母 查看此区间里的字母最后的index是否超出区间 超出则更新区间 
	// 直至找到最大的index 则index - i + 1就是所求区间长度 使用cache来存储每个字母的最后出现位置
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0)
        	return null;
        int n = S.length();
        int lastPosition[] = new int[26];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i)
        	lastPosition[S.charAt(i) - 'a'] = i;
        int i = 0;
        while (i < n) {
        	int index = lastPosition[S.charAt(i) - 'a'];
        	for (int j = 0; j < n && j < index; ++j) {
        		if (lastPosition[S.charAt(j) - 'a'] > index)
        			index = lastPosition[S.charAt(j) - 'a'];
        	}
        	result.add(index - i + 1);
        	i = index + 1;
        }
        return result;
    }
}
