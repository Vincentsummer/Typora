package vPack;

/**
 * 字符串 排序算法与传统的基于比较的通用排序算法不同，主要有LSD string sort、MSD string sort以及3-way string quicksort。
 * 这些排序算法都利用了字母表（Alphabet）的概念，时间复杂度通常都是线性的（传统的通用排序算法最佳性能只能达到线性对
 * 数级别NlgN），但通常需要额外的空间。
 * 字母表（Alphabet）
 * 字母表就是给定一个字符集合的字典，保存着字符和索引之间的唯一映射。
 * 如ASCII字母表，每个ASCII字符都有唯一的数值，且根据字符计算数值所需的时间通常是常数级别。
 * 键索引计数法
 * 键索引计数法（key-indexed counting sort），是上述各类排序算法的基础。该算法仅针对键范围已知的序列（键值需要大于
 * 等于0），效率高于通用排序算法。其另一特点是，该算法是稳定的。
 * 基本思想
 * 假设有一组待排序的键，其值范围在O~R-1之间。
 * 那么通过计算每个键的频率，就可以知道每个键在排序结果中的起始位置：
 * 每个键在排序结果中的起始索引=前一个键的起始索引+前一个键的频次
 * 具体步骤：
 * 统计各个键出现的频次，如count[i]表示键i出现的频次；
 * 统计每个键k在排序结果中的起始索引index[k]；
 * index[k] = index[k-1] + count[k-1];
 * 遍历原序列的每个元素，按照键的起始索引复制到辅助数组；（由此可知，该排序算法是稳定的）
 * 将辅助数组复制回原序列。
 * **/

public class StringSort {

	private static final int R = 256;		// 字符串键范围
	private static int f = 0;
	/**
	 * LSD：低位优先的字符串排序（Least-Signifcant-Digit First，LSD），仅适用于键长度都相同的序列。
	 * 基本步骤：
	 * 首先，假设待排序的键长度均相同，设为W。
	 * 排序时，从键的低位（最右侧）开始，依次向左，先对键的最右一个字符采用键索引计数法进行排序。
	 * 然后，从右向左以下一个字符作为关键字，用键索引计数法排序，总共需要排序W次。
	 * **/
	public void LSDSort(String str[], int w) {
		// str为待排序字符串数组， w 为字符串长度，所有字符串长度必须相同
		int n = str.length;
		String aux[] = new String[n];		// 辅助数组
		
		// 从右往左，对第k个字符，用键索引计数法排序
		for (int k = w - 1; k >= 0; k--) {
			int count[] = new int[R+1];
			// 计算键的频率，对应索引值加1，以便计算初始索引
			for (int i = 0; i < n; i++)
				count[str[i].charAt(k)+1]++;
			// 计算键排序后的初始索引
			for (int r = 0; r < R; r++)
				count[r+1] += count[r];
			// 元素分类
			for (int i = 0; i < n; i++)
				aux[count[str[i].charAt(k)]++] = str[i];
			// 回写
			for (int i = 0; i < n; i++)
				str[i] = aux[i];
		}
	}
	
	/**
	 * MSD：高位优先的字符串排序（Most-Signifcant-Digit First，MSD），可以处理不等长的字符串。
	 * 基本步骤：
	 * MSD算法从左向右检查每个字符：
	 * 先对第一个字符进行键索引计数排序，排序后将所有首字符相同的归为一个子数组。
	 * 然后对每个子数组分别按照步骤1进行递归排序。
	 * 注意：在对各个子数组进行排序时，需要特别注意到达字符串末尾的情况。
	 * 比如“RON”和“RONG”，显然“RON”应小于“RONG”，所以字符串末尾在字符集中对应的整数应该最小。
	 * 为此定义一个特殊方法charAt，用来映射键字符和整数值，当索引达到字符串末尾时，默认为最小键0。
	 * **/
	
	public void MSD(String str[]) {
		String aux[] = new String[str.length];
		MSDSort(str, aux, 0, str.length-1, 0);
	}
	
	private void MSDSort(String str[], String[] aux, int start, int end, int index) {
	    /**
	     * MSD排序的递归方法. 即对str[start...end]中的每个字符串，按照第index个字符进行键索引计数排序
	     * 
	     * @param array 待排序子序列
	     * @param aux   辅助数组		
	     * @param start 子序列起始索引
	     * @param end   子序列结束索引
	     * @param index 子序列中运用键索引计数排序的字符索引位置，从0开始计
	     */
		if (start >= end)
			return;
		// 1. 频数统计
		int count[] = new int[R+2];
		for (int i = start; i <= end; i++)
			count[charAt(str[i], index)+2]++;
		// 2. 转换为键在排序结果中的起始位置, 注意str数组的起始位置为start
		for (int i = 0; i < R+1; i++)
			count[i+1] += count[i];
		// 3. 复制排序结果至辅助数组
		for (int i = start; i <= end; i++)
			aux[count[charAt(str[i], index)+1]++] = str[i];
		// 4. 回写排序结果
		for (int i = start; i <= end; i++)
			str[i] = aux[i-start];	// 此处为aux[i-start]，count[i] 记录的是字符在子数组中的位置
		
		// 递归处理首字符相同的子数组，此时count保存的是每个键结束的索引+1
		for (int i = 0; i < R; i++)
			MSDSort(str, aux, start + count[i], start + count[i+1] - 1, index+1);
		
	}
	// 计算字符串在索引index处的字符键值
	private int charAt(String s, int index) {
		if (index < 0) {
			System.out.println("invalid index value!");
			return -1;
		}
		if (index >= s.length())
			return -1;
		return s.charAt(index);
	}
	
	/**
	 * 三向字符串快速排序：是对高位优先（MSD）的字符串排序（Most-Signifcant-Digit First）的改进。
	 * 基本步骤：
	 * 该算法将字符串数组切分成三个子数组：
	 * 一个含有所有首字母小于切分字符的子数组；
	 * 一个含有所有首字母等于切分字符的子数组；
	 * 一个含有所有首字母大于切分字符的子数组；
	 * 然后，递归地对这三个数组排序，对于所有首字母等于切分字符的子数组，在递归排序时忽略首字母。
	 * @return 
	 * **/
	public void threeWayStrQuickSort(String str[], int low, int high, int d) {
		if (low >= high || low < 0 || high < 0)
			return;
		int left = low - 1, right = left;
		// 查找字符表得到字符对应的唯一键值，并以此为基数
		int v = charAt2(str[high], d);

		for (int j = low; j <= high; j++) {
			int t = charAt2(str[j], d);
			if (v >= t) {
				swap(str, ++right, j);
				if (v > t)
					swap(str, ++left, right);
			}
		}
		threeWayStrQuickSort(str, low, left, d);
		threeWayStrQuickSort(str, right+1, high, d);
		// str[left...right]之间都是 d 索引处等于 v 的键。若v<0，说明已达到字符串末尾，不需要再进行三向切分
		if (v >= 0)
			threeWayStrQuickSort(str, left+1, right, d+1);
		
	}
	private int charAt2(String s, int d) {
		if (d >= s.length())
			return -1;
		return s.charAt(d);
	}
	
	private void swap(String str[], int idx1, int idx2) {
		String tmp = str[idx1];
		str[idx1] = str[idx2];
		str[idx2] = tmp;
	}
	public void toString(String str[]) {
		for (String s : str)
			System.out.print(s + " ");
		System.out.println("");
	}
}
