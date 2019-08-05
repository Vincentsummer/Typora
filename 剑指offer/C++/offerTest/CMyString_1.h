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

// ��������4�㣺
// 1. �ѷ���ֵ����������Ϊ�����͵����ã����ں�������ǰ����ʵ�����������
// 2. �Ѵ���Ĳ�������������Ϊ�������á�
//  3. �ͷ�ʵ���������е��ڴ档
// 4. �жϴ���Ĳ����͵�ǰ��ʵ����*this���Ƿ�Ϊͬһ��ʵ����

// ����ⷨ
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

//// �����쳣��ȫ�ԵĽⷨ
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
