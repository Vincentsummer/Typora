//
// Created by vincent on 20-2-25.
//

#include <cstdint>
#include "sort.h"

void vincent::insertSort(int *A, int len)
{
    for (int j = 1; j < len; j++){
        int key = A[j], i = j - 1;
        while (i >= 0 && A[i] > key){
            A[i+1] = A[i];
            i--;
        }
        A[i+1] = key;
    }
}

/// 选择排序，每次选择最小值
void vincent::selectSort(int *A, int len)
{
    for (int i = 0; i < len-1; i++){
        int idx = i;
        for (int j = i+1; j < len; ++j){
            if (A[j] < A[idx]) idx = j;
        }
        swap(A, idx, i);
    }
}


/// 冒泡排序，两两交换，每轮选出最大值
void vincent::bubbleSort(int *A, int len)
{
    for (int i = len-1; i >= 0; --i){
        for (int j = 0; j < i; j++){
            if (A[j] > A[j+1])
                swap(A, j, j+1);
        }
    }
}

void vincent::merge(int *A, int p, int q, int r)
{
    int len1 = q - p + 1, len2 = r - q;
    int L[len1+1], R[len2+1];

    for (int i = 0; i < len1; i++)
        L[i] = A[p+i];

    for (int i = 0; i < len2; i++)
        R[i] = A[q+i+1];

    L[len1] = INT32_MAX;    // 哨兵， 结尾标志
    R[len2] = INT32_MAX;

    int i = 0, j = 0;
    for (int k = p; k <= r; ++k){
        if (L[i] < R[j])
            A[k] = L[i++];
        else
            A[k] = R[j++];
    }
}

void vincent::mergeSort(int *A, int p, int r)
{
    if (p < r){
        int q = (p + r) / 2;
        mergeSort(A, p, q);
        mergeSort(A, q+1, r);
        merge(A, p, q, r);
    }
}

void vincent::swap(int *A, int idx1, int idx2)
{
    int tmp = A[idx1];
    A[idx1] = A[idx2];
    A[idx2] = tmp;
}

int vincent::patition(int *A, int p, int r)
{
    int i = p - 1;
    int x = A[r];
    for (int j = p; j < r; j++){
        if (x >= A[j])
            swap(A, ++i, j);
    }
    swap(A, ++i, r);
    return i;
}

void vincent::quickSort(int *A, int p, int r)
{
    if (p < r){
        int q = patition(A, p, r);
        quickSort(A, p, q-1);
        quickSort(A, q+1, r);
    }
}

void vincent::maxHeapify(int *A, int i, int heapSize)
{
    int l = left(i), r = right(i);
    int lastest = i;
    if (l < heapSize && A[l] > A[i])
        lastest = l;
    if (r < heapSize && A[r] > A[lastest])
        lastest = r;
    if (lastest != i){
        swap(A, lastest, i);
        maxHeapify(A, lastest, heapSize);
    }
}

void vincent::buildMaxHeap(int *A, int len)
{
    for (int i = (len-1) / 2; i >= 0; --i)
        maxHeapify(A, i, len);
}

void vincent::heapSort(int *A, int len)
{
    int heapSize = len;
    buildMaxHeap(A, len);
    for (int i = len-1; i >= 0; --i){
        swap(A, 0, i);
        heapSize--;
        maxHeapify(A, 0, heapSize);
    }
}