//
// Created by vincent on 20-3-1.
//

#ifndef CPP_VBANKER_H
#define CPP_VBANKER_H

/**
 * 银行家算法(死锁避免算法)
 * 死锁避免：在资源动态分配的过程中，防止系统进入不安全状态。
 * 安全状态：系统能按照某种进程推进顺序（安全序列）进行。
 * 银行家算法数据结构：
 *  1. 可利用资源向量（Available）：系统还可以分配的资源。
 *  2. 最大需求矩阵（Max）：进程的最大资源需要。
 *  3. 分配矩阵（Alloction）：进程已经获得的资源。
 *  4. 需求矩阵（Need）：进程还需要获得的资源。
 * 算法步骤：
 *  1. 假设 P1 进程提出请求 K 个资源。
 *  2. 如果 K <= Need，就继续步骤；否则出错，因为请求资源 K 不能超过还需要获得的资源。
 *  3. 如果 K <= Available，就继续步骤；否则出错，因为请求资源 K 不能超过系统还可以分配的资源 Available。
 *  4. 系统试探分配资源，并修改下列数据。
 *      Available = Available - K；表示分配给 P1 K 个资源后，还剩多少系统可分配资源。
 *      Allocation = Allocation + K；表示 P1 已经获得的资源。
 *      Need = Need - K；表示进程 P1 还需要获得的资源。
 *  5. 此时系统执行安全性算法，计算进程是否处于安全性状态。
 * 安全性算法：
 *  工作向量（Work）：系统提供给进程的各类资源数目。
 *  Finish：表示系统是否有足够的资源分配给进程，这是一个布尔值。初始化为 false。
 *  算法步骤：
 *   在进程集合中找到下述条件的进程
 *      1. Finish[ i ] = false;
 *      2. Need <= Work。
 *      3. 进程执行完毕。
 *      4. Work = Work + Allocation。
 *      5. Finish[i] = true。
 *      6. 返回继续执行 1，寻找其他的进程分配资源。
 *      7. 若所有的 Finish 为 true 则安全。
 * **/

#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

namespace vincent
{
    class Banker
    {
    public:

        Banker(unsigned long process, unsigned long resNum) :
                PROCESS(process),
                REC_NUM(resNum),
                Available(PROCESS),
                sign(PROCESS),
                work(PROCESS, vector<int>(REC_NUM)),
                workAll(PROCESS, vector<int>(REC_NUM)),
                Max(PROCESS, vector<int>(REC_NUM)),
                Allocation(PROCESS, vector<int>(REC_NUM)),
                Need(PROCESS, vector<int>(REC_NUM))
        {}

        void readFile(const string&, const string&);                    // 读取文件
        int bankerAlgorithm(const int&, const vector<vector<int>>&);    // 银行家算法
        int safetyAlgorithm();                                          // 安全性算法
        void printRunOrder(const int&);
    private:
        unsigned long PROCESS;                                          // 进程数量
        unsigned long REC_NUM;                                          // 资源种类数量
        ///定义数据结构
        vector<int>         Available;                                  // 可用资源
        vector<int>         sign;                                       // 记录成功的安全序列
        vector<vector<int>> work,                                       // 工作矩阵work
                            workAll,                                    // 可用资源矩阵workAll
                            Max,                                        // 最大需求矩阵Max
                            Allocation,                                 // 分配矩阵Allocation
                            Need;                                       // 需求矩阵Need
    };
} // namespace vincent

#endif //CPP_VBANKER_H
