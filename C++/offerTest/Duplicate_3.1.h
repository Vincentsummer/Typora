// 测试用例
// 1. 数组中包含一个或多个重复的数字。{2，3，1，0，2，5，3}
// 2. 数组中不包含重复的数字。{1，2，3，4，5}
// 3. 无效输入用例（空指针，数组中包含0到n-1之外的数字）{}，{1，3，5，7}

#include <iostream>
#include <string.h>

using namespace std;

bool duplicate(int numbers[], int length, int* duplication)
{
    // 空指针和无效长度判断
    if (numbers == NULL || length <= 0)
        return false;

    // 无效数字判断 （数组中包含0到n-1之外的数字）
    for (int i = 0; i < length; ++i){
        if (numbers[i] < 0 ||numbers[i] > length - 1)
            return false;
    }

    // 遍历数组
    for (int i = 0; i < length; ++i){
        // 数组第i个位置的数字不为i
        while(numbers[i] != i){
            // 该情况表示存在重复数字numbers[i]
            if (numbers[i] == numbers[numbers[i]]){
                *duplication = numbers[i];
                return true;
            }
            // 否则交换两位置的数字
            int temp = numbers[i];
            numbers[i] = numbers[temp];
            numbers[temp] = temp;
        }
    }
    return false;
}
