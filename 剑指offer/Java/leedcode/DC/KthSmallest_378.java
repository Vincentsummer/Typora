package DC;

/**
 * 378. 有序矩阵中第K小的元素
 * **/

public class KthSmallest_378 {
	/**
	 * 1.找出二维矩阵中最小的数left，最大的数right，那么第k小的数必定在left~right之间。
	 * 2.mid=(left+right) / 2；在二维矩阵中寻找小于等于mid的元素个数count。
	 * 3.若这个count小于k，表明第k小的数在右半部分且不包含mid，即left=mid+1, right=right，又保证了
	 * 第k小的数在left~right之间。
	 * 4.若这个count大于k，表明第k小的数在左半部分且可能包含mid，即left=left, right=mid，又保证了第
	 * k小的数在left~right之间。
	 * 5.因为每次循环中都保证了第k小的数在left~right之间，当left==right时，第k小的数即被找出，等于right。
	 * 注意：这里的left mid right是数值，不是索引位置。
	 * **/
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length; 
        int low = matrix[0][0], high = matrix[m-1][m-1];
        while (low < high){
            int cnt = 0, mid = (low + high) / 2;
            int i = m - 1, j = 0;
            while (i >= 0 && j < m){
                if (matrix[i][j] <= mid){
                    cnt += (i + 1);
                    j++;
                }
                else i--;
            }
            if (cnt < k) low = mid + 1;
            else high = mid;
        }
        return high;
    }
}
