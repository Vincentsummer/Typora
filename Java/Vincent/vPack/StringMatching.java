package vPack;

public class StringMatching {

	// 求子串位置的定位函数，暴力匹配
	public int Index(String S, String T, int pos) {
		// 返回子串T在主串S中第pos个字符之后的位置。若不存在，返回0。(位置从0起)
		int i = pos, j = 0;
		while (i < S.length() && j < T.length()) {
			if (S.charAt(i) == T.charAt(j)) {
				++i;
				++j;
			}
			else {
				i = i - j + 1;
				j = 0;
			}
		}
		if (j >= T.length())
			return i - T.length();
		return -1;
	}
	
	// KMP 模式匹配算法
	public int KMPMatch(String T, String P) {
		int n = T.length(), m = P.length();
		int[] next = new int[m];
		int i = 0, j = 0;
		getNext(P, next);
		while (i < n && j < m) {
			if (j != -1 && T.charAt(i) != P.charAt(j))
				j = next[j];
			else {
				i++;
				j++;
			}
		}
		return j == m ? i - j : -1;
	}
	
	// 求模式串P的prefix函数：prefix[q] 表示 P 的字符q之前的子串的真后缀的最长前缀长度。
	// 注意prefix函数下标从1开始，令prefix[0] = -1。而字符串P的字符下标从0开始。
	private void getNext(String P, int next[]) {
		int j = 0, k = -1;
		next[0] = -1;
		while (j < P.length() - 1) {
			if (k != -1 && P.charAt(k) != P.charAt(j))
				k = next[k];
			else
				next[++j] = ++k;
		}
	}
}
