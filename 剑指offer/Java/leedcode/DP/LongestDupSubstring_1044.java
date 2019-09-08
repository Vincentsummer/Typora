package DP;
/**
 * 1044. 最长重复子串(未完成)
 * **/
public class LongestDupSubstring_1044 {
    public String longestDupSubstring(String S) {
    	int N = S.length();
    	int maxLen = 0, maxIndex = -1;
    	int prefix[] = new int[N+1];
    	prefix[0] = -1;
        int i = 0;
        int k = -1;
        while (i < N) {
            if (k == -1 || S.charAt(k) == S.charAt(i)) {
                k++;
                i++;
                prefix[i] = k;
            }
            else
                k = prefix[k];
        }
    	
    	for (int j  = 0; j <= N; ++j) {
        	if (maxLen < prefix[j]) {
    			maxLen = prefix[j];
    			maxIndex = j;
    		}
        	System.out.print(prefix[j] + " ");
    	}
//    	System.out.println(S.substring(maxIndex - maxLen, maxIndex));
    	return maxIndex == -1 ? "" : S.substring(maxIndex - maxLen, maxIndex);
    }
}
