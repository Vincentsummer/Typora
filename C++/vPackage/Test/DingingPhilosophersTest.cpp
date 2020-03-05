//
// Created by vincent on 20-2-24.
//

#include "../vThread/vDiningPhilosophers.h"

int main()
{
    vector<thread> phlis;

    vincent::Philosopher::getInstance()->
            addMethods({function<void(ssize_t) >(vincent::phil::plan3)});

    for (; vincent::phil::count < vincent::phil::MAXID; vincent::phil::count++){
        phlis.push_back(thread(
                vincent::Philosopher::getInstance()->getMethod(0), vincent::phil::count));
    }


    for (auto it = phlis.begin(); it != phlis.end(); it++)
        it->join();

    return 0;

}