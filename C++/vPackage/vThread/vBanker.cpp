//
// Created by vincent on 20-3-1.
//

#include "vBanker.h"

namespace vincent
{
    // 读取文件数据，打印到控制台，并将数据存入到相应矩阵中
    void Banker::readFile(const string &file1, const string &file2)
    {
        // 读取完整文件并打印
        ifstream inAllData(file1, ios::in); // 创建文件流对象
        if (!inAllData){
            cerr << "File1 open error." << endl;
            exit(-1);
        }

        else{
            char c;
            // 按字符读取文件内容，到达末尾停止
            while ((c = inAllData.get()) != EOF)
                cout << c;
            cout << endl;
            inAllData.close();
        }

        // 读取只有数字的文件并存入矩阵中
        ifstream inData(file2, ios::in);
        if (!inData){
            cerr << "File2 open error." << endl;
            exit(-1);
        }
        else{
            int data;
            for (int i = 0; i < PROCESS; i++) {
                for (int j = 0; j < REC_NUM; j++) {
                    inData >> data;
                    Max[i][j] = data;
                }
                for (int j = 0; j < REC_NUM; j++){
                    inData >> data;
                    Allocation[i][j] = data;
                }
                for (int j = 0; j < REC_NUM; j++){
                    inData >> data;
                    Need[i][j] = data;
                }
                if (i == 0){
                    for(int j = 0; j < REC_NUM; j++) {
                        inData >> data;
                        Available[j]=data;
                    }
                }
            }
            inData.close();
        }
    }

    int Banker::bankerAlgorithm(const int &i, const vector<vector<int>> &request)
    {
        for (int j = 0; j < REC_NUM; j++){
            if (request[i][j] > Need[i][j]){
                cout << "The number of resouces that needed out of max value." << endl;
                return 0;
            }
            else if (request[i][j] > Available[j]){
                cout << " Resouce is not enough, process " << i << " need to wait!" << endl;
                return 0;
            }
        }

        // 尝试为进程分配资源
        for (int j = 0; j < REC_NUM; j++){
            Available[j] = Available[j] - request[i][j];
            Allocation[i][j] = Allocation[i][j] + request[i][j];
            Need[i][j] = Need[i][j] - request[i][j];
        }

        // 执行安全性算法
        int n = safetyAlgorithm();
        cout << endl;

        // 所有进程均返回true, 表示此刻安全
        if (n == PROCESS)
            cout << "it's secure to allocate resouce to process " << i << endl;
        else{
            // 把分配给进程i的资源归还给系统
            for (int j = 0; j < REC_NUM; j++){
                Available[j] = Available[j] + request[i][j];
                Allocation[i][j] = Available[j] - request[i][j];
                Need[i][j] = Need[i][j] + request[i][j];
            }
            cout << "it's not secure to allocate resouce to process " << i << endl;
        }
        return n;
    }

    int Banker::safetyAlgorithm()
    {
        int i = 0, j = 0, m = 0, n = 0;
        //将可用资源数目赋给工作向量Work
        vector<int> Work(Available), Finish(PROCESS, 0);

        while (i < PROCESS){
            if (Finish[i] == 0){
                //满足释放资源条件，并从头开始扫描进程集合
                while (j < REC_NUM && Need[i][j] <= Work[j])
                    j++;
                if (j == REC_NUM){
                    for (int k = 0; k < REC_NUM; k++){
                        work[i][k] = Work[k];
                        Work[k] += Allocation[i][k];
                        workAll[i][k] = Work[k];
                    }
                    Finish[i] = 1;
                    sign[m++] = i;  // 保存安全序列
                    i = -1;
                    n++;            // 统计安全执行的进程
                }
                j = 0;
            }
            i++;
        }

        return n;
    }

    void Banker::printRunOrder(const int &result)
    {
        if(result == PROCESS) {
            cout << " 进程\\资源情况" << "   Work" << "   Need"
                 << "   Allocation" << "   Work+Available" << "   Finish" << endl;

            for(int i = 0;i < PROCESS;i++) {
                cout<<"    "<<"P["<<sign[i]<<"]  "<<'\t';
                for(int j = 0;j < REC_NUM;j++)
                    cout<<work[sign[i]][j]<<" ";
                cout<<'\t'<<'\t';
                for(int j = 0;j < REC_NUM;j++)
                    cout<<Need[sign[i]][j]<<" ";
                cout<<'\t'<<'\t';
                for(int j = 0;j < REC_NUM;j++)
                    cout<<Allocation[sign[i]][j]<<" ";
                cout<<'\t'<<'\t';
                for(int j = 0;j < REC_NUM;j++)
                    cout<<workAll[sign[i]][j]<<" ";
                cout<<'\t'<<'\t';
                cout<<"true"<<endl;
            }
            cout<<endl<<"找到安全序列｛P["<<sign[0]<<"]";
            for(int m = 1;m < PROCESS;m++){
                cout<<", P["<<sign[m]<<"]";
            }
            cout<<"}"<<endl;
        } else {
            cout<<"   进程\\资源情况 "<<"  Allocation"<<"   Need"<<"   Available";
            cout<<endl;
            for(int k = 0;k < 5;k++){
                cout<<'\t'<<"P["<<k<<"]"<<'\t'<<'\t';
                for(int j = 0;j < 3;j++)
                    cout<<Allocation[k][j]<<" ";
                cout<<'\t'<<'\t';
                for(int j = 0;j < 3;j++)
                    cout<<Need[k][j]<<" ";
                cout<<'\t'<<'\t';
                if(k == 0) {
                    for(int j = 0;j < 3;j++)
                        cout<<Available[j]<<" ";
                }
                cout<<endl;
            }
        }
    }
} // namespace vincent
