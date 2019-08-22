package Greedy;

public class MinDeletionSize {
    public int minDeletionSize(String[] A) {
        int n = A.length;
        if (n == 0)
            return 0;
        int result = 0;
        for (int i = 0; i < A[0].length(); ++i) {
        	for (int j = 1; j < n; ++j) {
        		if (A[j].charAt(i) < A[j-1].charAt(i)) {
        			result++;
        			break;
        		}
        	}
        }
        return result;
    }
}
