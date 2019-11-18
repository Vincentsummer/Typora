package Others;

/**
 * 922. 按奇偶排序数组 II
 * **/

public class SortArrayByParityII_922 {
	// 双指针，O(N) & O(1)
    public int[] sortArrayByParityII(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i += 2)
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1)
                    j += 2;
                // Swap A[i] and A[j]
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        return A;
    }
}