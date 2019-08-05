// ��������
// 1. �����а���һ�������ظ������֡�{2��3��1��0��2��5��3}
// 2. �����в������ظ������֡�{1��2��3��4��5}
// 3. ��Ч������������ָ�룬�����а���0��n-1֮������֣�{}��{1��3��5��7}

#include <iostream>
#include <string.h>

using namespace std;

bool duplicate(int numbers[], int length, int* duplication)
{
    // ��ָ�����Ч�����ж�
    if (numbers == NULL || length <= 0)
        return false;

    // ��Ч�����ж� �������а���0��n-1֮������֣�
    for (int i = 0; i < length; ++i){
        if (numbers[i] < 0 ||numbers[i] > length - 1)
            return false;
    }

    // ��������
    for (int i = 0; i < length; ++i){
        // �����i��λ�õ����ֲ�Ϊi
        while(numbers[i] != i){
            // �������ʾ�����ظ�����numbers[i]
            if (numbers[i] == numbers[numbers[i]]){
                *duplication = numbers[i];
                return true;
            }
            // ���򽻻���λ�õ�����
            int temp = numbers[i];
            numbers[i] = numbers[temp];
            numbers[temp] = temp;
        }
    }
    return false;
}
