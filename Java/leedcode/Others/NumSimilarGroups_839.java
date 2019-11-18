package Others;

public class NumSimilarGroups_839 {
    public int numSimilarGroups(String[] A) {
    	int res = 0;
        for (int i = 1; i < A.length; i++) {
        	for (int j = i-1; j < i; j++) {
        		if (isSimilar(A[i], A[j]))
        			res++;
        		
        	}
        }
        return res;
    }
    public boolean isSimilar(String str1, String str2) {
    	int len = str1.length(), count = 0;
    	for (int i = 0; i < len; i++) {
    		if (str1.charAt(i) != str2.charAt(i))
    			count++;
    	}
    	return count == 2 ? true : false;
    }
}
