
#ifndef FIBONACCI_10_H_
#define FIBONACCI_10_H_

/**
 * ����������
 * 1. ���ܲ���
 * 2. �߽�ֵ����
 * 3. ���ܲ��ԣ�����ϴ�����֣�
 * **/

// �ݹ�ⷨ ʱ�临�Ӷ� O(n!)
long long Fibonacci_Recursive(unsigned int n){
	if (n <= 0)
		return 0;
	if (n == 1)
		return 1;

	return Fibonacci_Recursive(n-1) + Fibonacci_Recursive(n-2);
}

// �������ϼ��㣬��̬�滮˼��, ʱ�临�Ӷ� O(n)
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
