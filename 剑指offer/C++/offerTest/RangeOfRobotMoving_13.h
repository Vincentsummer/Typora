/*
 * RangeOfRobotMoving_13.h
 *
 *  Created on: Aug 6, 2019
 *      Author: vincent
 */

#ifndef RANGEOFROBOTMOVING_13_H_
#define RANGEOFROBOTMOVING_13_H_

#include <string.h>

/**
 * 测试用例：
 * 1. 功能测试（方格为多行多列，k为正数）。
 * 2. 边界值测试（方格只有 一行或者只有一列，k等于0）
 * 3. 特殊输入测试（k为负数）
 * **/

int getDigitSum(int number){
	int sum = 0;
	while(number > 0){
		sum += number % 10;
		number /= 10;
	}
	return sum;
}

bool check(int threashold, int rows, int cols, int row, int col, bool* visited){
	if (row >= 0 && row < rows && col >= 0 && col < cols &&
			getDigitSum(row) + getDigitSum(col) <= threashold &&
			!visited[row * cols + col])
		return true;

	return false;
}

int movingCountCore(int threashold, int rows, int cols, int row, int col, bool* visited){
	int count = 0;
	if (check(threashold, rows, cols, row, col, visited)){
		visited[row * cols + col] = true;

		count = 1 + movingCountCore(threashold, rows, cols, row-1, col, visited)
				+ movingCountCore(threashold, rows, cols, row, col-1, visited)
				+ movingCountCore(threashold, rows, cols, row+1, col, visited)
				+ movingCountCore(threashold, rows, cols, row, col+1, visited);
	}
	return count;
}

int movingCount(int threashold, int rows, int cols){
	if (threashold < 0 || rows <= 0 || cols <= 0)
		return 0;

	bool* visited = new bool[rows * cols];
	memset(visited, 0, rows * cols);
//	for (int i = 0; i < rows * cols; ++i)
//		visited[i] = false;

	int count = movingCountCore(threashold, rows, cols, 0, 0, visited);

	delete[] visited;
	return count;
}

#endif /* RANGEOFROBOTMOVING_13_H_ */
