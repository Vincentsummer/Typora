//
// Created by vincent on 20-2-25.
//

#include <iostream>
#include "../vSortAndSearch/sort.h"

int main()
{
    int len = 6;
    int A[len] = {5,2,4,6,1,3};
    vincent::bubbleSort(A, len);
    for (int i = 0; i < len; i++)
        std::cout << A[i] << " ";
}
