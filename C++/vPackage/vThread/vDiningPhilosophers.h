//
// Created by vincent on 20-2-24.
//

/**
 * 哲学家进餐问题（经典多线程(进程)同步问题）
 * 约束条件：
 * (1)只有拿到两只筷子时，哲学家才能吃饭。
 * (2)如果筷子已被别人拿走，则必须等别人吃完之后才能拿到筷子。
 * (3)任一哲学家在自己未拿到两只筷子吃完饭前，不会放下手中已经拿到的筷子。
 *
 * 该问题涉及信号量、管程和死锁等概念
 * 死锁：
 *    产生条件：
 *      1. 互斥条件：进程对所分配到的资源进行排它性使用。
 *      2. 请求和保持条件：进程已经保持至少一个资源，但又提出了新的资源请求，而该资源已被其它进程占有，
 *         此时请求进程阻塞，但又对自己已获得的其它资源保持不放。
 *      3. 不剥夺条件：指进程已获得的资源，在未使用完之前，不能被剥夺，只能在使用完时由自己释放。
 *      4. 环路等待条件：指在发生死锁时，必然存在一个进程——资源的环形链。
 *    解决方法：
 *      1. 死锁预防：破坏产生死锁的四个必要条件中的一个或者几个，来预防发生死锁。
 *      2. 死锁避免
 *      3. 死锁检测和解除
 *
 * 哲学家进餐问题的死锁预防
 *  1.破坏请求和保持条件：利用原子思想完成。即只有拿起两支筷子的哲学家才可以进餐，否则，一支筷子也不拿。
 *      解法1 ： 利用AND信号量机制(C++不提供AND信号量机制，使用解法2可实现同样的效果)
 *      解法2 ： 利用记录型信号量机制实现：
 *          使用一个额外的信号量定义：semaphore mutex=1。
 *          将拿两只筷子的过程作为临界资源，一次只允许一个哲学家进入。
 *  2. 破坏环路等待条件
 *      解法1 ： 奇数号哲学家先拿他左边的筷子，偶数号哲学家先拿他右边的筷子。
 *      解法2 ： 至多允许四位哲学家进餐，将最后一个哲学家停止申请资源，断开环路。
 *          增加一个信号量定义semaphore count=4。
 *      解法3 ： 哲学家申请资源总是按照资源序号先大后小的顺序，这样0~3号哲学家先右后左，但是4号哲学家
 *            先左后右，改变方向，破坏了环路。
 * **/

#ifndef CPP_VDININGPHILOSOPHERS_H
#define CPP_VDININGPHILOSOPHERS_H

#include <functional>
#include <vector>
#include <thread>
#include <sstream>
#include <set>
#include "vSemaphore.h"

using namespace std;

namespace vincent
{

    namespace phil
    {
        const static int MAXID = 5;
        static int count = 0;

        void printAction(string action, ssize_t id);

        void plan1(ssize_t id);     // 利用记录型信号量机制
        void plan2(ssize_t id);     // 奇数号哲学家先拿左筷子，偶数号哲学家先拿右筷子。
        void plan3(ssize_t id);     // 至多允许四位哲学家进餐，将最后一个哲学家停止申请资源，断开环路。

        extern Semaphore sems;
        extern Semaphore chopsticks[];
        extern bool chops[];  // 临界资源，筷子
        extern chrono::milliseconds ms;
        extern mutex mu;
        extern condition_variable cond;

    } // namespace phil

    class Philosopher
    {
    public:

        static Philosopher *philosopher;
        static Philosopher* getInstance()
        {
            if (philosopher) return philosopher;
            return philosopher = new Philosopher();
        }

        void addMethods(initializer_list<function<void(ssize_t)> > me)
        {
            for (auto it = me.begin(); it != me.end(); it++)
                method.push_back(*it);
        }

        void addMethod(function<void(ssize_t)> t)
        {
            method.push_back(t);
        }

        function<void(ssize_t)> getMethod(size_t i)
        {
            return method[i];
        }

    private:
        vector<function<void(ssize_t)> > method;
        Philosopher() {}
        ~Philosopher() {}

        Philosopher(const Philosopher &t) = delete;
        Philosopher& operator=(const Philosopher &t) = delete;
    };

} // namespace vincent

#endif //CPP_VDININGPHILOSOPHERS_H
