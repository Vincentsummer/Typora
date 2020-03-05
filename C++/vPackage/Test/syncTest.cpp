//
// Created by vincent on 20-2-14.
//

#include <iostream>
#include <thread>
#include <sstream>
#include "../vThread/vSyncTest.h"

std::stringstream stream;

void printNumber(int x)
{
    stream << x;
}

int main()
{
    int n = 5;
    vincent::ZeroEvenOdd3 zeroEvenOdd(n);
    function<void(int)> fun;
    fun = printNumber;

    thread thread1(&vincent::ZeroEvenOdd3::zero, &zeroEvenOdd, fun);
    thread thread2(&vincent::ZeroEvenOdd3::odd, &zeroEvenOdd, fun);
    thread thread3(&vincent::ZeroEvenOdd3::even, &zeroEvenOdd, fun);

    thread1.join();
    thread2.join();
    thread3.join();

    cout << stream.str();
}