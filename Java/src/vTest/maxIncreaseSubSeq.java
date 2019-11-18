/**
 * 最长递增子序列的长度
 * **/

package vTest;

import java.util.Scanner;

public class maxIncreaseSubSeq {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int[]  seq = new int[N];
		scan.nextLine();
		for (int i = 0; i < N; ++i)
			seq[i] = scan.nextInt();
		
		int result = LIS_2(seq, N);
		System.out.println(result);

	}
	/**
	 * 思路一：这个问题满足无后效性（以前各阶段的状态无法直接影响它未来的决策），可使用动态规划求解。
	 * 定义dp[i]为以i结尾的数组的最大递增子序列长度，
	 * 则有递推式：dp[i+1] = max(dp[i - k] + 1), seq[k] < seq[i+1], for k <= i。
	 * 这种情况下最终结果不一定为 dp[N-1]，因为数组的最长子序列不一定以seq[N-1]结尾，
	 * 因此result = max(dp[i]), for 0 <= i < N。
	 * 时间复杂度O(N^2), 空间复杂度O(N)。
	 * **/
	public static int LIS_1(int[] seq, int N) {
		int[] dp = new int[N];
		int result = 0;
		
		for (int i = 0; i < N; ++i) {
			dp[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (seq[i] > seq[j] && dp[j] + 1 > dp[i])
					dp[i] = dp[j] + 1;
			}
		}
		
		for (int i = 0; i < N; ++i)
			result = dp[i] > result ? dp[i] : result;

			return result;
	}
	
	/**
	 * 思路二：思路一根据无后效性，当考察第 i+1 个元素时，不考虑前面 i 个元素的分布情况。思路二从另一个角度分析，
	 * 考虑前面 i 个元素的情况。
	 * 方法如下：找到前 i 个元素中的一个递增子序列，使得这个递增子序列的最大的元素比seq[i+1]小，且长度尽可能长。
	 * 维护一个数组maxV[i]：表示长度为 i 的递增子序列的最大元素的最小值为maxV[i]。
	 * 可一证明maxV[i] 为递增数组，可使用二分法对其查找。
	 * 时间复杂度 O(nlogn)，空间复杂度 O(N)
	 * **/
	public static int LIS_2(int[] seq, int N) {
		// 记录数组递增序列的信息
		int[]	maxV = new int[N+1];
		int[] dp = new int[N];
		
		// 数组最长递增子序列的长度
		int nMaxLIS = 1;
		
		maxV[1] = seq[0];							// 数组中的第一值， 边界值
		maxV[0] = min(seq, N) - 1;			// 数组中的最小值， 边界值
		
		// 初始化dp
		for (int i = 0; i < N; ++i)
			dp[i] = 1;
		
		for (int i = 1; i < N; ++i) {
			int start = 1, end = dp[i-1];
			int j = (start + end) / 2;
			while (start <= end) {
				if (seq[i] <= maxV[j+1] && seq[i] > maxV[j]) {
					dp[i] = j+1;
					break;
				}
				if (seq[i] > maxV[j])
					start = j + 1;
				else
					end = j - 1;
				
				j = (start + end) / 2;
			}
			
//			int j = dp[i-1];
//			for (; j >= 1; --j) {
//				if (seq[i] > maxV[j]) {
//					dp[i] = j + 1;
//					break;
//				}
//			}
			
			// 当前最长序列的大于nMaxLIS，则更新
			if (dp[i] > nMaxLIS) {
				nMaxLIS = dp[i];
				maxV[dp[i]] = seq[i];
			}
			else if (maxV[j] < seq[i] && seq[i] < maxV[j+1])
				maxV[j+1] = seq[i];
		}
		return nMaxLIS;
	}
	
	public static int min(int[] seq, int N) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; ++i)
			min = min > seq[i] ? seq[i] : min;
		
		return min;
	}
}
