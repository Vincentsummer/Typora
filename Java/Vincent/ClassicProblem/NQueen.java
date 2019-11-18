package ClassicProblem;

/**
 * 回溯法之n皇后问题
 * **/

public class NQueen {
	private int n;		// 皇后个数
	private int x[];		// 当前解
	private long sum;		// 当前已找到的可行方案数
	
	private boolean place(int k) {
		for (int j = 1; j < k; j++) {
			if (Math.abs(k-j) == Math.abs(x[j] - x[k]) || x[j] == x[k]) 	// 不能在同一斜线或同一列的约束
				return false;
		}
		return true;
	}
	
	// 递归回溯
	private void Backtrack(int t) {
		if (t > n)
			sum++;
		else {
			for (int i = 1; i <= n; i++) {
				x[t] = i;		// x[i] 表示将皇后 i 放在棋盘的第 i 行的第 x[i] 列。
				if (place(t))	// 若可行，则放置下一个皇后，否则继续尝试下一个位置。
					Backtrack(t+1);
			}
		}
	}
	
	// 迭代回溯
	private void IterativeBacktrack() {
		x[1] = 0;
		int k = 1;
		while (k > 0) {
			x[k] += 1;
			while (x[k] <= n && !place(k))
				x[k] += 1;
			if (x[k] <= n) {
				if (k == n)
					sum++;
				else {
					k++;
					x[k] = 0;
				}
			}
			else
				k--;
		}
	}
	public static long nQueen(int n) {
		NQueen X = new NQueen();
		X.n = n;
		X.sum = 0;
		int p[] = new int[n+1];
		X.x = p;
		X.Backtrack(1);
//		X.IterativeBacktrack();
		return X.sum;
	}
}
