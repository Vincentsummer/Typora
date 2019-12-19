# C++学习笔记

## 基本知识与思想

### 左值与右值，左值引用与右值引用（C++11）

#### 左值与右值

所有的具名变量或对象都是左值，而匿名变量则是右值。左值可以取地址，而右值不能。

在C++11中，将右值又划分为纯右值（prvalue）与将亡值，C++11中所有的值必属于左值、将亡值、纯右值三者之一。比如，非引用返回的临时变量、运算表达式产生的临时变量、原始字面量和lambda表达式等都是纯右值。而将亡值是C++11新增的、与右值引用相关的表达式，比如，将要被移动的对象、T&&函数返回值、std::move返回值和转换为T&&的类型的转换函数的返回值等。

```C++
// i为左值，getVar()函数返回的临时值为右值。
int i = getVar();
```

#### 引用

C++中的引用通过**const指针实现**，**分配额外的内存空间**。

```C++
int b = 0;
int &a = b;
//等价于
int *const c = &b;

//const 引用。先在数据区开辟一个值为1的无名整型量，再将引用d与这个整型量进行绑定。
//常量左值引用是一个“万能”的引用类型，可以接受左值、右值、常量左值和常量右值。需要注意的是普通的左值引用不能接受右值
const int &d = 1; // ok
int &e = 1; // error
```

####  右值引用（C++11）

右值引用是对右值的引用。通过右值引用的声明，右值又“重获新生”，其生命周期与右值引用类型变量的生命周期一样长，只要该变量还活着，该右值临时量将会一直存活下去。

```c++
T&& k = getVar();
```

右值引用（&&）与左值引用（&）的区别：

1. 绑定的对象（引用的对象）不同，左值引用绑定的是返回左值引用的函数、赋值、下标、解引用、前置递增递减。
2. 左值持久，右值短暂，右值只能绑定到临时对象，所引用的对象将要销毁或该对象没有其他用户。
3. 使用右值引用的代码可以自由的接管所引用对象的内容。

```C++
 //初始化时，右值引用要用一个右值表达式绑定。和const int &a=1；相同的操作。
int &&a = 1;
int b = 2;
a = b; // 可以用左值表达式修改右值引用的所引用临时对象的值，即rebind。rebind是传值过程，对a的操作不影响b。
a--; // 此时a为1，b为2。
// 右值引用a是一个左值，右值引用c初始化时不能引用左值a
int &&c = a; // error
```

右值引用是C++11中新增加的一个很重要的特性，其主是要用来解决C++98/03中遇到的两个问题，第一个问题就是临时对象非必要的昂贵的拷贝操作（移动语义），第二个问题是在模板函数中如何按照参数的实际类型进行转发（完美转发）。

右值引用独立于左值和右值。即右值引用类型的变量可能是左值也可能是右值。如T&&？

```C++
template<typename T>
void f(T&& t){}
 
f(10); //t是右值
 
int x = 10;
f(x); //t是左值
```

T&& t在发生自动类型推断（如函数模板的类型自动推导，或auto关键字）的时候，它是未定的引用类型（universal references），如果被一个左值初始化，它就是一个左值；如果它被一个右值初始化，它就是一个右值，它是左值还是右值取决于它的初始化。

引用折叠：

- 所有的右值引用叠加到右值引用上仍然还是一个右值引用；
- 所有的其他引用类型之间的叠加都将变成左值引用。

### 浅拷贝与深拷贝

浅拷贝：如果复制的对象中引用了一个外部内容（例如分配在堆上的数据），那么在复制这个对象的时候，让新旧两个对象指向同一个外部内容，就是浅拷贝。（指针虽然复制了，但所指向的空间内容并没有复制，而是由两个对象共用，两个对象不独立，删除空间存在）**默认构造函数为浅拷贝**。

深拷贝：如果在复制这个对象的时候为新对象制作了外部对象的独立复制，就是深拷贝。

### 构造函数、拷贝构造函数、移动构造函数、赋值操作符、移动赋值操作符

#### 构造函数、拷贝构造函数与赋值操作符

C++中一般创建对象，拷贝或赋值的方式有构造函数，拷贝构造函数，赋值函数这三种方法。

一个C++的空类，编译器会加入哪些默认的成员函数：

- 默认构造函数和拷贝构造函数
- 析构函数
- 赋值函数（赋值运算符）
- 取值函数

构造函数可以被重载，可以多个，可以带参数；析构函数只有一个，不能被重载，不带参数。

构造函数是一种特殊的类成员函数，是当创建一个类的对象时，它被调用来对类的数据成员进行初始化和分配内存。（构造函数的命名必须和类名完全相同）。

拷贝构造函数是C++独有的，它是一种特殊的构造函数，用基于同一类的一个对象构造和初始化另一个对象。

在C++中以下两种情况拷贝构造函数会被调用：

1. 一个对象以值传递的方式传入函数体
3. 一个对象需要通过另一个对象进行初始化

当一个类的对象向该类的另一个对象赋值时，就会用到该类的赋值操作符。

拷贝构造函数和赋值操作符的区别：

1. 拷贝构造函数是一个对象初始化一块内存区域，这块内存就是新对象的内存区，而赋值操作符是对于一个已经被初始化的对象来进行赋值操作。
2. 一般来说在数据成员包含指针对象的时候，需要考虑两种不同的处理需求：一种是复制指针对象，另一种是引用指针对象。拷贝构造函数大多数情况下是复制（深拷贝），而赋值操作符是引用对象（浅拷贝）。
3. 实现不一样。拷贝构造函数首先是一个构造函数，它调用时候是通过参数的对象初始化产生一个对象。赋值操作符则是把一个新的对象赋值给一个原有的对象，所以如果原来的对象中有内存分配要先把内存释放掉，而且还要检察一下两个对象是不是同一个对象，如果是，不做任何操作，直接返回。

总结：

- 对象不存在，且没用别的对象来初始化，就是调用了构造函数；
- 对象不存在，且用别的对象来初始化，就是拷贝构造函数。
- 对象存在，用别的对象来给它赋值，就是赋值函数。

#### 移动构造函数与移动赋值操作符（C++11）

**移动构造函数使用右值引用来避免临时对象的拷贝构造（深拷贝）**，将资源（堆、系统对象等）通过浅拷贝方式从一个对象转移到另一个对象，这样能减少不必要的临时对象的创建、拷贝以及销毁，可以大幅度提高C++应用程序的性能，消除临时对象的维护(创建和销毁)对性能的影响。移动赋值操作符同理。

```c++
//
// Created by vincent on 19-12-17.
//

#include <iostream>
#include <cstring>

using namespace std;

class Foo
{
public:
    Foo()   // 无参构造函数
    {
        v_   = NULL;
        len_ = 0;
        cout << "Constructor" << endl;
    }

    Foo(const char *p)  // 有参构造函数
    {
        len_ = strlen(p);
        setV(p);
        cout << "Constructor, v_:" << getV() << endl;
    }

    Foo(const Foo& a) // 拷贝构造函数
    {
        len_ = a.len_;
        setV(a.v_);
        cout << "Copy Constructor, v_:" << getV() << endl;
    }

    Foo& operator=(const Foo& a)    // 赋值操作符
    {
        if (this != &a){
            len_ = a.len_;
            setV(a.v_);
        }
        cout << " = func, v_" << getV() << endl;
        return *this;
    }

    Foo(Foo&& a) // 移动构造函数
    {
        len_   = a.len_;
        v_     = a.v_;
        a.len_ = 0;
        a.v_   = NULL;
        cout << "Move Constructor, v_:" << getV() << endl;
    }

    Foo& operator=(Foo&& a)    // 移动赋值操作符
    {
        if (this != &a){
            len_   = a.len_;
            v_     = a.v_;
            a.len_ = 0;
            a.v_   = NULL;
        }
        cout << " Move = func, v_:" << getV() << endl;
        return *this;
    }

    virtual ~Foo(){ // 析构函数
        if (v_ != NULL){
            cout << "Deconstructor" << endl;
            free(v_);
            return;
        }
        else{
            cout << "v_ == NULL" << endl;
        }

    }

    void setV(const char *s)
    {
        v_ = new char[len_ + 1];
        memcpy(v_, s, len_);
        v_[len_] = '\0';
    }

    char* getV() const
    {
        return v_;
    }

private:
    char*  v_;
    size_t len_;
};

Foo& getA1(Foo* b)
{
    return *b;
}

Foo getA2(const char* str)
{
    Foo a(str);
    return a;
}

void printV1(const Foo a)
{
    cout << a.getV() << endl;
}

void printV2(const Foo& a)
{
    cout << a.getV() << endl;
}

void printV3(const Foo&& a)
{
    cout << a.getV() << endl;
}

void printV4(const Foo* a)
{
    cout << a->getV() << endl;
}

int main()
{
    // 调用有参构造函数一次
    Foo a("vincent");
    // 不调用构造函数
    Foo &b = getA1(&a);
    /* 调用有参构造函数一次，移动构造函数两次。分别为返回临时对象和构造c时调用。
     * 若移动构造函数不存在则调用拷贝构造函数。
     * 编译器的返回值优化会将临时对象优化掉，使其只调用一次有参构造函数。
     * 等价于Foo c(getA2("vincent"));。
     */
    Foo c  = getA2("bug");

    /* 调用有参构造函数一次，移动构造函数一次。
     * 原因在于右值引用绑定了右值，使临时右值的生命周期得以延长。
     * */
    Foo &&d = getA2("vincent");

    //c为左值， 调用拷贝构造函数一次。等价于 Foo f(c);
    Foo e = c;

    // 调用赋值操作符
    a = e;
    // 调用移动赋值操作符，此时e.v_ == NULL;
    c = std::move(e);

    // 值传递，调用拷贝构造函数
    printV1(c);
    // 左值引用传递，不调用构造函数
    printV2(c);
    // 右值引用传递，不调用构造函数
    printV3(std::move(c));
    // 指针传递，不调用构造函数
    printV4(&c);

    // 判断左值引用与右值引用，b为左值引用，d为右值引用。
    cout << std::is_lvalue_reference<decltype(b)>::value << endl;
    cout << std::is_rvalue_reference<decltype(d)>::value << endl;

    return 0;
}

/**************Output******************/
// 在编译时设置编译选项-fno-elide-constructors用来关闭返回值优化效果。
Constructor, v_:vincent
Constructor, v_:bug
Move Constructor, v_:bug
v_ == NULL
Move Constructor, v_:bug
v_ == NULL
Constructor, v_:vincent
Move Constructor, v_:vincent
v_ == NULL
Copy Constructor, v_:bug
 = func, v_bug
 Move = func, v_:bug
Copy Constructor, v_:bug
bug
Deconstructor
bug
bug
bug
1
1
v_ == NULL
Deconstructor
Deconstructor
Deconstructor
```

### std::move（移动语义）与std::forward（完美转发）（C++11）

### auto 与 decltype（C++11）的联系与区别

### C++ volatile特性

### C++单例模式的实现

### 不完全类型

### SFINAE特性

### RAII与Scoped Locking思想

### C++中的四种智能指针

### static_cast与dynamic_cast

### atexit登记函数

### syscall系统调用

### std::function与std::bind（C++11）

### std::ref和std::cref

### static_assert与assert

### size_type，size_t，ssize_t，与int的区别

### calloc 与 malloc

### typedef 与 define

### struct，union及enum

## 网络编程

### 观察者模式

### LockFree思想

### 网络编程模型

#### Reactor模式

#### 多线程编程中的fork()函数

