//
// Created by vincent on 20-2-14.
//

#include <thread>
#include <vector>
#include <iostream>
#include "../vThread/vSemaphore.h"
#include "../vTime/vTime.h"

using namespace std;

vincent::VSemaphore sem(3);
mutex   mu;

void worker()
{
    sem.wait();
    thread::id thread_id = this_thread::get_id();
    string now = vincent::FormatTimeNow("%H:%M:%S");
    {
        lock_guard<mutex> lock(mu);
        cout << "Thread " << thread_id << ": wait succeeded" << " (" << now << ")" << endl;
    }

    this_thread::sleep_for(chrono::seconds(1));
    sem.signal();
}

int main()
{
    const size_t SIZE = 3;
    vector<thread> v;
    v.reserve(SIZE);
    for (size_t i = 0; i < SIZE; ++i){
        v.emplace_back(worker);
    }

    cout << v.capacity() << endl;
    for (thread &t : v)
        t.join();

    return 0;
}