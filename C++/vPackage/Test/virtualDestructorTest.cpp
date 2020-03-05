//
// Created by vincent on 20-2-26.
//

/**
 * 当某个类需要作为基类时，其析构函数通常被声明为virtual(即虚析构函数)。
 * 虚析构函数使得在删除指向子类对象的基类指针时可以调用子类的析构函数达到释放子类中堆内存的目的，而防止内存泄露。
 * 若基类没有生命虚析构函数，子类便不会得到析构，此时可以将一个没有虚析构函数的子类对象绑定到基类的引用变量上，
 * 使得子类得到析构。
 * **/

#include <iostream>

using namespace std;

class A
{
public:
    A(){ cout << "A()" << endl; }
    ~A() { cout << "~A()" << endl; }
};

class B
{
public:
    B() { cout << "B()" << endl; }
    virtual ~B() { cout << "~B()" << endl; }
};

class C : public A
{
public:
    C() { cout << "C()" << endl; }
    ~C() { cout << "~C()" << endl; }
};

class D : public B
{
public:
    D() { cout << "D()" << endl; }
    ~D() { cout << "~D()" << endl; }
};

int main()
{
    A *c = new C(); // 基类没有虚析构函数，子类不会被析构
    delete c;
    cout << endl;

    B *d = new D(); // 基类有虚析构函数，子类正常析构
    delete d;
    cout << endl;

    C cc;
    A &a = cc;  // 子类对象绑定到基类的引用变量上，子类正常析构
}