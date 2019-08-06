/*
 * MinValueOfRevSeq.h
 *
 *  Created on: Aug 5, 2019
 *      Author: vincent
 */

#ifndef MINVALUEOFREVSEQ_11_H_
#define MINVALUEOFREVSEQ_11_H_

/**
 * 测试用例：
 * 1. 功能测试
 * 2. 边界值测试
 * 3. 特殊输入测试
 * **/

int MinInOrder(int* numbers, int index1, int index2){
	int result = numbers[index1];
	for (int i = index1 + 1; i <= index2; ++i){
		if (result > numbers[i])
			result = numbers[i];
	}
	return result;
}

int Min(int* numbers, int length){
	if (numbers == nullptr || length <= 0)
		throw "Invalid parameters";
	int index1 = 0, index2 = length - 1, indexMid = index1;

	// 二分查找
	while(numbers[index1] >= numbers[index2]){
		// 当两指针相隔为1时 break
		if (index2 - index1 == 1){
			indexMid = index2;
			break;
		}

		indexMid = (index1 + index2) / 2;

		// 当三者指向的数字都相等时，只能顺序查找
		if (numbers[index1] == numbers[index2] && numbers[indexMid] == numbers[index1])
			return MinInOrder(numbers, index1, index2);

		if (numbers[indexMid] >= numbers[index1])
			index1 = indexMid;
		else if (numbers[indexMid] <= numbers[index2])
			index2 = indexMid;
	}
	return numbers[indexMid];
}

#endif /* MINVALUEOFREVSEQ_11_H_ */
