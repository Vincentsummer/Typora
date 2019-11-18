/*
 * Exponent_16.h
 *
 *  Created on: Aug 7, 2019
 *      Author: vincent
 */

#ifndef EXPONENT_16_H_
#define EXPONENT_16_H_

/**
 * 测试用例：
 * 将底数和指数分别设为正数、负数和零。
 * **/

bool g_InvalidInput = false;

// 两种求整数次方的方法
double PowerWithUnsignedExponent(double base, unsigned int exponent){
	double result = 1.0;
	for (unsigned int i = 1; i <= exponent; ++i)
		result *= base;

	return result;
}

// O(logn)解法
double PowerWithUnsignedExponent2(double base, unsigned int exponent){
	if (exponent == 0)
		return 1;
	if (exponent == 1)
		return base;

	double result = PowerWithUnsignedExponent2(base, exponent >> 1);
	result *= result;
	if (exponent & 0x01 == 1)
		result *= base;

	return result;
}

bool equal(double num1, double num2) {
	return (num1 - num2) > -0.0000001 && (num1 - num2) < 0.0000001 ? true : false;
}

// 分别讨论exponent不同情况
double Power(double base, int exponent){
	g_InvalidInput = false;

	if (equal(base, 0.0) && exponent < 0){
		g_InvalidInput = true;
		return 0.0;
	}

	unsigned int absExponent = (unsigned int)(exponent);
	if (exponent < 0)
		absExponent = (unsigned int)(-exponent);

	double result = PowerWithUnsignedExponent(base, absExponent);
	if (exponent < 0)
		result = 1.0 / result;

	return result;
}

#endif /* EXPONENT_16_H_ */
