
#ifndef FIBONACCI_10_H_
#define FIBONACCI_10_H_

/**
 * 测试用例：
 * 1. 功能测试
 * 2. 边界值测试
 * 3. 性能测试（输入较大的数字）
 * **/

// 递归解法 时间复杂度 O(n!)
long long Fibonacci_Recursive(unsigned int n){
	if (n <= 0)
		return 0;
	if (n == 1)
		return 1;

	return Fibonacci_Recursive(n-1) + Fibonacci_Recursive(n-2);
}

// 从下往上计算，动态规划思想, 时间复杂度 O(n)
long long Fibonacci(unsigned n){
	int result[2] = {0, 1};
	if (n < 2)
		return result[n];

	long long fibNMinusOne = 1;
	long long fibNMinusTwo = 0;
	long long fibN = 0;
	for (unsigned int i = 2; i <= n; i++){
		fibN = fibNMinusOne + fibNMinusTwo;
		fibNMinusTwo = fibNMinusOne;
		fibNMinusOne = fibN;
	}
	return fibN;
}

#endif /* FIBONACCI_10_H_ */
