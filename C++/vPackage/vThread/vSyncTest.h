//
// Created by vincent on 20-2-14.
//

#ifndef CPP_VSYNCTEST_H
#define CPP_VSYNCTEST_H

/**
 * leetcode 1116. 打印零与奇偶数
 *
 * 题目： 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *       线程 A 将调用 zero()，它只输出 0 。
 *       线程 B 将调用 even()，它只输出偶数。
 *       线程 C 将调用 odd()，它只输出奇数。
 *   每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，
 * 其中序列的长度必须为 2n。
 *
 * 多线程间的同步
 * 1. 互斥锁 + 条件变量 / 信号量（可由互斥锁和条件变量实现）
 * 2. 使用原子布尔类型 std::atomic_flag 无锁设计
 * 3. 使用原子类，std::atomic 无锁设计
 * **/

#include <functional>
#include <mutex>
#include <condition_variable>
#include <atomic>
#include <thread>

using namespace std;

namespace vincent
{
    class VSyncTest
    {
    public:
        virtual void zero(function<void(int)> printNumber) = 0;
        virtual void even(function<void(int)> printNumber) = 0;
        virtual void odd(function<void(int)> printNumber) = 0;
    protected:
        int n;
    };

    /// 使用互斥锁 + 条件变量
    class ZeroEvenOdd1 : VSyncTest
    {
    public:
        ZeroEvenOdd1(int n) : count(0) { this->n = n; }

        void zero(function<void(int)> printNumber);
        void odd(function<void(int)> printNumber);
        void even(function<void(int)> printNumber);


    private:
        int count;
        mutex mutex_;
        condition_variable cond_;
    };

    /// 使用std::atomic_flag, 无锁设计
    class ZeroEvenOdd2 : VSyncTest
    {
    public:
        ZeroEvenOdd2(int n);

        void zero(function<void(int)> printNumber);
        void odd(function<void(int)> printNumber);
        void even(function<void(int)> printNumber);

    private:
        atomic_flag zero_ = ATOMIC_FLAG_INIT;
        atomic_flag odd_  = ATOMIC_FLAG_INIT;
        atomic_flag even_ = ATOMIC_FLAG_INIT;
    };

    /// 使用原子类，std::atomic 无锁设计
    class ZeroEvenOdd3 : VSyncTest
    {
    public:
        ZeroEvenOdd3(int n);

        void zero(function<void(int)> printNumber);
        void odd(function<void(int)> printNumber);
        void even(function<void(int)> printNumber);
    private:
        int count_;
        enum State { ZERO_ODD, ZERO_EVEN, ODD, EVEN };
        std::atomic<State> st;
    };

} // namespace vincent

#endif //CPP_VSYNCTEST_H
