/*
 * PathOfMatrix_12.h
 *
 *  Created on: Aug 6, 2019
 *      Author: vincent
 */

#ifndef PATHOFMATRIX_12_H_
#define PATHOFMATRIX_12_H_

#include <string.h>

/**
 * 测试用例：
 * 1. 功能测试（在多行多列的矩阵中不存在路径）
 * 2. 边界值测试 （矩阵中只有一行或者一列，矩阵和路径中的所有字母都是相同的）
 * 3. 特殊输入测试 (输入nullptr指针)
 * **/

bool hasPathCore(const char* matrix, int rows, int cols, int row, int col, const char* str, int& pathLength, bool* visited){
	if (str[pathLength] == '\0')
		return true;

	bool hasPath = false;

	if (row >= 0 && row < rows && col >= 0 && col < cols && matrix[row * cols + col] == str[pathLength]
				&& !visited[row * cols + col]){
		++pathLength;
		visited[row * cols + col] = true;

		hasPath = hasPathCore(matrix, rows, cols, row, col-1, str, pathLength, visited)
				|| hasPathCore(matrix, rows, cols, row-1, col, str, pathLength, visited)
				|| hasPathCore(matrix, rows, cols, row, col+1, str, pathLength, visited)
				|| hasPathCore(matrix, rows, cols, row+1, col, str, pathLength, visited);

		if (!hasPath){
			--pathLength;
			visited[row * cols + col] = false;
		}
	}

	return hasPath;
}

bool hasPath(char* matrix, int rows, int cols, char* str){
	if (matrix == nullptr || rows < 1 || cols < 1 || str == nullptr)
		return false;
	bool* visited = new bool[rows * cols];

	// 函数原型是：void *memset(void *s, int ch, size_t n);
	// 函数功能是：将s所指向的某一块内存中的前n个字节的内容全部设置为ch指定的ASCII值。
	memset(visited, 0, rows * cols);

	int pathLength = 0;
	for (int row = 0; row < rows; ++row){
		for (int col = 0; col < cols; ++col){
			if (hasPathCore(matrix, rows, cols, row, col, str, pathLength, visited))
				return true;
		}
	}
	delete[] visited;
	return false;
}

#endif /* PATHOFMATRIX_12_H_ */
