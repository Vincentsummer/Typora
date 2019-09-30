package Others;
/**
 * 1004. 最大连续1的个数 III
 * **/
public class LongestOnes_1004 {
	// 双指针
    public int longestOnes(int[] A, int K) {
        int p = 0, countOf0 = 0, res = 0, tmp = 0;
        for (int j = 0; j < A.length; ++j){
            if (countOf0 == K && A[j] == 0){
                while (A[p++] == 1) tmp--;
                tmp = j - p + 1;
            }
            else if (A[j] == 0) {countOf0++; tmp++;}
            else tmp++;
            res = Math.max(res, tmp);
        }
        return res;
    }
}
