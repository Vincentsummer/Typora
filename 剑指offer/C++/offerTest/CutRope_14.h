/*
 * CutRope_14.h
 *
 *  Created on: Aug 6, 2019
 *      Author: vincent
 */

#ifndef CUTROPE_14_H_
#define CUTROPE_14_H_

#include <cmath>

/**
 * 测试用例：
 * 1. 功能测试（绳子的初始长度大于5）
 * 2. 边界值测试（绳子的初始长度小于5）
 * **/

// 动态规划方法
int maxProductAfterCutting_1(int length){
	if (length < 2)
		return 0;
	if (length == 2)
		return 1;
	if (length == 3)
		return 2;
    // 数组第i个元素表示把长度为i的绳子剪掉后的各段的最大乘积。
	int* products = new int[length+1];
	// 第i个元素为下标加1
	products[0] = 0;
	products[1] = 1;
	products[2] = 2;
	products[3] = 3;

	int max = 0;
	for (int i = 4; i <= length; ++i){
		max = 0;
		for (int j = 1; j <= i / 2; ++j){
			// 长度为i的绳子在剪第一次的时候有i-1种可能 其中最优值为 max(products[i] * products[i-j])
			int product = products[j] * products[i-j];
			if (max < product)
				max = product;
			products[i] = max;
		}
	}

	max = products[length];
	delete[] products;

	return max;
}

//贪心算法
int maxProductAfterCutting_2(int length){
	if (length < 2)
		return 0;
	if (length == 2)
		return 1;
	if (length == 3)
		return 2;

	// 尽可能多地剪去长度为 3 的绳子段
	int timesOf3 = length / 3;

	//当绳子最后剩下的长度为 4 的时候，不能再剪去长度为 3 的绳子段。 此时剪成 2X2
	if (length - timesOf3 * 3 == 1)
		timesOf3 -= 1;

	int timesOf2 = (length - timesOf3 * 3) / 2;
	return (int)(pow(3, timesOf3)) * (int)(pow(2, timesOf2));
}

#endif /* CUTROPE_14_H_ */
