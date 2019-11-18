package DP;

/**
 * 96. 不同的二叉搜索树(Catalan数：1,1,2,5,14,42，...，下标从0开始)
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * Catalan数：h(n+1) = h(0)*h(n) + h(1)h(n-1) + ... + h(n)h(0)
 * 递推式：h(n) = h(n-1)*(4*n - 2)/(n+1)
 * 直接解：h(n) = C(2n, n) / (n-1) = C(2n, n) - C(2n, n-1)，其中C为组合数。
 * Catalan数常见应用：
 * 1. 括号化：矩阵链乘
 * 2. 出栈次序（买票找零）
 * 3. 凸多边形三角划分
 * 4. 给定节点组成二叉搜索树
 * 5. n对括号正确匹配数目
 * 
 * **/

public class NumTrees_96 {

    public int numTrees(int n) {
    	if (n == 0)
    		return 1;
    	long catalan = 1;
    	for (int i = 1; i <= n; ++i)
    		catalan = catalan * (4 * i - 2) / (i + 1);
    	return (int)catalan;
    }
}
