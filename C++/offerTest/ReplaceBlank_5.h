#include <iostream>

using namespace std;

void replaceBlank(char str[], int length) {
	if (str == nullptr || length <= 0)
		return;
	int count = 0;

	// 统计字符串中空格的个数
	for (int i = 0; i < length; i++){
		if (str[i] == ' ')
			count ++;
	}

	// 两个指针，一个指向原始字符串末尾，一个指向替换后字符串的末尾。
	int indexOfOriginal = length - 1;
	int indexOfNew = indexOfOriginal + count * 2;

	// 从后往前遍历
	while (indexOfOriginal > 0 && indexOfNew > indexOfOriginal){
		// 碰到空格则替换
		if (str[indexOfOriginal] == ' '){
			str[indexOfNew --] = '0';
			str[indexOfNew --] = '2';
			str[indexOfNew --] = '%';
		}
		// 否则将原字符替换到indexOfNew的位置
		else{
			str[indexOfNew --] = str[indexOfOriginal];
		}

		-- indexOfOriginal;
	}
}



