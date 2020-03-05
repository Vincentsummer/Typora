//
// Created by vincent on 20-3-1.
//

#include <zconf.h>
#include "../vThread/vBanker.h"

#define PROCESS 5
#define RES_NUM 3

string file1 = "../../vPackage/Test/bankerAllData.txt";
string file2 = "../../vPackage/Test/bankerData.txt";

int main()
{
    vincent::Banker banker(PROCESS, RES_NUM);
    banker.readFile(file1, file2);

    int i, N = 0;

    vector<vector<int>> request(PROCESS, vector<int>(RES_NUM));
    while (N < 999){
        cout << endl << "请输入请求资源Request[进程标号i][资源类型j]:" << endl;
        cout << "进程i=：";
        cin >> i;
        cout<<"各类资源数量(A B C)=:  ";
        for(int m = 0; m < RES_NUM; m++)
            cin >> request[i][m];
        //执行银行家算法
        int result = banker.bankerAlgorithm(i, request);
        //输出每次判断产生的执行序列
        cout << endl << "资源分配表：" << endl;
        banker.printRunOrder(result);
        cout << endl << "请输入N(当N=999退出)：" << endl;
        cin >> N;
    }
}