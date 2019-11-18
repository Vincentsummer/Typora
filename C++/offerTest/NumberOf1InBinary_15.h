/*
 * OnesNumInBinary_15.h
 *
 *  Created on: Aug 7, 2019
 *      Author: vincent
 */

#ifndef NUMBEROF1INBINARY_15_H_
#define NUMBEROF1INBINARY_15_H_

/**
 * 测试用例：
 * 1. 正数（包括边界值1,0x7FFFFFFF）。
 * 2. 负数（包括边界值0x80000000,0xFFFFFFFF）。
 * **/

// 方式一:将数n与1做与运算，接着将1左移后不断重复上述过程
int NumberOf1_1(int n){
	int count = 0;
	unsigned int flag = 1;
	while(flag){
		if (n & flag)
			count++;

		flag = flag << 1;
	}

	return count;
}

// 方式二：把一个整数减去1后再和该整数做与运算，会把该整数最右边的1变成0。

int NumberOf1_2(int n){
	int count = 0;
	while (n){
		n &= (n-1);
		count++;
	}
	return count;
}

#endif /* NUMBEROF1INBINARY_15_H_ */
