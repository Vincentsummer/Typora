package ClassicProblem;

/**
 * 回溯法之装载问题
 * 具体问题见Algorithm_learning.md
 * **/

public class Loading {
	private int n;		// 集装箱数
	private int[] w;		// 集装箱重量数组
	private int c, cw, bestw;	// 分别为第一艘轮船的载重量，当前载重量和当前最优载重量
	private int r; 		// 剩余集装箱重量
	private int x[], bestx[]; 	// 当前解和当前最优解
	
	private void Backtrack (int i){
		// 搜索第 i 层节点
		if (i >= n) {		// 到达叶节点
			if (cw > bestw) {
				for (int j = 0; j < n; ++j)
					bestx[j] = x[j];
				bestw = cw;
			}
			return;
		}
		// 搜索子树
		r -= w[i];
	 	// 搜索左子树
		if (cw + w[i] <= c) {
			x[i] = 1;
			cw += w[i];
			Backtrack(i+1);
			cw -= w[i];
		}
		//	搜索右子树
		if (cw + r > bestw)  	// 上限函数，剪枝 若 cw + r <= w 则不向下搜索
			x[i] = 0;
			Backtrack(i+1);		
		r += w[i];
	}
	
	public static int maxLoading(int w[], int c, int n, int bestx[]) {
		// 返回最优载重量
		Loading X = new Loading();
		// 初始化X
		X.w = w;
		X.c = c;
		X.n = n;
		X.x = new int[n];
		X.bestx = bestx;
		X.bestw = 0;
		X.cw = 0;
		// 初始化 r
		X.r = 0;
		for (int i = 0; i < n; ++i)
			X.r += w[i];
		// 计算最优载重量
		X.Backtrack(0);
		return X.bestw;
	}
	
	public static int IterativeMaxLoading(int w[], int c, int n, int bestx[]) {
		/**
		 * 迭代回溯法
		 * 返回最优载重量及其相应解
		 * **/
		
		// 初始化根节点
		int i = 0;	// 当前层
		int x[] = new int[n];
		int bestw = 0, cw = 0, r = 0;
		for (int j = 0; j < n; ++j)
			r += w[j];
		// 搜索子树
		while(true) {
			while(i < n && cw + w[i] <= c) {
				// 进入左子树
				r -= w[i];
				cw += w[i];
				x[i] = 1;
				i++;
			}
			if (i >= n) {	// 到达叶节点
				for (int j = 0; j < n; ++j)
					bestx[j] = x[j];
				bestw = cw;
			}
			else {		// 进入右子树
				r -= w[i];
				x[i] = 0;
				i++;
			}
			while (cw + r <= bestw) {
				// 剪枝回溯
				i--;
				while(i > 0 && x[i] == 0) {
					// 从右子树返回
					r += w[i];
					i--;
				}
				if (i == 0)
					return bestw;
				// 进入右子树
				x[i] = 0;
				cw -= w[i];
				i++;
			}
		}
	}
}










