package ClassicProblem;

public class Flowshop {
	int M[][];	// 各作业所需的处理时间
	int x[], bestx[], f2[], f1; 	// 当前作业调度，当且最优作业调度，机器2完成处理时间，机器1完成处理时间
	int f, bestf, n;	// 完成时间和，当前最优值，作业数
	
	private void Backtrack(int i) {
		if (i >=  n) {
			for (int j = 0; j < n; j++)
				bestx[j] = x[j];
			bestf = f;
		}
		else {
			for (int j = i; j < n; j++) {
				f1 += M[x[j]][0];
				if (i == 0)
					f2[i] = f1 + M[x[j]][1];
				else 
					f2[i] = (f2[i-1] > f1 ? f2[i-1] : f1) + M[x[j]][1];
				f += f2[i];
				if (f < bestf) {
					Swap(x, i, j);
					Backtrack(i+1);
					Swap(x, i, j);
				}
				f1 -= M[x[j]][0];
				f -= f2[i];
			}
		}
	}
	
	private void Swap(int x[], int i, int j) {
		int tmp = x[i];
		x[i] = x[j];
		x[j] = tmp;
	}
	
	public static int Flow(int M[][], int n, int bestx[]) {
		Flowshop X = new Flowshop();
		X.x = new int[n];
		X.f2 = new int[n];
		X.M = M;
		X.n = n;
		X.bestx = bestx;
		X.bestf = Integer.MAX_VALUE;
		X.f1 = 0;
		X.f = 0;
		for (int i = 0; i < n; i++)
			X.x[i] = i;
		X.Backtrack(0);
		return X.bestf;
	}
}
