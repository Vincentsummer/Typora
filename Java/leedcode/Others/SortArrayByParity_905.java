package Others;
/**
 * 	905 按奇偶排序数组
 * **/
public class SortArrayByParity_905 {
    public int[] sortArrayByParity(int[] A) {
        int len = A.length;
        if (len <= 1) return A;
        for (int i = 0, j = -1; i < len; ++i) {
        	if (A[i] % 2 == 0)
        		swap(A, ++j, i);
        }
        return A;
    }
    
    public void swap(int A[], int idx1, int idx2) {
    	int tmp = A[idx1];
    	A[idx1] = A[idx2];
    	A[idx2] = tmp;
    }
}
