//
// Created by vincent on 20-2-14.
//

#include <iostream>
#include "vSyncTest.h"

namespace vincent
{
    void ZeroEvenOdd1::zero(function<void(int)> printNumber)
    {
        for (int i = 1; i <= n; ++i){
            unique_lock<mutex> lck(mutex_);
            cond_.wait(lck, [=](){return count == 0 || count == 2;});
            printNumber(0);
            count = count == 0 ? 1 : 3;
            cond_.notify_all();
        }
    }

    void ZeroEvenOdd1::odd(function<void(int)> printNumber)
    {
        for (int i = 2; i <= n; i += 2){
            unique_lock<mutex> lck(mutex_);
            cond_.wait(lck, [=](){return count == 3;});
            printNumber(i);
            count = 0;
            cond_.notify_all();
        }
    }

    void ZeroEvenOdd1::even(function<void(int)> printNumber)
    {
        for (int i = 1; i <= n; i += 2){
            unique_lock<mutex> lck(mutex_);
            cond_.wait(lck, [=](){return count == 1;});
            printNumber(i);
            count = 2;
            cond_.notify_all();
        }
    }

    ZeroEvenOdd2::ZeroEvenOdd2(int n)
    {
        this->n = n;
        odd_.test_and_set();
        even_.test_and_set();
    }

    void ZeroEvenOdd2::zero(function<void(int)> printNumber)
    {
        for (int i = 1; i <= n; ++i){
            while (zero_.test_and_set());
            printNumber(0);
            if (i % 2 != 0) odd_.clear();
            else even_.clear();
        }
    }

    void ZeroEvenOdd2::odd(function<void(int)> printNumber)
    {
        for (int i = 1; i <= n; i += 2){
            while (odd_.test_and_set());
            printNumber(i);
            zero_.clear();
        }
    }

    void ZeroEvenOdd2::even(function<void(int)> printNumber)
    {
        for (int i = 2; i <= n; i += 2){
            while (even_.test_and_set());
            printNumber(i);
            zero_.clear();
        }
    }

    ZeroEvenOdd3::ZeroEvenOdd3(int n) : count_(1)
    {
        this->n = n;
        st.store(State::ZERO_ODD, memory_order_relaxed);
    }

    void ZeroEvenOdd3::zero(function<void(int)> printNumber)
    {
        while(count_ <= n){
            State s = st.load(memory_order_relaxed);
            if (s == State::ZERO_ODD || s == State::ZERO_EVEN) {
                printNumber(0);
                cout << count_ << endl;
                if (s == State::ZERO_ODD)
                    st.store(ODD, memory_order_relaxed);
                else
                    st.store(EVEN, memory_order_relaxed);
            }
            this_thread::yield();
        }
    }

    void ZeroEvenOdd3::odd(function<void(int)> printNumber)
    {
        while (count_ <= n){
            if (st.load(memory_order_relaxed) == State::ODD){
                printNumber(count_++);
                st.store(ZERO_EVEN, memory_order_relaxed);
            }
            this_thread::yield();
        }
    }

    void ZeroEvenOdd3::even(function<void(int)> printNumber)
    {
        while (count_ <= n){
            if (st.load(memory_order_relaxed) == State::EVEN){
                printNumber(count_++);
                st.store(ZERO_ODD, memory_order_relaxed);
            }
            this_thread::yield();
        }
    }
} // namespace vincent