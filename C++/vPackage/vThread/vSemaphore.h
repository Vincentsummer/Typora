//
// Created by vincent on 20-2-14.
//

#ifndef CPP_VSEMAPHORE_H
#define CPP_VSEMAPHORE_H

#include <mutex>
#include <condition_variable>

namespace vincent
{
    class Semaphore
    {
    public:
        explicit Semaphore(int count = 1) : count_(count){}

        // V操作，释放资源，count_加1，并唤醒等待中的线程
        void signal_one()
        {
            std::unique_lock<std::mutex> lck(mutex_);
            ++count_;
            cond_.notify_one();
        }

        void signal_all()
        {
            std::unique_lock<std::mutex> lck(mutex_);
            ++count_;
            cond_.notify_all();
        }

        // P操作，当count_ <= 0时线程阻塞，否则获取资源，count_ 减1
        void wait()
        {
            std::unique_lock<std::mutex> lck(mutex_);
            cond_.wait(lck, [=](){return count_ > 0;});
            --count_;
        }

        int getCount() { return count_; }

    private:
        std::mutex              mutex_;
        std::condition_variable cond_;
        int                     count_;
    };
} // namespace leetcode

#endif //CPP_VSEMAPHORE_H
