//
// Created by vincent on 20-2-24.
//

#include <iostream>
#include "vDiningPhilosophers.h"

namespace vincent
{

    Philosopher *Philosopher::philosopher = 0;

    namespace phil {

        Semaphore   sems(MAXID - 1);
        Semaphore   chopsticks[MAXID];
        bool        chops[MAXID];
        chrono::milliseconds ms(1000);

        mutex mu;
        condition_variable cond;

        bool idError(ssize_t id) {
            if (id > MAXID) {
                cout << id << " is out of range!" << endl;
                return true;
            }
            return false;
        }

        void printAction(string action, ssize_t id)
        {
            lock_guard<mutex> lock(mu);
            cout << "Philosopher " << id
                 << "(" << this_thread::get_id() << ")" << " is " << action << endl;
        }

        void plan1(ssize_t id) {

            if (idError(id)) return;

            // 吃饭
            auto eating = [&chops](ssize_t i) -> void {
                // 使用一个额外的互斥锁和条件变量获取临界资源
                unique_lock<mutex> lck(mu);
                // 临界资源，筷子，将拿两只筷子的过程作为临界资源，一次只允许一个哲学家进入。
                // 左右两只筷子都可使用则都获取，否则都不获取
                while (chops[i] || chops[(i + 1) % MAXID]) {
                    cout << "Philosopher " << i
                         << "(" << this_thread::get_id() << ")" << " is wait." << endl;
                    cond.wait(lck);
                }
                chops[i] = true;
                chops[(i + 1) % MAXID] = true;
                cout << "Philosopher " << i
                     << "(" << this_thread::get_id() << ")" << " is eating" << endl;
            };

            // 思考，释放筷子
            auto thinking = [&chops](ssize_t i) -> void {
                unique_lock<mutex> lck(mu);
                chops[i] = false;
                chops[(i + 1) % MAXID] = false;
                cond.notify_all();
                cout << "Philosopher " << i
                     << "(" << this_thread::get_id() << ")" << " is thinking" << endl;
            };

            while (1) {
                thinking(id);
                this_thread::sleep_for(ms);     // 睡眠一秒
                eating(id);
            }
        }

        void plan2(ssize_t id)
        {
            if (idError(id)) return;

            while (1){
                // 奇数号哲学家，先拿左边筷子
                if (id % 2){
                    chopsticks[(id+1) % MAXID].wait();
                    chopsticks[id].wait();
                    printAction("eating", id);
                    this_thread::sleep_for(ms);
                    chopsticks[(id+1) % MAXID].signal_all();
                    chopsticks[id].signal_all();
                }
                // 偶数号哲学家，先拿右边筷子
                else{
                    chopsticks[id].wait();
                    chopsticks[(id+1) % MAXID].wait();
                    printAction("eating", id);
                    this_thread::sleep_for(ms);
                    chopsticks[id].signal_all();
                    chopsticks[(id+1) % MAXID].signal_all();
                }
                printAction("thinking", id);
            }
        }

        void plan3(ssize_t id)
        {
            if (idError(id)) return;

            while(1){
                sems.wait();
                chopsticks[id].wait();
                chopsticks[(id+1) % MAXID].wait();
                printAction("eating", id);
                this_thread::sleep_for(ms);

                chopsticks[(id+1) % MAXID].signal_all();
                chopsticks[id].signal_all();
                printAction("thinking", id);
                this_thread::sleep_for(ms);
                sems.signal_all();
            }
        }
    }
} // namespace vincent