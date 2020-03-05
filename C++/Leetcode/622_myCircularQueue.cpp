//
// Created by vincent on 20-2-8.
//

#include "solution.h"

/**
 * 622 设计循环队列
 * 使用vector设计
 * **/

namespace leetcode
{
    MyCircularQueue::MyCircularQueue(int k)
    {
        queue_.resize(k);
        size_ = k;
        front_ = 0;
        rear_ = 0;
        isFull_ = false;
    }

    MyCircularQueue::~MyCircularQueue()
    {}

    bool MyCircularQueue::isFull()
    {
        return isFull_;
    }

    bool MyCircularQueue::isEmpty()
    {
        return !isFull_ && front_ == rear_;
    }

    bool MyCircularQueue::enQueue(int value)
    {
        if (isFull()) return false;
        queue_[rear_] = value;
        rear_ = (rear_ + 1) % size_;

        if (front_ == rear_) isFull_ = true;
        return true;
    }

    bool MyCircularQueue::deQueue()
    {
        if (isEmpty()) return false;
        front_ = (front_ + 1) % size_;
        isFull_ = false;
        return true;
    }

    int MyCircularQueue::Front()
    {
        return isEmpty() ? -1 : queue_[front_];
    }

    int MyCircularQueue::Rear()
    {
        if (isEmpty()) return -1;

        int rear = (rear_ + size_ - 1) % size_;
        return isEmpty() ? -1 : queue_[rear];
    }

} // namespace leetcode