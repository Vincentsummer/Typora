#include<iostream>
#include<string.h>

using namespace std;

class CMyString
{
public:
    CMyString(char* pData = nullptr);
    CMyString(const CMyString& str);
    CMyString& operator=(const CMyString& str);
    ~CMyString(void);

private:
    char* m_pdata;
};

// 考虑如下4点：
// 1. 把返回值的类型声明为该类型的引用，并在函数结束前返回实例自身的引用
// 2. 把传入的参数的类型声明为常量引用。
//  3. 释放实例自身已有的内存。
// 4. 判断传入的参数和当前的实例（*this）是否为同一个实例。

// 经典解法
CMyString& CMyString::operator=(const CMyString &str)
{
    if (this == &str)
        return *this;

    delete []m_pdata;
    m_pdata = nullptr;

    m_pdata = new char[strlen(str.m_pdata) + 1];
    strcpy(m_pdata, str.m_pdata);

    return *this;
}

//// 考虑异常安全性的解法
//CMyString& CMyString::operator=(const CMyString &str)
//{
//    if (this != &str){
//        CMyString strTemp(str);
//
//        char* pTemp = strTemp.m_pdata;
//        strTemp.m_pdata = m_pdata;
//        m_pdata = pTemp;
//    }
//
//    return *this;
//}
