//
// Created by vincent on 20-2-13.
//

#include <mutex>
#include <atomic>
#include <thread>
#include "solution.h"

/**
 * 1116. 打印零与奇偶数
 * 多线程间的同步
 * 1. 互斥锁 + 条件变量
 * 2. 信号量（可由互斥锁和条件变量实现）
 * 3. 使用原子布尔类型 std::atomic_flag 无锁设计
 * 4. 使用原子类，std::atomic 无锁设计
 * **/

namespace leetcode
{
    void ZeroEvenOdd::zero(function<void(int)> printNumber)
    {
        for (int i = 1; i <= n; ++i){
            unique_lock<mutex> lck(mutex_);
            cond_.wait(lck, [=](){return count == 0 || count == 2;});
            printNumber(0);
            count = count == 0 ? 1 : 3;
            cond_.notify_all();
        }
    }

    void ZeroEvenOdd::odd(function<void(int)> printNumber)
    {
        for (int i = 1; i <= n; i += 2){
            unique_lock<mutex> lck(mutex_);
            cond_.wait(lck, [=](){return count == 1;});
            printNumber(i);
            count = 2;
            cond_.notify_all();
        }
    }

    void ZeroEvenOdd::even(function<void(int)> printNumber)
    {
        for (int i = 2; i <= n; i += 2){
            unique_lock<mutex> lck(mutex_);
            cond_.wait(lck, [=](){return count == 3;});
            printNumber(i);
            count = 0;
            cond_.notify_all();
        }
    }

    class ZeroEvenOdd2 {
    private:
        enum State { ZERO_ODD, ZERO_EVEN, ODD, EVEN };
        int n;
        int idx;
        std::atomic<State> st;

    public:
        ZeroEvenOdd2(int n) {
            this->n = n;
            idx = 1;
            st.store(State::ZERO_ODD, std::memory_order_relaxed);
        }

        // printNumber(x) outputs "x", where x is an integer.
        void zero(function<void(int)> printNumber) {
            while(idx <= n){
                State s = st.load(memory_order_relaxed);
                if (s == State::ZERO_ODD || s == State::ZERO_EVEN) {
                    printNumber(0);
                    if (s == State::ZERO_ODD)
                        st.store(ODD, memory_order_relaxed);
                    else
                        st.store(EVEN, memory_order_relaxed);
                }
                this_thread::yield();
            }
        }

        void even(function<void(int)> printNumber) {
            while (idx <= n) {
                if (st.load(std::memory_order_relaxed) == State::EVEN) {
                    printNumber(idx++);
                    st.store(State::ZERO_ODD, std::memory_order_relaxed);
                }
                std::this_thread::yield();
            }
        }

        void odd(function<void(int)> printNumber) {
            while (idx <= n) {
                if (st.load(std::memory_order_relaxed) == State::ODD) {
                    printNumber(idx++);
                    st.store(State::ZERO_EVEN, std::memory_order_relaxed);
                }
                std::this_thread::yield();
            }
        }
    };
} // namespace leetcode