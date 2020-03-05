//
// Created by vincent on 20-2-13.
//

#ifndef CPP_VTHREAD_H
#define CPP_VTHREAD_H

using namespace std;

namespace vincent
{
    class Thread
    {
    public:
        Thread(thread t) : t_(move(t))
        {
            if (!t_.joinable())
                throw std::logic_error("No Thread!");
        }

        ~Thread()
        {
            if (t_.joinable())
                t.join;
        }

        Thread(Thread const&) = delete;
        Thread& operator=(Thread const&) = delete;

    private:
        void printNumber(int x) { std::cout << x << std::endl; }

        std::thread t_;
    };

} // namespace vincent



#endif //CPP_VTHREAD_H
