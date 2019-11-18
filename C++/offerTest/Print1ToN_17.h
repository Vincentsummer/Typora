/*
 * Print1ToN_17.h
 *
 *  Created on: Aug 8, 2019
 *      Author: vincent
 */

#ifndef PRINT1TON_17_H_
#define PRINT1TON_17_H_

#include <string.h>
#include <stdio.h>


/**
 * 测试用例：
 * 1. 功能测试（输入1、2、3...）
 * 2. 特殊输入测试（输入-1、0）
 * **/

// 判断number是否到达了最大的n位数。利用最高位的进位判断。
bool Increment(char* number){
	bool isOverflow = false;
	int nTakeOver = 0;
	int nLength = strlen(number);
	for (int i = nLength-1; i >= 0; i--){
		int nSum = number[i] - '0' + nTakeOver;
		if (i == nLength - 1)
			nSum++;
		if (nSum >= 10){
			if (i == 0)
				isOverflow = true;
			else{
				nSum -= 10;
				nTakeOver = 1;
				number[i] = '0' + nSum;
			}
		}
		else{
			number[i] = '0' + nSum;
			break;
		}
	}
	return isOverflow;
}

// 打印函数，打印时去掉前置0
void PrintNumber(char* number){
	bool isBegining0 = true;
	int nLength = strlen(number);

	for (int i = 0; i < nLength; ++i){
		if (isBegining0 && number[i] != '0')
			isBegining0 = false;
		if (!isBegining0)
			printf("%c", number[i]);
	}

	printf("\t");
}

void Print1ToMaxOfNDigits(int n){
	if (n <= 0)
		return;

	char* number = new char[n+1];
	memset(number, '0', n);
	number[n] = '\0';

	while(!Increment(number)){
		PrintNumber(number);
	}

	delete[] number;
}

/**
 * 递归法求解
 * **/

void Print1ToMaxOfDigitsRecursively(char* number, int length, int index){
	if (index == length - 1){
		PrintNumber(number);
		return;
	}

	for (int i = 0; i < 10; i++){
		number[index+1] = i + '0';
		Print1ToMaxOfDigitsRecursively(number, length, index+1);
	}
}

void Print1ToMaxOfNDigits2(int n){
	if (n <= 0)
		return;
	char* number = new char[n+1];
	number[n] = '\0';

	for (int i = 0; i < 10; i++){
		number[0] = i + '0';
		Print1ToMaxOfDigitsRecursively(number, n, 0);
	}

	delete[] number;
}

#endif /* PRINT1TON_17_H_ */
