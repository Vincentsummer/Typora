#include <iostream>

using namespace std;

void replaceBlank(char str[], int length) {
	if (str == nullptr || length <= 0)
		return;
	int count = 0;

	// ͳ���ַ����пո�ĸ���
	for (int i = 0; i < length; i++){
		if (str[i] == ' ')
			count ++;
	}

	// ����ָ�룬һ��ָ��ԭʼ�ַ���ĩβ��һ��ָ���滻���ַ�����ĩβ��
	int indexOfOriginal = length - 1;
	int indexOfNew = indexOfOriginal + count * 2;

	// �Ӻ���ǰ����
	while (indexOfOriginal > 0 && indexOfNew > indexOfOriginal){
		// �����ո����滻
		if (str[indexOfOriginal] == ' '){
			str[indexOfNew --] = '0';
			str[indexOfNew --] = '2';
			str[indexOfNew --] = '%';
		}
		// ����ԭ�ַ��滻��indexOfNew��λ��
		else{
			str[indexOfNew --] = str[indexOfOriginal];
		}

		-- indexOfOriginal;
	}
}



