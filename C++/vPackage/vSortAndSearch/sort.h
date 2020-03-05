//
// Created by vincent on 20-2-25.
//

#ifndef CPP_SORT_H
#define CPP_SORT_H

#include "../vUtils.h"

namespace vincent
{
    /// 插入排序
    void insertSort(int *A, int len);

    /// 选择排序
    void selectSort(int *A, int len);

    /// 冒泡排序
    void bubbleSort(int *A, int len);

    /// 归并排序
    void merge(int *A, int p, int q, int r);
    void mergeSort(int *A, int p, int r);

    /// 快速排序
    int patition(int *A, int p, int r);
    void quickSort(int *A, int p, int r);

    /// 堆排序
    inline int parent(int i) { return (i-1) / 2; }     // 父节点下标
    inline int left(int i) { return 2 * i + 1; }       // 左孩子下标
    inline int right(int i) { return 2 * i + 2; }      // 右孩子下标

    void maxHeapify(int *A, int i, int len);     // 维护大顶堆的性质
    void buildMaxHeap(int *A, int len);          // 建大顶堆
    void heapSort(int *A, int len);              // 堆排序

} // namespace vincent

#endif //CPP_SORT_H
